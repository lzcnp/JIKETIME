package com.lzc.cn.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class ClientMonitorEventExcutor {
    private final String host;
    private final int port;

    public ClientMonitorEventExcutor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @throws Exception
     * @Description: 配置相应的参数，提供连接到远端的方法
     */
    public void run() throws Exception {
        // io 线程池 创建EventLoopGroup 实例
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端启动辅助对象
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() { //ChannelInitializer 通道的初始化器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception { //pipeline
                            socketChannel.pipeline().addLast(new HandlerClient()); // 添加自定义的handler
                            /**socketChannel.pipeline().addLast(new HandlerClient());
                             * 。。。。  无数的handler的盛装容器
                             */
                        }
                    });
            /**
             * ChannelFuture 代表的是I/O的执行结果,通过时间机制，获取执行结果，通过添加监听器，执行我们想要的操作
             */
            ChannelFuture future = bs.connect().sync();
            // 发送消息到服务器端，编码格式是utf-8
            future.channel().writeAndFlush(Unpooled.copiedBuffer("hello wolrd", CharsetUtil.UTF_8));
            //阻塞操作，closeFuture()开启一个channel的监听器
            future.channel().close().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池的链接
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new ClientExcutor("127.0.0.1", 18081).run();
    }
}
