package com.sshandapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class Utils {
	public static String getStrResponse() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		InputStream inputStream;
		String strResponse = "";
		try {
			inputStream = request.getInputStream();
			String strMessage = "";
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse += strMessage;
			}
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strResponse;
	}	
}
