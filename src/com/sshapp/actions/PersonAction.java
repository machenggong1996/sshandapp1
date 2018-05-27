package com.sshapp.actions;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sshapp.entities.Person;
import com.sshapp.service.PersonService;

public class PersonAction extends ActionSupport implements RequestAware, ModelDriven<Person>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PersonService personService;
	private Person model;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public String list() {
		request.put("person", personService.getAll());
		return "list";
	}

	public String save() {
		personService.saveOrUpdate(model);
		System.out.println(model);
		return "save";
	}

	public String select() {

		return "select";
	}

	public String selectjump() {
	    String name = model.getName();
	    System.out.println(name);
		Person p = personService.getPersonByName(name);
		String password = p.getPassword();
		if (password.equals(model.getPassword())) {
			return "true";
		} else {
			return "false";
		}

	}

	public void prepareSave() {
		model = new Person();
	}

	public String input() {

		return INPUT;
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public Person getModel() {

		return model;
	}
}
