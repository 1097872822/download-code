package com.mwq.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;

	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	static {
		try {
			Configuration config = new Configuration().configure();
			sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("------在加载Hibernate配置文件时抛出异常，内容如下：");
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null || session.isOpen()) {
			session.close();
		}
	}

}