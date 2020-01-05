package com.darin.nettylearning.inboundhandlerandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("服务器的ip=" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息=" + msg);

    }

    //重写channelActive 发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");

        ctx.writeAndFlush(1234568888L); //发送的是一个long
        //分析
        //1. "abcdabcdabcdabcd" 是 16个字节
        //2. 该处理器的下一个handler 是  MyLongToByteEncoder
        //3. MyLongToByteEncoder 父类  MessageToByteEncoder
        //4. 父类  MessageToByteEncoder
        //5. 因此我们编写 Encoder 是要注意传入的数据类型和处理的数据类型一致
//         ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd",CharsetUtil.UTF_8));
    }
}
