package com.freddy.kulachat.ims.netty.websocket;

import com.freddy.kulachat.ims.bean.IMSMsg;
import com.freddy.kulachat.ims.config.IMSOptions;
import com.freddy.kulachat.ims.interf.IMSInterface;
import com.freddy.kulachat.ims.listener.IMSMsgReceivedListener;
import com.freddy.kulachat.ims.netty.tcp.NettyTCPChannelInitializerHandler;
import com.freddy.kulachat.ims.netty.tcp.NettyTCPIMS;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;

/**
 * @author FreddyChen
 * @name Netty WebSocket IM Service
 * @date 2020/08/07 16:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc 基于Netty实现的WebSocket协议客户端
 */
public class NettyWebSocketIMS implements IMSInterface {

    private static final Logger logger = LoggerFactory.getLogger(NettyWebSocketIMS.class);
    private ServerBootstrap bootstrap;
    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private volatile boolean isClosed;
    private boolean initialized;
    private IMSOptions mIMSOptions;
    private IMSMsgReceivedListener mIMSMsgReceivedListener;

    private NettyWebSocketIMS() {
    }

    public static NettyWebSocketIMS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final NettyWebSocketIMS INSTANCE = new NettyWebSocketIMS();
    }

    @Override
    public boolean init(IMSOptions options, IMSMsgReceivedListener msgReceivedListener) {
        if (options == null) {
            initialized = false;
            return false;
        }

        this.mIMSOptions = options;
        this.mIMSMsgReceivedListener = msgReceivedListener;
        initialized = true;
        return true;
    }

    @Override
    public void start() {
        if (!initialized) {
            logger.warn("NettyWebSocketIMS启动失败：初始化失败");
            return;
        }

        try {
            initServerBootstrap();
            ChannelFuture future = bootstrap.bind(mIMSOptions.getPort()).sync();
            channel = future.channel();
            if (channel != null && channel.isOpen() && channel.isActive() && channel.isRegistered() && channel.isWritable()) {
                logger.debug(String.format("NettyWebSocketIMS启动成功，address = ws://%1$s:%2$d/websocket", Inet4Address.getLocalHost().getHostAddress(), mIMSOptions.getPort()));
            } else {
                logger.debug("NettyWebSocketIMS启动失败");
            }

            future.awaitUninterruptibly();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bossGroup.shutdownGracefully();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bossGroup = null;
            }

            try {
                workGroup.shutdownGracefully();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workGroup = null;
            }
        }
    }

    @Override
    public void sendMsg(IMSMsg msg) {

    }

    @Override
    public void sendMsg(IMSMsg msg, boolean isJoinResendManager) {

    }

    @Override
    public void release() {
        closeChannel();
        closeServerBootstrap();
        isClosed = true;
    }

    /**
     * 初始化ServerBootstrap
     */
    private void initServerBootstrap() {
        try {
            closeServerBootstrap();// 先关闭之前的bootstrap
            // boss线程池用于处理TCP连接，通常服务端开启的都是一个端口，所以线程数指定为1即可
            bossGroup = new NioEventLoopGroup(1);
            // work线程用于处理IO事件，需要多线程处理，不指定线程数，默认就是CPU核心数*2
            workGroup = new NioEventLoopGroup();
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置TCP接收缓冲区大小（字节数）
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    // 服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝。默认值，Windows为200，其他为128
                    .option(ChannelOption.SO_BACKLOG, 256)
                    // 设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 设置禁用nagle算法，如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new NettyWebSocketChannelInitializerHandler(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeServerBootstrap() {
        try {
            if (bootstrap != null) {
                bootstrap.config().group().shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bootstrap = null;
        }
    }

    private void closeChannel() {
        try {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    channel.eventLoop().shutdownGracefully();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel = null;
        }
    }
}
