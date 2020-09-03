package com.freddy.kulachat.ims.netty.websocket;

import com.freddy.kulachat.ims.netty.tcp.NettyTCPReadHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 14:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NettyWebSocketChannelInitializerHandler extends ChannelInitializer<Channel> {

    private NettyWebSocketIMS ims;

    NettyWebSocketChannelInitializerHandler(NettyWebSocketIMS ims) {
        this.ims = ims;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new NettyWebSocketReadHandler(ims));
    }
}
