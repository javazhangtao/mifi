package com.mifi.main.netty;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.mifi.main.GenericServer;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;


@Sharable
public class HttpCoreServerHandler extends ChannelInboundHandlerAdapter {
	
	private Logger log = Logger.getLogger(HttpCoreServerHandler.class);
	
	ExecutorService service=Executors.newFixedThreadPool(50);
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest){
			HttpRequest req = (HttpRequest) msg;
			String clientIP = req.headers().get("X-Forwarded-For");
			if(clientIP == null){
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
				clientIP = insocket.getAddress().getHostAddress();
			}
//			HttpRequest request = (HttpServletRequest) msg;
			log.info(req.getUri());
//			String ip = req. getHeader("x-forwarded-for"); 
//		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		      ip = request.getHeader("Proxy-Client-IP"); 
//		    } 
//		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		      ip = request.getHeader("WL-Proxy-Client-IP"); 
//		    } 
//		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		      ip = request.getHeader("HTTP_CLIENT_IP"); 
//		    } 
//		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
//		    } 
//		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
//		      ip = request.getRemoteAddr(); 
//		    } 
//
			URI uri = new URI(req.getUri());
//			uri.get
			if (uri.getPath().equals("/favicon.ico")) {
                return;
            }
			if(HttpHeaders.is100ContinueExpected(req)){
				ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
			}
			String _result=new GenericServer(req).handler();
			FullHttpResponse  response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(_result.getBytes()));
			response.headers().set(Names.CONTENT_TYPE, "text/html; charset=utf-8");
			response.headers().set(Names.CONTENT_LENGTH, response.content().readableBytes());
			ctx.write(response).addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
