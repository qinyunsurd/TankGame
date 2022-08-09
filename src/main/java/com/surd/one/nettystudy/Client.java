package com.surd.one.nettystudy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author admin
 * @date
 */
public class Client {
    public static void main(String[] args) {
        //事件处理 线程池
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture future =
                    b.group(group).channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer()) //connect 后才执行
                    .connect("localhost", 8888);
                    //.addListener(new ChannelFutureListener() {
                    //    @Override
                    //    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    //        if (channelFuture.isSuccess()){
                    //            System.out.println("connected!");
                    //        }else {
                    //            System.out.println("not connect!");
                    //        }
                    //    }
                    //}).sync();

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("connected!");
                    } else {
                        System.out.println("not connect!");
                    }
                }
            });
            future.sync();
            System.out.println("....");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            group.shutdownGracefully();
        }

    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println(socketChannel);
    }
}