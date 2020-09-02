package com.freddy.kulachat.ims;

import com.freddy.kulachat.ims.config.CommunicationProtocol;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.config.ImplementationMode;
import com.freddy.kulachat.ims.config.TransportProtocol;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/31 19:19
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class IMSKit {

    private static final Logger logger = LoggerFactory.getLogger(IMSKit.class);
    private IMSInterface ims;

    private IMSKit() {
    }

    public static IMSKit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final IMSKit INSTANCE = new IMSKit();
    }

    public boolean init(IMSOptions options, IMSMsgReceivedListener msgReceivedListener) {
        logger.debug("IMSKit初始化开始");
        if(options == null) {
            logger.warn("IMSKit初始化失败：IMSOptions 为 null");
            return false;
        }

        ImplementationMode implementationMode = options.getImplementationMode();
        if (implementationMode == null) {
            logger.warn("IMSKit初始化失败：ImplementationMode 为 null");
            return false;
        }

        CommunicationProtocol communicationProtocol = options.getCommunicationProtocol();
        if (communicationProtocol == null) {
            logger.warn("IMSKit初始化失败：CommunicationProtocol 为 null");
            return false;
        }

        TransportProtocol transportProtocol = options.getTransportProtocol();
        if(transportProtocol == null) {
            logger.warn("IMSKit初始化失败：TransportProtocol 为 null");
            return false;
        }

        ims = IMSFactory.getIMS(implementationMode, communicationProtocol);
        if (ims == null) {
            logger.warn("IMSKit初始化失败：ims 为 null");
            return false;
        }

        boolean initialized = ims.init(options, msgReceivedListener);
        if (!initialized) {
            logger.warn("IMSKit初始化失败：请查看 " + ims.getClass().getSimpleName() + " 相关的日志");
            return false;
        }

        logger.warn("IMSKit初始化完成\nims = " + ims.getClass().getSimpleName() + "\noptions = " + options);
        return true;
    }

    public void start() {
        if(ims == null) {
            logger.warn("IMSKit启动失败");
            return;
        }

        ims.start();
    }
}
