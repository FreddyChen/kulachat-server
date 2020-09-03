package com.freddy.kulachat.core;

import com.freddy.kulachat.ims.IMSKit;
import com.freddy.kulachat.ims.config.CommunicationProtocol;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.config.ImplementationMode;
import com.freddy.kulachat.ims.config.TransportProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})// todo 先不要自动配置DataSource
public class KulaChatCoreApplication {

    private static final Logger logger = LoggerFactory.getLogger(KulaChatCoreApplication.class);

    public static void main(String[] args) {
        logger.debug("KulaChat server starting...");
        SpringApplication.run(KulaChatCoreApplication.class, args);
        startIMS();
        logger.debug("KulaChat server started.");
    }

    private static void startIMS() {
        IMSOptions options = new IMSOptions.Builder()
                .setImplementationMode(ImplementationMode.Netty)
                .setCommunicationProtocol(CommunicationProtocol.WebSocket)
                .setTransportProtocol(TransportProtocol.Protobuf)
                .build();

        boolean initialized = IMSKit.getInstance().init(options, null);
        if(!initialized) {
            logger.warn("IMSKit启动失败，请查看IMSKit相关日志");
            return;
        }

        IMSKit.getInstance().start();
    }
}
