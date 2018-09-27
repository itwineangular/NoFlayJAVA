package com.itwine.mcm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;




@Entity(name="CourseProfile")
@Table(name="courseprofile")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@SuppressWarnings("serial")
public class CourseProfile implements Serializable, Cloneable{
	
	@Id
	@GeneratedValue
	@Column(name="course_Id")
	private long courseId;
	
	@Column(name="courseName")
	private String courseName;	
	
	@Column(name="courseCode")
	private String courseCode;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="categoryName")
	private String categoryName;	
	
	@Column(name="createdBy")
	private String createdBy;
	
	@Column(name="createDateTime")
	private Date createDateTime = new Date();
	
	@Column(name="modifiedBy")
	private String modifiedBy;
	
	@Column(name="modifiedDateTime")
	private Date modifiedDateTime = new Date();
	
    @JsonBackReference
	@JoinColumn(name="categoryId")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private CourseCategory courseCategory;
	
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}	
	
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}

	
	/*public void addCourseCategory(CourseCategory tempcategory) {

		if (getCourseCategory() == null) {
			setCourseCategory(new HashSet());
		}
		getCourseCategory().add(tempcategory);
	}*/

	public Object clone() throws CloneNotSupportedException 
	{ 
		return super.clone(); 
	} 
}