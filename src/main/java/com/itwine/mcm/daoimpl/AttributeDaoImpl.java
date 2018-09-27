package com.itwine.mcm.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.itwine.mcm.dao.AttributeDao;
import com.itwine.mcm.model.Attribute;

public class AttributeDaoImpl implements AttributeDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addAttribute(Attribute attribute) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(attribute);
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public Attribute getAttributeById(long id) {
		session = sessionFactory.openSession();
		Attribute attribute = (Attribute) session.load(Attribute.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return attribute;
	}

	@Override
	public List<Attribute> getAttributeList() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Attribute> attributeList = session.createCriteria(Attribute.class).list();
		tx.commit();
		session.close();
		return attributeList;
	}

	@Override
	public boolean deleteAttribute(long id) {
		session = sessionFactory.openSession();
		Object o = session.load(Attribute.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public Boolean update(long id, Attribute attribute) {
		session = sessionFactory.openSession();
		Attribute attributes = (Attribute) session.load(Attribute.class, id);
		attributes.setAttributeName(attribute.getAttributeName());
		attributes.setAttributeCode(attribute.getAttributeCode());
		attributes.setBusinessCatCode(attribute.getBusinessCatCode());
		tx = session.getTransaction();
		session.beginTransaction();
		session.update(attributes);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Attribute> searchAttribute(Attribute attribute) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Attribute> attribute1 = null;

		String hql = "SELECT a.attributeName as attributeName, a.attributeCode as attributeCode , a.businessCatCode as businessCatCode  from Attribute a where 1=1";

		if ((!StringUtils.isEmpty(attribute.getAttributeName()))) {
			hql = hql + (" and (a.attributeName = :attributeName) ");
		}

		if ((!StringUtils.isEmpty(attribute.getAttributeCode()))) {
			hql = hql + (" and (a.attributeCode = :attributeCode) ");
		}

		if ((!StringUtils.isEmpty(attribute.getBusinessCatCode()))) {
			hql = hql + (" and (a.businessCatCode = :businessCatCode) ");
		}

		/*
		 * if ((!StringUtils.isEmpty(attribute.getBusinessCategory()))) { hql = hql +
		 * (" and (a.businessCategory = :businessCategory) "); }
		 */

		Query query = session.createQuery(hql);

		if ((!StringUtils.isEmpty(attribute.getAttributeName()))) {
			query.setString("attributeName", attribute.getAttributeName());
		}
		if ((!StringUtils.isEmpty(attribute.getAttributeCode()))) {
			query.setString("attributeCode", attribute.getAttributeCode());
		}
		if ((!StringUtils.isEmpty(attribute.getBusinessCatCode()))) {
			query.setString("businessCatCode", attribute.getBusinessCatCode());
		}
		/*
		 * if ((!StringUtils.isEmpty(attribute.getBusinessCategory()))) {
		 * query.setString("businessCategory", attribute.getBusinessCategory()); }
		 */

		query.setResultTransformer(new AliasToBeanResultTransformer(Attribute.class));
		attribute1 = query.list();

		return attribute1;

	}

}
