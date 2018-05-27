package com.sshapp.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sshandapp.status.Status;
import com.sshandapp.utils.Utils;
import com.sshapp.entities.Person;
import com.sshapp.service.PersonService;

/**
 * @author machenggong
 *
 */
public class PersonAction1 extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ModelDriven<Person>, Preparable {

	/**
	 * 与移动端对接
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PersonService personService;
	private Person model;
	Person p = new Person();

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void json() {

		List<Person> list = new ArrayList<Person>();
		Gson gson = new Gson();
		// Person p = new Person();
		// p.setId(1);
		// p.setName("mary");
		// p.setPassword("123456");
		// list.add(p);
		List<Person> list1 = personService.getAll();

		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Person>>() {
		}.getType();
		String beanListToJson = gson.toJson(list1, type);
		System.out.println("GSON-->" + beanListToJson);
		try {
			response.setCharacterEncoding("GBK");
			// response.setContentType("text/xml;charset=utf-8");
			this.response.getWriter().write(beanListToJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login() throws org.json.JSONException {
		net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(Utils.getStrResponse());
		// Integer id = obj.getInt("id");
		String name = obj.getString("name");
		String password = obj.getString("password");
		System.out.println(name + " " + password);
		try {
			Person p = personService.getPersonByName(name);
			if (p.getName().equals(name)) {
				gsonStatus("false");
			} else {

			}
		} catch (Exception e) {
			gsonStatus("true");
			// e.printStackTrace();
			// p.setId(id);
			p.setName(name);
			p.setPassword(password);
			personService.saveOrUpdate(p);
		}

	}

	public void select() {
		net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(Utils.getStrResponse());
		// Integer id = obj.getInt("id");
		String namejson = obj.getString("name");
		String passwordjson = obj.getString("password");
		String password = null;
		try {

			Person p = personService.getPersonByName(namejson);
			password = p.getPassword();// 空指针
			if (password.equals(passwordjson)) {
				gsonStatus("true");
				System.out.println("1");
			} else {
				gsonStatus("false");
				System.out.println("2");
			}
		} catch (Exception x) {
			gsonStatus("false");
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public Person getModel() {
		return model;
	}

	public void prepareSave() {
		model = new Person();
	}

	public void gsonStatus(String s) {

		List<Status> list = new ArrayList<Status>();
		Gson gson = new Gson();
		Status status = new Status();
		status.setStatus(s);
		list.add(status);
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Status>>() {
		}.getType();
		String beanListToJson = gson.toJson(list, type);
		System.out.println("GSON-->" + beanListToJson);
		try {
			response.setCharacterEncoding("GBK");
			// response.setContentType("text/xml;charset=utf-8");
			this.response.getWriter().write(beanListToJson);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
