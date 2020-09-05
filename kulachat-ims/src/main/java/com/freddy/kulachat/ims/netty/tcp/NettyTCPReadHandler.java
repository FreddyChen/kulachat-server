package com.freddy.kulachat.ims.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 19:17
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NettyTCPReadHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTCPReadHandler.class);
    private NettyTCPIMS ims;

    NettyTCPReadHandler(NettyTCPIMS ims) {
        this.ims = ims;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channelActive() ctx = " + ctx);
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        logger.debug(String.format("客户端【%1$s:%2$d】已连接)", address.getHostName(), address.getPort()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.warn("channelInactive() ctx = " + ctx);
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        logger.debug(String.format("客户端【%1$s:%2$d】已断开连接)", address.getHostName(), address.getPort()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("exceptionCaught() ctx = " + ctx + "\tcause = " + cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("channelRead() ctx = " + ctx + "\tmsg = " + msg);
    }
}
