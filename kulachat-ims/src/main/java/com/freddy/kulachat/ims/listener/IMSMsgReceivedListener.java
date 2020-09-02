package com.freddy.kulachat.ims.listener;

import com.freddy.kulachat.ims.bean.IMSMsg;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 17:04
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public interface IMSMsgReceivedListener {

    void onMsgReceived(IMSMsg msg);
}
