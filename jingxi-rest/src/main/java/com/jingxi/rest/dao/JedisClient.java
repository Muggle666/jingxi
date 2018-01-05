package com.jingxi.rest.dao;

public interface JedisClient {
	String get(String key);
	String set(String key,String value);
	String hget(String hkey,String value);
	long hset(String hkey,String key,String value);
	long incr(String hkey);
	long expire(String key,int second);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey,String key);
}
