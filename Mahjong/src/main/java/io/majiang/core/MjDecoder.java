package io.majiang.core;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MjDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
/*		int num = in.readableBytes();
		if(num<3)
			return ;
		byte[] b = new byte[num-2];
		short uri = in.readShort();
		in.readBytes(b);
		String json = new String(b);
		out.add(new MjRequest(uri,json,ctx.channel()));*/
		int num = in.readableBytes();
		if(num<4){
			System.err.println("nothing in");
			return ;
		}
		byte[] b = new byte[num];
		in.readBytes(b);
		String request = new String(b);
		int spaceIndex = request.indexOf(" ");
		String uri = request.substring(0, spaceIndex);
		String json = request.substring(spaceIndex+1);
		out.add(new MjRequest(uri,json,ctx.channel()));
		
	}

}
