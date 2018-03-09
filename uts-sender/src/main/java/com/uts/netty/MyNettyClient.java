package com.uts.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.SctpChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
public class MyNettyClient {
    public static class  SingletionHolder{
        static  final  MyNettyClient mynettyClient = new MyNettyClient();
    }
    public static MyNettyClient getNettyClient(){
        return SingletionHolder.mynettyClient;
    }
    private int port=8765;
    private String host = "127.0.0.1";
    private Bootstrap b;
    private EventLoopGroup group;
    private ChannelFuture future;

    public MyNettyClient(){
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                ChannelPipeline p = sc.pipeline();
                p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder() );
                p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                p.addLast(new MyNettyCLientHandler());

            }
        });


    }

    public  void conn(){
        try {
         future=   b.connect(host,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            future.channel().closeFuture().sync();
            group.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ChannelFuture getFuture(){
        if(future == null){
            conn();
        }
        if(!future.channel().isActive()){
            conn();
        }
        return future;
    }

}
