package com.mifi.main.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;


@Sharable
public class HttpCoreServerInitializer extends ChannelInitializer<SocketChannel> {

	private final SslContext sslCtx;
    
    public HttpCoreServerInitializer() {
    	sslCtx=null;
    }
    
    public HttpCoreServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (null!=sslCtx) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new HttpServerCodec());
        //完整参数封装
        p.addLast("aggregator", new HttpObjectAggregator(1048576));  
        p.addLast(new HttpCoreServerHandler());
    }
}
