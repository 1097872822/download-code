package com.mwq.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.hibernate.mapping.TbTimecard;

public class BaseDao {

	// query
	protected Object queryObject(String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		Object object = query.uniqueResult();
		return object;
	}

	protected List queryList(String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		return list;
	}

	//
	public static void main(String[] args) {
		BaseDao dao = new BaseDao();
		dao.queryList("from TbAccessionForm");
	}

	// filter
	public List filterSet(Set set, String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createFilter(set, hql);
		List list = query.list();
		return list;
	}

	// save
	public boolean saveObject(Object obj) {
		boolean isSave = true;
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(obj);
			tr.commit();
		} catch (HibernateException e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	// update
	public boolean updateObject(Object obj) {
		boolean isUpdate = true;
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(obj);
			tr.commit();
		} catch (HibernateException e) {
			isUpdate = false;
			tr.rollback();
			e.printStackTrace();
		}
		return isUpdate;
	}

	// saveOrUpdate
	public boolean saveOrUpdateObject(Object obj) {
		boolean isUpdate = true;
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(obj);
			tr.commit();
		} catch (HibernateException e) {
			isUpdate = false;
			tr.rollback();
			e.printStackTrace();
		}
		return isUpdate;
	}

	// delete
	public boolean deleteObject(Object obj) {
		boolean isDelete = true;
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.delete(obj);
			tr.commit();
		} catch (HibernateException e) {
			isDelete = false;
			tr.rollback();
			e.printStackTrace();
		}
		return isDelete;
	}

	// deleteOfBatch
	public boolean deleteOfBatch(String hql) {
		boolean isDelete = true;
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tr.commit();
		} catch (HibernateException e) {
			isDelete = false;
			tr.rollback();
			e.printStackTrace();
		}
		return isDelete;
	}

}
