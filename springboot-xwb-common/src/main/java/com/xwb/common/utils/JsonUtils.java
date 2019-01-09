package com.xwb.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

public abstract class JsonUtils {

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(obj);
		return json;
	}

	public static <T> T fromJson(String json, Class<T> claz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}

		Gson gson = new Gson();
		T obj = gson.fromJson(json, claz);
		return obj;
	}

	public static <T> T fromJson(String json, Class<T> claz, String dateFormat) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
		T obj = gson.fromJson(json, claz);
		return obj;
	}

}
