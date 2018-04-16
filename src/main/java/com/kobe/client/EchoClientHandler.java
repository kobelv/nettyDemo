package com.kobe.client;

import com.kobe.netty.dto.Request;
import com.kobe.netty.dto.Response;

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
		Request m = new Request();
		m.setId("001");
		m.setName("kobe");
        m.setRequestBody("message sent from client");
        ctx.writeAndFlush(m);
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
