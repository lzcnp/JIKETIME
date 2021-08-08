package com.lzc.cn.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;



/**
 * @Auther: 梁川
 * @Date: 2021/07/30
 * @Description: 通用handler,处理I/O事件 就是具体的逻辑实现
 */
@ChannelHandler.Sharable // todo  这个主要是告诉我们handler线程是安全的
public class HandlerClient extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     *
     * @param channelHandlerContext 通道的上下文
     * @param byteBuf  字节序列
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("接收到的消息:"+byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 异常处理 打印异常并关闭通道
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
