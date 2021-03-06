package controller;

import java.util.Date;

import model.Campaign;
import model.Course;
import model.Schedule;
import model.Bill;
import model.Branch;
import model.Parent;
import model.PointEvent;
import model.Result;
import model.Student;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.CampaignDAO;
import dataManager.CourseDAO;
import dataManager.ScheduleDAO;
import dataManager.BillDAO;
import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.PointEventDAO;
import dataManager.ResultDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import system.Config;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class StudentCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			Parent parent = ParentDAO.getParentByNric((String) inputJson.get(Key.PARENTNRIC));
			Campaign campaign = CampaignDAO.getCampaignById((long) inputJson.get(Key.CAMPAIGNID));
			if (branch != null) {
				if (parent != null) {
					String name = (String) inputJson.get(Key.NAME);
//					String email = (String) inputJson.get(Key.EMAIL);
//					String password = (String) inputJson.get(Key.PASSWORD);
//					String passwordSalt = Encrypt.nextSalt();
//					String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
					String gender = (String) inputJson.get(Key.GENDER);
					Date birthDate = Config.BIRTHDATE.parse((String) inputJson.get(Key.BIRTHDATE));
					String homeContact = (String) inputJson.get(Key.HOMECONTACT);
					String emergencyContact = (String) inputJson.get(Key.EMERGENCYCONTACT);
					String address = (String) inputJson.get(Key.ADDRESS);
					String postalCode = (String) inputJson.get(Key.POSTALCODE);
					double latitude = (double) inputJson.get(Key.LATITUDE);
					double longitude = (double) inputJson.get(Key.LONGITUDE);
					String schoolName = (String) inputJson.get(Key.SCHOOLNAME);
					String schoolLevel = (String) inputJson.get(Key.SCHOOLLEVEL);
					String studentNric = (String) inputJson.get(Key.STUDENTNRIC);
					String profilePic = (String) inputJson.get(Key.PROFILEPIC);
					long takenDiagnostic = (long) inputJson.get(Key.TAKENDIAGNOSTIC);
					
					Student student = null;
					if (campaign != null){
						student = new Student(name, gender, birthDate, homeContact, emergencyContact, address, postalCode, latitude,
								longitude, schoolName, schoolLevel, studentNric, profilePic, takenDiagnostic, parent, branch, campaign);
					} else {
						student = new Student(name, gender, birthDate, homeContact, emergencyContact, address, postalCode, latitude,
								longitude, schoolName, schoolLevel, studentNric, profilePic, takenDiagnostic, parent, branch);
					}
					
					StudentDAO.addStudent(student);

					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, student.toJson());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get student by id
	public static JSONObject getStudentById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all student, useful while doing holistic analysis
	public static JSONObject getAllStudents() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray studentJArr = new JSONArray();
			for (Student a : StudentDAO.getAllStudents()) {
				studentJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, studentJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				String name = (String) inputJson.get(Key.NAME);
//				String email = (String) inputJson.get(Key.EMAIL);
//				String password = (String) inputJson.get(Key.PASSWORD);
//				String passwordSalt = Encrypt.nextSalt();
//				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String gender = (String) inputJson.get(Key.GENDER);
				Date birthDate = Config.BIRTHDATE.parse((String) inputJson.get(Key.BIRTHDATE));
				String homeContact = (String) inputJson.get(Key.HOMECONTACT);
				String emergencyContact = (String) inputJson.get(Key.EMERGENCYCONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String schoolName = (String) inputJson.get(Key.SCHOOLNAME);
				String schoolLevel = (String) inputJson.get(Key.SCHOOLLEVEL);
				String studentNric = (String) inputJson.get(Key.STUDENTNRIC);
				String profilePic = (String) inputJson.get(Key.PROFILEPIC);
				long takenDiagnostic = (long) inputJson.get(Key.TAKENDIAGNOSTIC);
				
//				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
//				if(branch != null){
//					student.setBranch(branch);
//				}
//				Parent parent = ParentDAO.getParentById((long) inputJson.get(Key.PARENTID));
//				if(parent != null){
//					student.setParent(parent);
//				}
				
				student.setName(name);
				student.setGender(gender);
				student.setBirthDate(birthDate);
				student.setHomeContact(homeContact);
				student.setEmergencyContact(emergencyContact);
				student.setAddress(address);
				student.setPostalCode(postalCode);
				student.setSchoolName(schoolName);
				student.setSchoolLevel(schoolLevel);
				student.setStudentNric(studentNric);
				student.setTakenDiagnostic(takenDiagnostic);
				student.setProfilePic(profilePic);
				StudentDAO.modifyStudent(student);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject deleteStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long) inputJson.get(Key.STUDENTID));
			if (student != null) {
				student.setObjStatus(Value.DELETED);
				StudentDAO.modifyStudent(student);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Register a new student
	public static JSONObject registerStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		returnJson = getStudentByNric(inputJson);
		if ((long) returnJson.get(Key.STATUS) == 0) {
			returnJson = createStudent(inputJson);
		} else {
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, Message.STUDENTNRICALREADYEXIST);
		}
		return returnJson;
	}
	
	// Get parent by student nric
	public static JSONObject getStudentByNric(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String nric = (String) inputJson.get(Key.STUDENTNRIC);
			Student student = StudentDAO.getStudentByNric(nric);
			if (student != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, student.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// login student
//	public static JSONObject loginStudent(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			String email = (String) inputJson.get(Key.EMAIL);
//			Student student = StudentDAO.getStudentByEmail(email);
//			if (student != null) {
//				String password = (String) inputJson.get(Key.PASSWORD);
//				String passwordSalt = student.getPasswordSalt();
//				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
//				String checkHash = student.getPasswordHash();
//				if (checkHash.equals(passwordHash)) {
//					returnJson.put(Key.STATUS, Value.SUCCESS);
//					returnJson.put(Key.MESSAGE, student.toJson());
//				} else {
//					returnJson.put(Key.STATUS, Value.FAIL);
//					returnJson.put(Key.MESSAGE, Message.WRONGSTUDENTPASSWORD);
//				}
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.STUDENTNOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}

	// Get students by branch
	public static JSONObject getStudentsByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray studentArr = new JSONArray();
				for (Student s : StudentDAO.getStudentsByBranch(branch)) {
					studentArr.add(s.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get students by parent
	public static JSONObject getStudentsByParent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Parent parent = ParentDAO.getParentById((long)inputJson.get(Key.PARENTID));
			if (parent != null) {
				JSONArray studentArr = new JSONArray();
				for (Student s : StudentDAO.getStudentsByParent(parent)) {
					studentArr.add(s.toJsonSimple());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, studentArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.PARENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	public static JSONObject getStudentByTeacherAndCourse(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Course course = CourseDAO.getCourseById((long) inputJson.get(Key.COURSEID));
				if(course != null){
					JSONArray studentArr = new JSONArray();
					for (Student s : StudentDAO.getStudentsByTeacherAndCourse(teacher, course)) {
						studentArr.add(s.toJsonSimple());
					}
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, studentArr);
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
}
