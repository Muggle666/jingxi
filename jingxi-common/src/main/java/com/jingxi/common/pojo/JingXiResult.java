package com.jingxi.common.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JingXiResult {
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	// 响应业务状态
	private Integer status;
	// 响应消息
	private String msg;
	// 响应中的数据
	private Object data;

	public static JingXiResult build(Integer status, String msg, Object data) {
		return new JingXiResult(status, msg, data);
	}

	public static JingXiResult ok(Object data) {
		return new JingXiResult(data);
	}

	public static JingXiResult ok() {
		return new JingXiResult(null);
	}

	public JingXiResult(){
		
	}

	public static JingXiResult build(Integer status, String msg) {
		return new JingXiResult(status, msg, null);
	}

	public JingXiResult(Integer status, String msg, Object data) {
		this.status= status;
		this.msg=msg;
		this.data= data;
		}

	public JingXiResult(Object data) {
		this.status=200;
		this.msg= "OK";
		this.data= data;
		}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/*** 将json结果集转化为JingXiResult对象*
	 * @paramjsonData json数据
	 * @paramclazz JingXiResult中的object类型
	 * @return
	 */
	public static JingXiResult formatToPojo(String jsonData, Class<?> clazz) {
		try{
			if(clazz== null) {
				return MAPPER.readValue(jsonData, JingXiResult.class);
			}
			JsonNode jsonNode= MAPPER.readTree(jsonData);
			JsonNode data= jsonNode.get("data");
			Object obj= null;
			if(clazz!= null) {
				if(data.isObject()) {
					obj= MAPPER.readValue(data.traverse(), clazz);
					} else if(data.isTextual()) {
						obj= MAPPER.readValue(data.asText(), clazz);
						}
					}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
			} catch(Exception e) {
			return null;
			}

	}

	/***
	 * 没有object对象的转化
	 * @paramjson
	 * @return
	 */
	public static JingXiResult format(String json) {
		try {
			return MAPPER.readValue(json, JingXiResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Object是集合转化
	 * @paramjsonData json数据
	 * @paramclazz 集合中的类型
	 * @return
	 */
	public static JingXiResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}
