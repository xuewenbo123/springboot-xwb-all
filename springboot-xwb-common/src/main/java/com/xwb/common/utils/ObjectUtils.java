package com.xwb.common.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectUtils {

	public static Map<String, Object> toMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Field[] fields = FieldUtils.getAllFields(obj.getClass());
		Map<String, Object> resultMap = new LinkedHashMap<>();
		for (Field field : fields) {
			if(Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			try {
				resultMap.put(field.getName(), FieldUtils.readField(field, obj, true));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMap;
	}

}
