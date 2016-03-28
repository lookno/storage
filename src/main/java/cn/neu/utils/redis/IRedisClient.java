package cn.neu.utils.redis;

import redis.clients.jedis.Jedis;

public interface IRedisClient {
	public Jedis getJedis();
}
