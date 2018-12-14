package com.xwb.springbootxwbmain.mytest;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    public static void main(String[] args) {
        Map<String, Object> dataMap = new LinkedHashMap<>();

//        TreeMap<String, Object> dataMap = new TreeMap<>();
        dataMap.put("s","ssss");
        dataMap.put("q","ssss");
        dataMap.put("e",Integer.valueOf(1001));
        Map<String, Object> reaustMap = new LinkedHashMap<>();
        reaustMap.put("uid","ssss");
        reaustMap.put("midTransaction","ssss");
        reaustMap.put("opType",Integer.valueOf(1001));
        reaustMap.put("data", dataMap);
        Object json = JSONObject.toJSON(reaustMap);
        System.out.println(json.toString());

        Map<Integer, Object> map = new TreeMap<>();
        map.put(2, "aaaa");
        map.put(1, "dddddddddddd");
        System.out.println(JSONObject.toJSON(map));

    }

}
