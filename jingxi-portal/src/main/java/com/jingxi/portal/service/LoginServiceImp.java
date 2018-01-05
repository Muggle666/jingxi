package com.jingxi.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.model.TbUser;

@Service
public class LoginServiceImp implements LoginService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_TOKEN_USER_URL}")
	private String SSO_TOKEN_USER_URL;
	@Value("${SSO_LOGIN_PAGE_URL}")
	public String SSO_LOGIN_PAGE_URL;
	@Value("${SSO_REDIRICT_URL}")
	public String SSO_REDIRICT_URL;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_TOKEN_USER_URL + token);
			// 把json转换成java对象
			JingXiResult result = JingXiResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
