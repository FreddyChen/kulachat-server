package com.freddy.kulachat.ims.interf;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;

/**
 * @author FreddyChen
 * @name IMS抽象接口
 * @date 2020/08/07 16:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc 不同的服务端协议实现此接口即可，例：
 * {@link com.freddy.kulachat.ims.netty.tcp.NettyTCPIMS}
 * {@link com.freddy.kulachat.ims.netty.websocket.NettyWebSocketIMS}
 * {@link com.freddy.kulachat.ims.nio.tcp.NioTCPIMS}
 * {@link com.freddy.kulachat.ims.nio.websocket.NioWebSocketIMS}
 * {@link com.freddy.kulachat.ims.mina.tcp.MinaTCPIMS}
 * {@link com.freddy.kulachat.ims.mina.websocket.MinaWebSocketIMS}
 */
public interface IMSInterface {

    /**
     * 初始化
     *
     * @param options
     * @return
     */
    boolean init(IMSOptions options, IMSMsgReceivedListener msgReceivedListener);

    /**
     * 启动IMS
     */
    void start();

    /**
     * 发送消息
     *
     * @param msg
     */
    void sendMsg(IMSMsg msg);

    /**
     * 发送消息
     * 重载
     *
     * @param msg
     * @param isJoinResendManager 是否加入消息重发管理器
     */
    void sendMsg(IMSMsg msg, boolean isJoinResendManager);

    /**
     * 释放资源
     */
    void release();
}
