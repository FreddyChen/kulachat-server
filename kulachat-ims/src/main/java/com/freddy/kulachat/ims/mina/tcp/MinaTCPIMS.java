package com.freddy.kulachat.ims.mina.tcp;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.interf.IMSInterface;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/31 19:16
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class MinaTCPIMS implements IMSInterface {

    @Override
    public IMSInterface init(IMSOptions options) {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

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
