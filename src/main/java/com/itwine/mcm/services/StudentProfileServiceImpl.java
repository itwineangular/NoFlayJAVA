package com.itwine.mcm.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwine.mcm.controller.EmailController;
import com.itwine.mcm.dao.StudentProfileDao;
import com.itwine.mcm.model.StudentProfile;
import com.itwine.mcm.vo.StudentVo;


public class StudentProfileServiceImpl implements StudentProfileService{

	@Autowired
	StudentProfileDao studentProfileDao;
	
	
	
	@Autowired
	EmailController emailController;


	@Override
	public boolean addStudentProfile(StudentProfile studentProfile) throws Exception {		
		return studentProfileDao.addStudent(studentProfile);
	}

	@Override
	public StudentProfile getStudentById(long id) throws Exception {		
		return studentProfileDao.getStudentById(id);
	}

	@Override
	public boolean deleteStudentById(long id) throws Exception {		
		return studentProfileDao.deleteStudentById(id);
	}

	@Override
	public List<StudentProfile> getAllStudentList() throws Exception {	
		return studentProfileDao.getStudentProfileByList();
	}

	@Override
	public boolean updateStudent(StudentProfile studentProfile, long id) throws Exception {		
		return studentProfileDao.updateStudent(studentProfile,id);
	}

	@Override
	public List<StudentVo> getStudentExcelData(String fileName) throws IOException {


		List<StudentVo> studentVoList = new ArrayList<StudentVo>();
		FileInputStream inputStream = new FileInputStream(new File(fileName));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		if(iterator.hasNext()) {
			iterator.next();
		}
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			StudentVo studentVo = new StudentVo();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				nextCell.setCellType(CellType.STRING);
				switch (columnIndex) {
				case 0:
					studentVo.setStdName(nextCell.getStringCellValue());
					break;
				case 1:
					studentVo.setGender(nextCell.getStringCellValue());
					break;
				case 2:
					studentVo.setAddressOne(nextCell.getStringCellValue());
					break;
				case 3:
					studentVo.setAddressTwo(nextCell.getStringCellValue());
					break;
				case 4:
					studentVo.setCity(nextCell.getStringCellValue());
					break;
				case 5:
					studentVo.setCourse(nextCell.getStringCellValue());
					break;
				case 6:
					studentVo.setZipcode(nextCell.getStringCellValue());
					break;
				case 7:
					studentVo.setCountryName(nextCell.getStringCellValue());
					break;
				case 8:
					studentVo.setParentPhone(nextCell.getStringCellValue());
					break;
				case 9:
					studentVo.setStdEmail(nextCell.getStringCellValue());
					break;
				case 10:
					studentVo.setStdAdmissionNumber(nextCell.getStringCellValue());
					break;
				case 11:
					studentVo.setInstitutionName(nextCell.getStringCellValue());
					break;
				case 12:
					studentVo.setState(nextCell.getStringCellValue());
					break;
				case 13:
					studentVo.setPlan(nextCell.getStringCellValue());
					break;
				case 14:
					studentVo.setCourseCategory(nextCell.getStringCellValue());
					break;
				case 15:
					studentVo.setStatus(nextCell.getStringCellValue());
					break;
				}
			}
			studentVoList.add(studentVo);
		}

		workbook.close();
		inputStream.close();

		return studentVoList;
	}

	@Override
	public List<StudentVo> uploadStudentExcelData(List<StudentVo> studentVoList) {
		List<StudentVo> exceptionStudentVos = new ArrayList<StudentVo>();
		for(StudentVo studentVo : studentVoList) {
			StudentProfile studentProfile = new StudentProfile();
			studentProfile.setStdName(studentVo.getStdName());
			studentProfile.setStdPhone(studentVo.getParentPhone());
			studentProfile.setStdEmail(studentVo.getStdEmail());
			studentProfile.setStdAdmissionNumber(studentVo.getStdAdmissionNumber());
			studentProfile.setCourse(studentVo.getCourse());
			studentProfile.setCourseCategory(studentVo.getCourseCategory());
			studentProfile.setInstitutionName(studentVo.getInstitutionName());
			studentProfile.setStatus(studentVo.getStatus());
			studentProfile.setPlan(studentVo.getPlan());
			
		
			emailController.sendEmailToClient("message from "+studentVo.getInstitutionName(), "you are subscribed to this -plan" +"-"+studentVo.getPlan(), studentVo.getStdEmail());

			try {
				this.addStudentProfile(studentProfile);
			} catch (Exception e) {
				exceptionStudentVos.add(studentVo);
				e.printStackTrace();
			}
		}
		return exceptionStudentVos;
	}

	@Override
	public List<StudentProfile> searchStudentProfile(StudentProfile studentProfile) throws Exception {
		// TODO Auto-generated method stub
		return studentProfileDao.searchStudentProfile(studentProfile);
	}
}
