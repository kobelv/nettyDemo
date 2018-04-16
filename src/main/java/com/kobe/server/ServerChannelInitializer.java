package com.kobe.server;

import com.kobe.netty.serial.MarshallingCodeCFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
		
		ch.pipeline().addLast(new EchoServerHandler());
		//ch.pipeline().addLast(new TimeServerHandler());
		
	}

}
