package com.itwine.mcm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * @author SrinivasR
 *
 */

@Entity(name = "CourseCategory")
@Table(name = "coursecategory")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CourseCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "categoryId")
	private long categoryId;

	@Column(name = "categoryCode")
	private String categoryCode;

	@Column(name = "categoryName")
	private String categoryName;

	@Column(name = "Status")
	private String status;

	@Column(name = "createDateTime")
	private Date creationDate = new Date();

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "modifiedDateTime")
	private Date modifiedDate = new Date();

	@Column(name = "modifiedBy")
	private String modifiedBy;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "institution_id")
	private InstitutionProfile institution;

	@JsonManagedReference
	@OneToMany(mappedBy = "courseCategory",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CourseProfile> courseProfile;
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public InstitutionProfile getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionProfile institution) {
		this.institution = institution;
	}

	public List<CourseProfile> getCourseProfile() {
		return courseProfile;
	}


	public void setCourseProfile(List<CourseProfile> courseProfile) {
		this.courseProfile = courseProfile;
	}
	
	  public void addCourseProfile(CourseProfile tempcourse) {
		  tempcourse.setCourseCategory(this);
		        getCourseProfile().add(tempcourse);
		    }
	  }
