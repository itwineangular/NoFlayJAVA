package com.itwine.mcm.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.itwine.mcm.dao.CourseProfileDao;
import com.itwine.mcm.model.CourseCategory;
import com.itwine.mcm.model.CourseProfile;
import com.itwine.mcm.model.InstitutionProfile;

public class CourseProfileDaoImpl implements CourseProfileDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addCourse(CourseProfile courseProfile) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	/*	InstitutionProfile institutionProfile = new InstitutionProfile();
		courseProfile.setInstitution(institutionProfile);

		CourseCategory tempCategory =new CourseCategory();
		tempCourse.setInstitution(institutionProfile);
		institutionProfile.addCourseProfile(tempCourse);
		if(courseProfile.getCourseCategory()!=null){
		for(CourseCategory tempCourse1:courseProfile.getCourseCategory()){
			tempCategory.setCategoryName(tempCourse1.getCategoryName());
			courseProfile.setCategoryName(tempCourse1.getCategoryName());
			tempCategory.setCourseProfile(courseProfile);
			session.save(tempCategory);
		}
		}
		else{*/
		session.save(courseProfile);
		
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public CourseProfile getCourseById(long id) throws Exception {
		session = sessionFactory.openSession();
		CourseProfile courseProfile = (CourseProfile) session.load(CourseProfile.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		return courseProfile;
	}

	@Override
	public List<CourseProfile> getCourseByList() throws Exception {
session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<CourseProfile> courseProfile = session.createCriteria(CourseProfile.class).list();
		List<CourseProfile> dsdsd = new ArrayList<CourseProfile>() ;
		for(CourseProfile dfdfd: courseProfile){
		
			dfdfd.setCourseCategory(dfdfd.getCourseCategory());
			dsdsd.add(dfdfd);
			
		}
		
		courseProfile.addAll(dsdsd);
		tx.commit();
		session.close();
		return dsdsd;
	}

	@Override
	public boolean updateCourse(CourseProfile courseProfile, long id) throws Exception {
		session = sessionFactory.openSession();
		CourseProfile course = (CourseProfile) session.load(CourseProfile.class, id);
		course.setCourseName(courseProfile.getCourseName());
		course.setCourseCode(courseProfile.getCourseCode());
		
		course.setDuration(courseProfile.getDuration());
		/*course.setCourseCategory(courseProfile.getCourseCategory());*/
		course.setModifiedBy(courseProfile.getModifiedBy());
		course.setModifiedDateTime(courseProfile.getModifiedDateTime());
		tx = session.getTransaction();
		session.beginTransaction();
		session.update(course);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteCourse(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(CourseProfile.class, id);
		tx = session.beginTransaction();
		session.delete(o);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<CourseProfile> searchCourseProfile(CourseProfile courseProfile) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<CourseProfile> courseProfile1=null;
		
		 String hql = "SELECT cp.courseName as courseName, cp.courseCode as courseCode, cp.categoryName as categoryName, cp.duration as duration from CourseProfile cp where 1=1";
		
		 if ((!StringUtils.isEmpty(courseProfile.getCourseName()))) {
		        hql = hql + (" and (cp.courseName = :courseName) ");
		      }
		 
		 
		 if ((!StringUtils.isEmpty(courseProfile.getCourseCode()))) {
			  hql = hql + (" and (cp.courseCode = :courseCode) ");
		      }
		 
		 
		 if ((!StringUtils.isEmpty(courseProfile.getCategoryName()))) {
			 hql = hql + (" and (cp.categoryName = :categoryName) ");
		      }
		 
		 if ((!StringUtils.isEmpty(courseProfile.getDuration()))) {
			 hql = hql + (" and (cp.duration = :duration) ");
		      }
		
		 Query query = session.createQuery(hql);
		 
		      if ((!StringUtils.isEmpty(courseProfile.getCourseName()))) {
		        query.setString("courseName", courseProfile.getCourseName());
		      }
		      if ((!StringUtils.isEmpty(courseProfile.getCourseCode()))) {
			        query.setString("courseCode", courseProfile.getCourseCode());
			      }
		      if ((!StringUtils.isEmpty(courseProfile.getCategoryName()))) {
			        query.setString("categoryName", courseProfile.getCategoryName());
			      }
		      if ((!StringUtils.isEmpty(courseProfile.getDuration()))) {
			        query.setString("duration", courseProfile.getDuration());
			      }
		      
		      query.setResultTransformer(new AliasToBeanResultTransformer(CourseProfile.class));
		      courseProfile1 = query.list();
		
		return courseProfile1;
	}

}
