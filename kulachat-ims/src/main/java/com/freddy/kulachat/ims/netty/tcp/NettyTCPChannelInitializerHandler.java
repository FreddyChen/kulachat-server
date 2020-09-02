package com.freddy.kulachat.ims.netty.tcp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 14:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NettyTCPChannelInitializerHandler extends ChannelInitializer<Channel> {

    private NettyTCPIMS ims;

    NettyTCPChannelInitializerHandler(NettyTCPIMS ims) {
        this.ims = ims;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new NettyTCPReadHandler(ims));
    }
}
