package com.itwine.mcm.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.itwine.mcm.model.BusinessCategory;
import com.itwine.mcm.model.BusinessEntity;
import com.itwine.mcm.model.Plans;
import com.itwine.mcm.dao.BusinessCategoryDao;

/**
 * @author SrinivasR
 *
 */

public class BusinessCategoryDaoImpl implements BusinessCategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addBusinessCategory(BusinessCategory businessCategory) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(businessCategory);
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public BusinessCategory getBusinessCategoryById(long id) throws Exception {
		session = sessionFactory.openSession();
		BusinessCategory category = (BusinessCategory) session.load(BusinessCategory.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessCategory> getBusinessCategoryList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<BusinessCategory> categoryList = session.createCriteria(BusinessCategory.class).list();
		tx.commit();
		session.close();
		return categoryList;
	}

	@Override
	public boolean deleteBusinessCategory(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(BusinessCategory.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public void update(long id, BusinessCategory BusinessCategory) {
		session = sessionFactory.openSession();
		BusinessCategory businessCategory = (BusinessCategory) session.byId(BusinessCategory.class).load(id);
		businessCategory.setBusinessCategoryCode(BusinessCategory.getBusinessCategoryCode());
		businessCategory.setBusinessCategoryName(BusinessCategory.getBusinessCategoryName());
		session.flush();

	}

	@Override
	public List<BusinessCategory> searchBusinessCategory(BusinessCategory businessCategory) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<BusinessCategory> businessCategorySearch = null;
		String hql = "SELECT b.businessCategoryName as businessCategoryName , b.businessCategoryCode as businessCategoryCode  from BusinessCategory b where 1=1";

		if ((!StringUtils.isEmpty(businessCategory.getBusinessCategoryName()))) {
			hql = hql + (" and (b.businessCategoryName = :businessCategoryName) ");
		}

		if ((!StringUtils.isEmpty(businessCategory.getBusinessCategoryCode()))) {
			hql = hql + (" and (b.businessCategoryCode = :businessCategoryCode)");
		}

		Query query = session.createQuery(hql);

		if ((!StringUtils.isEmpty(businessCategory.getBusinessCategoryName()))) {
			query.setParameter("businessCategoryName", businessCategory.getBusinessCategoryName());
		}

		if ((!StringUtils.isEmpty(businessCategory.getBusinessCategoryCode()))) {
			query.setParameter("businessCategoryCode", businessCategory.getBusinessCategoryCode());
		}

		query.setResultTransformer(new AliasToBeanResultTransformer(BusinessCategory.class));
		businessCategorySearch = query.list();

		return businessCategorySearch;
	}

}
