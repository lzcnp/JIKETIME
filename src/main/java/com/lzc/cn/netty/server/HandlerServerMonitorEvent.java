package com.lzc.cn.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

@ChannelHandler.Sharable
public class HandlerServerMonitorEvent extends ChannelInboundHandlerAdapter {
    // 通道数组，保存所有注册到EventLoop的通道
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端发过来的消息："+ byteBuf.toString(CharsetUtil.UTF_8));
        //服务端向客户端发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好我是服务端，我已经收到你的信息了",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //todo 如果有建立连接的时候就会触发这个方法
        Channel incoming = ctx.channel();
        System.out.println("客户端："+incoming.remoteAddress()+"已经上线");
        group.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //todo 如果链接断开了就从数组中删除掉
        Channel incoming = ctx.channel();
        System.out.println("客户端："+incoming.remoteAddress()+"已经断开");
        group.remove(incoming);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //todo 该方法只会在通道建立的时候建立一次
        Channel incoming = ctx.channel();
        System.out.println("客户端："+incoming.remoteAddress()+"在线");
        SimpleDateFormat df =  new SimpleDateFormat("HH:mm:ss SSS");
        String strDate = df.format(new Date());
        ctx.writeAndFlush(Unpooled.copiedBuffer("这是服务器active方法中反馈的消息"+strDate +"\r\n",CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("客户端："+incoming.remoteAddress()+"掉线了");
    }
}
