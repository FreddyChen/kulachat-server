package com.freddy.kulachat.ims.netty.websocket;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;
import com.freddy.kulachat.ims.netty.tcp.NettyTCPIMS;

/**
 * @author FreddyChen
 * @name Netty WebSocket IM Service
 * @date 2020/08/07 16:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc 基于Netty实现的WebSocket协议客户端
 */
public class NettyWebSocketIMS implements IMSInterface {

    private NettyWebSocketIMS() {
    }

    public static NettyWebSocketIMS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final NettyWebSocketIMS INSTANCE = new NettyWebSocketIMS();
    }

    @Override
    public boolean init(IMSOptions options, IMSMsgReceivedListener msgReceivedListener) {
        return true;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendMsg(IMSMsg msg) {

    }

    @Override
    public void sendMsg(IMSMsg msg, boolean isJoinResendManager) {

    }

    @Override
    public void release() {

    }
}
