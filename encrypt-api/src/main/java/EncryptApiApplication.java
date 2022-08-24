import com.xiaoxcn.encrypt.RestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EncryptApiApplication {
    private int port ;

    public EncryptApiApplication(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        EncryptApiApplication application = new EncryptApiApplication(8080);
        application.start();
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new RestHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
