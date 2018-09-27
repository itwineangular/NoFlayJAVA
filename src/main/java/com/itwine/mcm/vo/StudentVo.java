package com.itwine.mcm.vo;

import java.io.Serializable;

public class StudentVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
   private String stdName;
	private String gender;
	private String addressOne;
	private String addressTwo;
	private String city;
	private String course;
	private String zipcode;
	private String countryName;
	private String parentPhone;
	private String stdEmail;
	private String stdAdmissionNumber;
	private String institutionName;
	private String state;
	private String plan;
	private String courseCategory;
	private String status;
	/**
	 * @return the name
	 */
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getStdEmail() {
		return stdEmail;
	}
	public void setStdEmail(String stdEmail) {
		this.stdEmail = stdEmail;
	}
	public String getStdAdmissionNumber() {
		return stdAdmissionNumber;
	}
	public void setStdAdmissionNumber(String stdAdmissionNumber) {
		this.stdAdmissionNumber = stdAdmissionNumber;
	}
	/**
	 * @return the addressOne
	 */
	public String getAddressOne() {
		return addressOne;
	}
	/**
	 * @param addressOne the addressOne to set
	 */
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	/**
	 * @return the addressTwo
	 */
	public String getAddressTwo() {
		return addressTwo;
	}
	/**
	 * @param addressTwo the addressTwo to set
	 */
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * @return the zipCode
	 */

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the parentPhone
	 */
	public String getParentPhone() {
		return parentPhone;
	}
	/**
	 * @param parentPhone the parentPhone to set
	 */
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	/**
	 * @return the email
	 */
	
	/**
	 * @return the institutionName
	 */
	public String getInstitutionName() {
		return institutionName;
	}
	/**
	 * @param institutionName the institutionName to set
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}
	/**
	 * @return the courseCategory
	 */
	public String getCourseCategory() {
		return courseCategory;
	}
	/**
	 * @param courseCategory the courseCategory to set
	 */
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
