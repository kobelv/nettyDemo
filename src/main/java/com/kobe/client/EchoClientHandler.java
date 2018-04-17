package com.kobe.client;

import com.kobe.netty.dto.AddressBookProtos;
import com.kobe.netty.dto.Request;
import com.kobe.netty.dto.Response;
import com.kobe.netty.dto.AddressBookProtos.Person;
import com.kobe.netty.dto.AddressBookProtos.Person.PhoneNumber;
import com.kobe.netty.dto.AddressBookProtos.Person.PhoneType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter{

	//ctx的读、写、关闭操作都是异步的，要等到操作完毕后做某件事，必须拿到它返回的FutureChannel，
	//并对它加一个 listener
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof String){
            System.out.println(msg.toString());
        }else{
        	Response m = (Response)msg;
            System.out.println("from server: "+m.getResponseBody());
        }
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/*Request m = new Request();
		m.setId("001");
		m.setName("kobe");
        m.setRequestBody("message sent from client");
        ctx.writeAndFlush(m);*/
        
        PhoneNumber phone = PhoneNumber.newBuilder().setNumber("12345678").setType(PhoneType.MOBILE).build();
		Person kobe = Person.newBuilder().setEmail("xxx@gmail.com").setId(1).setName("kobe").addPhones(0, phone).build();		
		AddressBookProtos.AddressBook addr = AddressBookProtos.AddressBook.newBuilder().addPerson(kobe).build();
		ctx.writeAndFlush(addr);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is closed");
    }
    
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.getStackTrace();
		ctx.close();
	}

}
