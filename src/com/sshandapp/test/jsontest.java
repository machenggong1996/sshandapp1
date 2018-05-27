package com.sshandapp.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class jsontest {

	private static String doPost(String url, JSONObject param) {
		HttpPost httpPost = null;
		String result = null;

		try {
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			if (param != null) {
				StringEntity se = new StringEntity(param.toString(), "utf-8");
				httpPost.setEntity(se); // post方法中，加入json数据
				httpPost.setHeader("Content-Type", "application/json");
			}

			HttpResponse response = client.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "utf-8");
				}
			}

		} catch (Exception ex) {
			System.out.println("报错" + ex);
		}
		return result;
	}

	private static JSONObject createJson() throws JSONException {
		JSONObject obj = new JSONObject();
		//obj.put("id", 21);
		obj.put("name", "machenggong11111234");
		obj.put("password", "12233578901");
		System.out.println(obj.toString());
		return obj;
	}

	public static void main(String[] args) throws JSONException {
		//System.out.println(doPost("http://localhost:8080/sshandapp1/loginjson.action", createJson()));
		System.out.println(doPost("http://localhost:8080/sshandapp1/selectjson.action", createJson()));
		//doPost("http://192.168.191.1:8080/sshandapp1/selectjson.action", createJson());
	}
}
