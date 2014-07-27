/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.majiang.base.server;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import io.majiang.entity.User;
import io.majiang.utils.ClassUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * Simple SSL chat server modified from {@link TelnetServer}.
 */
public class SecureChatServer {

    private final int port;

    public SecureChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
	    ClassUtils.scan();
	    //测试用户缓存
	    	CacheManager singletonManager = CacheManager.create();
		Cache memoryOnlyCache = new Cache("user-"+"1", 5000, false, false, 1800, 1800);
		singletonManager.addCache(memoryOnlyCache);
		Cache user = singletonManager.getCache("user-1");
		Element element = new Element("status", User.OUTSIDE_THE_ROOM);
		user.put(element);
	    
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new SecureChatServerInitializer());

            b.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8443;
        }
        new SecureChatServer(port).run();
    }
}
