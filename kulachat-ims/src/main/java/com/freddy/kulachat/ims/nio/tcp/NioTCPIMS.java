package com.freddy.kulachat.ims.nio.tcp;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;
import com.freddy.kulachat.ims.mina.websocket.MinaWebSocketIMS;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/31 19:17
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class NioTCPIMS implements IMSInterface {

    private NioTCPIMS() {
    }

    public static NioTCPIMS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final NioTCPIMS INSTANCE = new NioTCPIMS();
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
