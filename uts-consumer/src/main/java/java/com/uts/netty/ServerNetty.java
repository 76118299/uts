package java.com.uts.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2018/3/7 0007.
 */
public class ServerNetty {
    public static class SingletonHolder{
        static final  ServerNetty intance = new ServerNetty();
    }

    public static  ServerNetty getIntance(){
        return SingletonHolder.intance;
    }

    private EventLoopGroup bossGroup =new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    private ServerBootstrap serverBootstrap;
    private int port = 8765;
    private ChannelFuture cf;

    private ServerNetty(){
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG,1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline p = channel.pipeline();
                        p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        p.addLast(new ServerNettyHandler());
                    }
                });
    }

    public void start(){
        try {
           cf= serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getCf(){
        return cf;
    }
    public void close(){
        try {
            cf.channel().closeFuture().sync();
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
