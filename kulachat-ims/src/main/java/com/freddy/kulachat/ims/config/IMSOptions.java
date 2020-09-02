package com.freddy.kulachat.ims.config;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/07 16:42
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class IMSOptions {

    private ImplementationMode implementationMode;// 实现方式
    private CommunicationProtocol communicationProtocol;// 通信协议
    private TransportProtocol transportProtocol;// 传输协议
    private int port;// 端口号

    private IMSOptions(Builder builder) {
        if (builder == null) {
            return;
        }

        this.implementationMode = builder.implementationMode;
        this.communicationProtocol = builder.communicationProtocol;
        this.transportProtocol = builder.transportProtocol;
        this.port = builder.port;
    }

    public ImplementationMode getImplementationMode() {
        return implementationMode;
    }

    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

    public TransportProtocol getTransportProtocol() {
        return transportProtocol;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "IMSOptions\n{" +
                "\n\timplementationMode=" + implementationMode +
                "\n\tcommunicationProtocol=" + communicationProtocol +
                "\n\ttransportProtocol=" + transportProtocol +
                "\n\tport=" + port +
                "\n}";
    }

    public static class Builder {

        private ImplementationMode implementationMode;// 实现方式
        private CommunicationProtocol communicationProtocol;// 通信协议
        private TransportProtocol transportProtocol;// 传输协议
        private int port;// 端口号

        public Builder() {
            this.implementationMode = ImplementationMode.Netty;
        }

        public Builder setImplementationMode(ImplementationMode implementationMode) {
            this.implementationMode = implementationMode;
            return this;
        }

        public Builder setCommunicationProtocol(CommunicationProtocol communicationProtocol) {
            this.communicationProtocol = communicationProtocol;
            if(this.port == 0) {
                switch (communicationProtocol) {
                    case TCP:
                        this.port = IMSConfig.TCP_PORT;
                        break;

                    case WebSocket:
                        this.port = IMSConfig.WEBSOCKET_PORT;
                        break;
                }
            }
            return this;
        }

        public Builder setTransportProtocol(TransportProtocol transportProtocol) {
            this.transportProtocol = transportProtocol;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public IMSOptions build() {
            return new IMSOptions(this);
        }
    }
}
