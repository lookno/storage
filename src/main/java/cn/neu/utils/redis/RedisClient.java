package cn.neu.utils.redis;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisClient implements IRedisClient {
	@Resource
	private JedisPool jedisPool;

	public Jedis getJedis(){
		return jedisPool.getResource();
	}
}
