package com.jingxi.rest.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSingle(){
		//创建一个Jedis对象
		Jedis jedis = new Jedis("192.168.137.147",6379);
		//调用jedis方法，方法名称和redis一致
		jedis.set("key1","Jedis Test");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
	}

	@Test
	public void testJedisPool(){
		//创建Jedis连接池
		JedisPool pool = new JedisPool("192.168.137.147",6379); 
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext= 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool= (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis= pool.getResource();
		String string= jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();}
}
