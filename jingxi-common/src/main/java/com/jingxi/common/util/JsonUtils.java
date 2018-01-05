package com.jingxi.common.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

public class JsonUtils {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String objectToJson(Object date) {
		try {
			String string = MAPPER.writeValueAsString(date);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 把字符串转换成list
	public static <T> List<T> jsonToList(String result, Class<T> classType) {
		try {
			JavaType type = MAPPER.getTypeFactory().constructParametricType(List.class, classType);
			List<T> list = MAPPER.readValue(result, type);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 把json转换成java对象
	@SuppressWarnings("unchecked")
	public static <T> Object jsonToPojo(String json, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}
}
