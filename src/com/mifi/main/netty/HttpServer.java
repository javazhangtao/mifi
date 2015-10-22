package com.mifi.main.netty;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import org.apache.log4j.Logger;

import com.mifi.common.Dictionary;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class HttpServer {

	Logger logger=Logger.getLogger(HttpServer.class);
	static final boolean SSL = System.getProperty("ssl") != null;
	static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : Dictionary.getProperty("HTTP_PORT")));

	public void start() throws CertificateException, SSLException, InterruptedException {
		@SuppressWarnings("unused")
		final SslContext sslCtx;
		if (SSL) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
		} else {
			sslCtx = null;
		}

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new HttpCoreServerInitializer());
			// 连接超时时间，默认值60秒，单位：秒
			b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,10000);
			b.option(ChannelOption.SO_TIMEOUT,10000);
			Channel ch = b.bind(PORT).sync().channel();
			logger.info("HTTP SERVER STARTUP ["+(SSL ? "https" : "http") + "://127.0.0.1:" + PORT + '/'+"]");
			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
