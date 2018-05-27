package com.sshapp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sshapp.entities.Person;

public class PersonDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public List<Person> getAll() {
		String hql = "FROM Person";
		return getSession().createQuery(hql).list();
	}

	public void saveOrUpdate(Person person) {
		getSession().saveOrUpdate(person);
	}

	public Person getPersonByName(String name) {
		String hql = "FROM Person p WHERE p.name = ?";
		Query query = getSession().createQuery(hql).setString(0, name);
		return (Person) query.uniqueResult();
	}
}
