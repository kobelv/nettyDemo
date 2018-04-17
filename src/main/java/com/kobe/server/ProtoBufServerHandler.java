package com.kobe.server;

import com.kobe.netty.dto.AddressBookProtos;
import com.kobe.netty.dto.AddressBookProtos.AddressBook;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoBufServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook>{

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, AddressBook arg1) throws Exception {
		System.out.println("number of person included: " + arg1.getPersonCount());
		System.out.println("person's name: " + arg1.getPerson(0).getName());
		
	}

}
