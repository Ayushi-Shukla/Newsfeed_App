package com.news.dao;


import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.news.entity.*;

@Component
public class RegisterDao {
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterDao.class);

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.sessionFactory = emFactory.unwrap(SessionFactory.class);
	}

	public void save(AnalystDetails analystDetails) {
		LOGGER.info("save() in DAO is called!");
		Session session = sessionFactory.openSession();
		LOGGER.debug("User details {}", analystDetails);
		session.save(analystDetails);
		session.close();
		LOGGER.info("save() execution is completed!");
	}
	
}

