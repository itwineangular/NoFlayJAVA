package com.itwine.mcm.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.itwine.mcm.dao.MembershipDao;
import com.itwine.mcm.model.Membership;

public class MembershipDaoImpl implements MembershipDao {

	
	@Autowired
	SessionFactory sessionFactory;
	
	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addMembership(Membership membership) throws Exception {
		session=sessionFactory.openSession();
		tx=session.beginTransaction();
		session.save(membership);
		tx.commit();
		session.close();		
		return true;
	}

	@Override
	public Membership getMembershipById(long id) throws Exception {
		session=sessionFactory.openSession();
		Membership membership = (Membership) session.load(Membership.class, id);
		tx=session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		return membership;
	}

	@Override
	public List<Membership> getMembershipByList() throws Exception {
		session=sessionFactory.openSession();
		tx=session.getTransaction();
		session.beginTransaction();
		List<Membership> membership = session.createCriteria(Membership.class).list();
		tx.commit();
		session.close();
		return membership;
	}

	@Override
	public boolean updateMembership(Membership membership, long id) throws Exception {
		session=sessionFactory.openSession();
		Membership membership1 = (Membership) session.load(Membership.class, id);
		membership1.setMemberName(membership.getMemberName());
		membership1.setModifiedBy(membership.getModifiedBy());
		membership1.setModifiedDateTime(membership.getModifiedDateTime());
		tx=session.getTransaction();
		session.beginTransaction();
		session.update(membership1);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteMembership(long id) throws Exception {
		session=sessionFactory.openSession();
		Object o = session.load(Membership.class, id);
		tx=session.beginTransaction();
		session.delete(o);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Membership> searchMembership(Membership membership) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Membership> membershipSearch = null;
		String hql = "SELECT b.memberName as memberName from Membership b where 1=1";

		if ((!StringUtils.isEmpty(membership.getMemberName()))) {
			hql = hql + (" and (b.memberName = :memberName) ");
		}

			Query query = session.createQuery(hql);

			if ((!StringUtils.isEmpty(membership.getMemberName()))) {
				query.setString("memberName", membership.getMemberName());
			}

			query.setResultTransformer(new AliasToBeanResultTransformer(Membership.class));
			membershipSearch = query.list();

		return membershipSearch;
	}

}
