package io.majiang.core;

import com.google.gson.Gson;

import io.majiang.service.BaseService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DispatcherHandler extends SimpleChannelInboundHandler<MjRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MjRequest request)
			throws Exception {
		Class<BaseService> serviceClass = (Class<BaseService>) ClassScanner.getUriClass(request.getUri());		
		if(serviceClass == null){
			throw new Exception("no service");
		}
		try {
			MjResponse response = new MjResponse();
			serviceClass.newInstance().invoke(request,response);
			ctx.write(response.toJson());
			ctx.writeAndFlush("\n\r\n");
		} catch (Exception e) {
			System.err.println("service erro");
			e.printStackTrace();
			ctx.writeAndFlush("erro o\r\n");
		}

	}
	
}
