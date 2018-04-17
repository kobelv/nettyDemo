package com.kobe.client;

import com.kobe.netty.dto.AddressBookProtos;
import com.kobe.netty.serial.MarshallingCodeCFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
		pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
		
		pipeline.addLast(new EchoClientHandler());
		pipeline.addLast(new ReadTimeoutHandler(5)); 
		
		//add google protocol buffer encoder, decoder		
        pipeline.addLast(new ProtobufVarint32FrameDecoder());           
        //add your owned java object here
        pipeline.addLast(new ProtobufDecoder(AddressBookProtos.AddressBook.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());     
        pipeline.addLast(new ProtobufEncoder());
        
	}

}
