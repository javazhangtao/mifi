package com.mifi.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
	public Jedis getJedis() {
		return jedis;
	}
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	public RedisClient() {
		initialPool();
		jedis = jedisPool.getResource();
	}
	
	public void initialPool(){
		// 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxIdle(5); 
        config.setMaxWaitMillis(2000000l);
        config.setTestOnBorrow(false); 
        
//        jedisPool = new JedisPool(poolConfig, host, port, timeout)
	}
}
