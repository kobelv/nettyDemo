package com.kobe.server;

import com.kobe.netty.serial.MarshallingCodeCFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
		
		ch.pipeline().addLast(new EchoServerHandler());
		
		//if go idle beyond 5 seconds, the connection will be closed, to save cost 
		ch.pipeline().addLast(new ReadTimeoutHandler(5)); 
		
	}

}
