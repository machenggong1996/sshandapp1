package com.sshapp.service;

import java.util.List;

import com.sshapp.dao.PersonDao;
import com.sshapp.entities.Person;

public class PersonService {
	private PersonDao personDao;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public List<Person> getAll() {
		return personDao.getAll();
	}

	public void saveOrUpdate(Person person) {
		personDao.saveOrUpdate(person);
	}

	public Person getPersonByName(String name) {
		return personDao.getPersonByName(name);

	}
}
