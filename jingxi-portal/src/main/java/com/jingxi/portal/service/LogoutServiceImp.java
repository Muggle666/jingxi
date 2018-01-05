package com.jingxi.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.portal.pojo.JedisClient;

@Service
public class LogoutServiceImp implements LogoutService {
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public void logout() {
		Long l = jedisClient.del(REDIS_SESSION_KEY);
		System.out.println(l);
		System.out.println("LogoutServiceImp--logout()");
	}

}
