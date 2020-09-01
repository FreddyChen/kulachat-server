package com.freddy.kulachat.ims.config;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/07 16:42
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class IMSOptions {
    private CommunicationProtocol communicationProtocol;// 通信协议
    private TransportProtocol transportProtocol;// 传输协议
    private int tcpPort;// TCP端口号
    private int webSocketPort;// WebSocket端口号

    private IMSOptions(Builder builder) {
        if(builder == null) {
            return;
        }

        this.communicationProtocol = builder.communicationProtocol;
        this.transportProtocol = builder.transportProtocol;
        this.tcpPort = builder.tcpPort;
        this.webSocketPort = builder.webSocketPort;
    }

    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

    public TransportProtocol getTransportProtocol() {
        return transportProtocol;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public int getWebSocketPort() {
        return webSocketPort;
    }

    public static class Builder {
        private CommunicationProtocol communicationProtocol;// 通信协议
        private TransportProtocol transportProtocol;// 传输协议
        private int tcpPort;// TCP端口号
        private int webSocketPort;// WebSocket端口号

        public Builder() {
            this.tcpPort = IMSConfig.TCP_PORT;
            this.webSocketPort = IMSConfig.WEBSOCKET_PORT;
        }

        public Builder setCommunicationProtocol(CommunicationProtocol communicationProtocol) {
            this.communicationProtocol = communicationProtocol;
            return this;
        }

        public Builder setTransportProtocol(TransportProtocol transportProtocol) {
            this.transportProtocol = transportProtocol;
            return this;
        }

        public Builder setTCPPort(int tcpPort) {
            this.tcpPort = tcpPort;
            return this;
        }

        public Builder setWebSocketPort(int webSocketPort) {
            this.webSocketPort = webSocketPort;
            return this;
        }

        public IMSOptions build() {
            return new IMSOptions(this);
        }
    }
}
