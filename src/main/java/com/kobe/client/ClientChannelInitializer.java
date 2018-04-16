package com.kobe.client;

import com.kobe.netty.serial.MarshallingCodeCFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
		ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
		
		ch.pipeline().addLast(new EchoClientHandler());
		//ch.pipeline().addLast(new ReadTimeoutHandler(5)); 
		
	}

}
