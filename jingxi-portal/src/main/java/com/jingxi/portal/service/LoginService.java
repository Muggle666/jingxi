package com.jingxi.portal.service;

import com.jingxi.model.TbUser;

public interface LoginService {

	TbUser getUserByToken(String token);
}
