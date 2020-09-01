package com.freddy.kulachat.core;

import com.freddy.kulachat.ims.IMSKit;
import com.freddy.kulachat.ims.config.IMSOptions;
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
		initIMS();
		logger.debug("KulaChat server started.");
	}

	private static void initIMS() {
		logger.debug("Init IMS begin...");
		IMSOptions options = new IMSOptions.Builder()
				.setTCPPort(1)
				.build();

		IMSKit.getInstance().init(options);
		logger.debug("Init IMS end.");
	}
}
