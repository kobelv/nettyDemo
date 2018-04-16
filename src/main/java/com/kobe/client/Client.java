package com.kobe.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	private String host;
	private int port;
	public Client(String host, int port) {
		
		this.host = host;
		this.port = port;
	}
	
	public void run() throws Exception{
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap bs = new Bootstrap();
			bs.group(workerGroup);
			bs.channel(NioSocketChannel.class);
			bs.handler(new ClientChannelInitializer());
			ChannelFuture f = bs.connect(host, port).sync();
			f.channel().closeFuture().sync();
			
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
	public static void main(String[] args) throws NumberFormatException, Exception {
		new Client("localhost", 6666).run();

	}
	
}
