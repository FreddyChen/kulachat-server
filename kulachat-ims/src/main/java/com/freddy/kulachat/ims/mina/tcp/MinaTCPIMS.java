package com.freddy.kulachat.ims.mina.tcp;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;
import com.freddy.kulachat.ims.netty.websocket.NettyWebSocketIMS;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/31 19:16
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class MinaTCPIMS implements IMSInterface {

    private MinaTCPIMS() {
    }

    public static MinaTCPIMS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final MinaTCPIMS INSTANCE = new MinaTCPIMS();
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
