package com.lzc.cn.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class ServerMonitorEventExcutor {
    private final int port;

    public ServerMonitorEventExcutor(int port) {
        this.port = port;
    }

    public void run() {
        // 初始化线程池 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 用于开启服务端的启动辅助对象(启动NIO服务)
            ServerBootstrap sb = new ServerBootstrap();
            // 配置服务端的参数
            sb.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port)) //设置监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HandlerServerMonitorEvent()); //处理
                        }
                    });

            ChannelFuture future = sb.bind().sync(); //开启服务端的监听器
            System.out.println("在"+future.channel().localAddress() +"上开启监听");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ServerMonitorEventExcutor(18081).run();
    }
}
