package com.lzc.cn.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Auther: 梁川
 * @Date: 2021/07/30
 * @Description: 通用handler,服务端处理I/O事件
 */
@ChannelHandler.Sharable
public class HandlerServer extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //todo 收到消息转换成字节码
        ByteBuf in = (ByteBuf) msg;
        //todo 输出消息
        System.out.println("收到客户端的消息:" + in.toString(CharsetUtil.UTF_8));
        //todo 服务端接收到消息之后写回消息发送到客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好，我是服务端，我已经收到你的消息",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //todo 打印异常
       cause.printStackTrace();
       //todo 关闭通道
        ctx.close();
    }
}
