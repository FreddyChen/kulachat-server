package com.freddy.kulachat.ims.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 19:17
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NettyWebSocketReadHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyWebSocketReadHandler.class);
    private NettyWebSocketIMS ims;

    NettyWebSocketReadHandler(NettyWebSocketIMS ims) {
        this.ims = ims;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.debug("channelActive() ctx = " + ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.warn("channelInactive() ctx = " + ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("exceptionCaught() ctx = " + ctx + "\tcause = " + cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        logger.debug("channelRead() ctx = " + ctx + "\tmsg = " + msg);
    }
}
