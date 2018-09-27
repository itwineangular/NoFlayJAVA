package com.itwine.mcm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itwine.mcm.vo.StudentVo;
import com.itwine.mcm.model.Status;
import com.itwine.mcm.model.StudentProfile;
import com.itwine.mcm.services.StudentProfileService;


@RestController
@Controller
@RequestMapping("/students")
public class StudentRestController {

	private static final String STUDENT_PROFILE_XLSX = "STUDENT_PROFILE.xlsx";

	@Autowired
	StudentProfileService studentProfileService;
	
	@Autowired
	ServletContext contex;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addStudent(@RequestBody StudentProfile studentProfile){
		
		try{
			/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(studentProfile.getStdPassword());
            studentProfile.setStdPassword(encodedPassword);*/
            studentProfileService.addStudentProfile(studentProfile);
            return new Status(1, "Students inserted Successfully");
		}catch(Exception e){
			return new Status(0, e.toString());
		}		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody StudentProfile getStudentById(@PathVariable long id){
		
		StudentProfile studentProfile = null;
		try{
			studentProfile = studentProfileService.getStudentById(id);
			}catch(Exception e){
			e.printStackTrace();
		}
		return studentProfile;		
	}
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public @ResponseBody List<StudentProfile> getStudentList(){
		
		List<StudentProfile> studentList = null;
		try{
		 studentList = studentProfileService.getAllStudentList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentList;
	}
	
	@RequestMapping(value = "delete/{id}", method= RequestMethod.GET)
	public @ResponseBody Status deleteStudent(@PathVariable("id") long id){
		
		try{
		studentProfileService.deleteStudentById(id);
		return new Status(1, "Deleted Student Successfully...");
		}catch(Exception e){
			return new Status(0, e.toString());
		}
	}	
	
	@RequestMapping(value = "update/{id}", method= RequestMethod.PUT)
	public @ResponseBody Status updateStudent(@PathVariable("id") long id, @RequestBody StudentProfile studentProfile){
		
		try{
		studentProfileService.updateStudent(studentProfile,id);
		return new Status(1, "Updated Student details Successfully...");
		}catch(Exception e){
			return new Status(0, e.toString());
		}
	}	
	
	
	@RequestMapping(value = "/imageUploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandlers(@RequestParam("name") String name,	@RequestParam("file") MultipartFile file) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + STUDENT_PROFILE_XLSX);			
				BufferedOutputStream stream = new BufferedOutputStream(	new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return "You   uploaded file=" + name;				
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}			
		} else {
			return "You failed to upload " + name	+ " because the file was empty.";
		}
	}
	
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,	@RequestParam("file") MultipartFile file) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + STUDENT_PROFILE_XLSX);			
				BufferedOutputStream stream = new BufferedOutputStream(	new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return "You   uploaded file=" + name;				
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}			
		} else {
			return "You failed to upload " + name	+ " because the file was empty.";
		}
	}
	
	@RequestMapping(value = "/viewExcelData", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<StudentVo> getStudentExcelData(){
		
		List<StudentVo> studentVoList = null;
		
		try {
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			studentVoList = studentProfileService.getStudentExcelData(rootPath + File.separator + "tmpFiles"+ File.separator + STUDENT_PROFILE_XLSX);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return studentVoList;
	}	
	
	@RequestMapping(value = "/uploadStudentProfile", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<StudentVo> uploadStudentProfile(@RequestBody List<StudentVo> studentVoList){
		List<StudentVo> exceptionStudentVos = studentProfileService.uploadStudentExcelData(studentVoList);
		return exceptionStudentVos;
	}	
	
	@RequestMapping(value="/searchList", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<StudentProfile> searchStudentProfile(@RequestBody StudentProfile studentProfile){
		List<StudentProfile> studentProfileList=null;
		try {
			studentProfileList = studentProfileService.searchStudentProfile(studentProfile);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return studentProfileList;
	}
	
}
