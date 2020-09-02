package com.freddy.kulachat.ims;

import com.freddy.kulachat.ims.config.CommunicationProtocol;
import com.freddy.kulachat.ims.config.ImplementationMode;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.mina.tcp.MinaTCPIMS;
import com.freddy.kulachat.ims.mina.websocket.MinaWebSocketIMS;
import com.freddy.kulachat.ims.netty.tcp.NettyTCPIMS;
import com.freddy.kulachat.ims.netty.websocket.NettyWebSocketIMS;
import com.freddy.kulachat.ims.nio.tcp.NioTCPIMS;
import com.freddy.kulachat.ims.nio.websocket.NioWebSocketIMS;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 17:10
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class IMSFactory {

    public static IMSInterface getIMS(ImplementationMode implementationMode, CommunicationProtocol communicationProtocol) {
        switch (implementationMode) {
            case Nio: {
                switch (communicationProtocol) {
                    case TCP:
                        return NioTCPIMS.getInstance();

                    case WebSocket:
                        return NioWebSocketIMS.getInstance();

                    default:
                        break;
                }
                break;
            }

            case Mina: {
                switch (communicationProtocol) {
                    case TCP:
                        return MinaTCPIMS.getInstance();

                    case WebSocket:
                        return MinaWebSocketIMS.getInstance();

                    default:
                        break;
                }
                break;
            }

            case Netty:
            default:
                switch (communicationProtocol) {
                    case TCP:
                        return NettyTCPIMS.getInstance();

                    case WebSocket:
                        return NettyWebSocketIMS.getInstance();

                    default:
                        break;
                }
                break;
        }

        return null;
    }
}
