package com.itwine.mcm.services;

import java.io.IOException;
import java.util.List;

import com.itwine.mcm.model.StudentProfile;
import com.itwine.mcm.vo.StudentVo;



public interface StudentProfileService {
	
	public boolean addStudentProfile(StudentProfile studentProfile) throws Exception;
	
	public StudentProfile getStudentById(long id) throws Exception;
	
	public List<StudentProfile> getAllStudentList() throws Exception;
	
	public boolean deleteStudentById(long id) throws Exception;
	
	public boolean updateStudent(StudentProfile studentProfile,long id) throws Exception;


	List<StudentVo> getStudentExcelData(String fileName) throws IOException;

	public List<StudentVo> uploadStudentExcelData(List<StudentVo> studentVoList);
	
	public List<StudentProfile>searchStudentProfile (StudentProfile studentProfile)throws Exception;

}
