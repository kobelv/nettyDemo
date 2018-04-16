package com.kobe.server;

import com.kobe.netty.dto.Request;
import com.kobe.netty.dto.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	//ctx的读、写、关闭操作都是异步的，要等到操作完毕后做某件事，必须拿到它返回的FutureChannel，
	//并对它加一个 listener
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof String){
            System.out.println(msg.toString());
        }else{
            Request request = (Request)msg;
            System.out.println("from client: "+ request.getRequestBody());
            
            Response response = new Response();
            response.setResponseBody("Hello CLient, server side already received your msg.");
            ctx.writeAndFlush(response);
        }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.getStackTrace();
		ctx.close();
	}

}
