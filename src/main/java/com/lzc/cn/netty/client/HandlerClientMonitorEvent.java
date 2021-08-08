package com.lzc.cn.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class HandlerClientMonitorEvent extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        /**
         *
         * @param channelHandlerContext 通道的上下文
         * @param byteBuf  字节序列
         * @throws Exception
         */
        System.out.println("接收到的消息:" + byteBuf.toString(CharsetUtil.UTF_8));
    }


    /**
     * 异常处理 打印异常并关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
