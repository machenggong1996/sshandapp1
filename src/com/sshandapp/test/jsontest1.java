package com.sshandapp.test;

import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jsontest1 {
	public static void main(String[] args) throws JSONException {
		String data = "[{\"status\":\"false\"}]";
		JSONArray ja = JSONArray.fromObject(data);
		JSONObject jsons=ja.getJSONObject(0);	
		String result = jsons.getString("status");
		System.out.println(result);
		org.json.JSONArray oja= new org.json.JSONArray(data);
		org.json.JSONObject ojo = oja.getJSONObject(0);
		String r = ojo.getString("status");
		System.out.println(r);

	}
}
