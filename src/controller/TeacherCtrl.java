package controller;

import model.Branch;
import model.Parent;
import model.PointEvent;
import model.Salary;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.ParentDAO;
import dataManager.PointEventDAO;
import dataManager.SalaryDAO;
import dataManager.TeacherDAO;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class TeacherCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String password = (String) inputJson.get(Key.PASSWORD);
				
				String passwordSalt = Encrypt.nextSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				String qualification = (String) inputJson.get(Key.QUALIFICATION);
				String teacherNric = (String) inputJson.get(Key.TEACHERNRIC);

				Teacher teacher = new Teacher(name, email, passwordSalt, passwordHash, contact, address, qualification, teacherNric, branch);
				TeacherDAO.addTeacher(teacher);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
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

	// Get teacher by id
	public static JSONObject getTeacherById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			long teacherId = (long) inputJson.get(Key.TEACHERID);
			Teacher teacher = TeacherDAO.getTeacherById(teacherId);
			if (teacher != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get all teacher
	public static JSONObject getAllTeachers() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray teacherJArr = new JSONArray();
			for (Teacher a : TeacherDAO.getAllTeachers()) {
				teacherJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, teacherJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				String name = (String) inputJson.get(Key.NAME);
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String address = (String) inputJson.get(Key.ADDRESS);
				String qualification = (String) inputJson.get(Key.QUALIFICATION);
				String teacherNric = (String) inputJson.get(Key.TEACHERNRIC);

				teacher.setName(name);
				teacher.setEmail(email);
				teacher.setContact(contact);
				teacher.setAddress(address);
				teacher.setQualification(qualification);
				teacher.setTeacherNric(teacherNric);

				TeacherDAO.modifyTeacher(teacher);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	public static JSONObject deleteTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long) inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				teacher.setObjStatus(Value.DELETED);
				TeacherDAO.modifyTeacher(teacher);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// features
	// Register a new Teacher
	public static JSONObject registerTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		returnJson = getTeacherByEmail(inputJson);
		if ((long) returnJson.get(Key.STATUS) == 0) {
			returnJson = getTeacherByNric(inputJson);
			if ((long) returnJson.get(Key.STATUS) == 0) {
				returnJson = createTeacher(inputJson);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNRICALREADYEXIST);
			}
		} else {
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, Message.EMAILALREADYEXIST);
		}
		return returnJson;
	}

	// Get teacher by email
	public static JSONObject getTeacherByEmail(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Teacher teacher = TeacherDAO.getTeacherByEmail(email);
			if (teacher != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// Get parent by parent nric
	public static JSONObject getTeacherByNric(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String nric = (String) inputJson.get(Key.TEACHERNRIC);
			Teacher teacher = TeacherDAO.getTeacherByNric(nric);
			if (teacher != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	
	// login Teacher
	public static JSONObject loginTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			String email = (String) inputJson.get(Key.EMAIL);
			Teacher teacher = TeacherDAO.getTeacherByEmail(email);
			if (teacher != null) {
				String password = (String) inputJson.get(Key.PASSWORD);
				String passwordSalt = teacher.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String checkHash = teacher.getPasswordHash();
				if (checkHash.equals(passwordHash)) {
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, teacher.toJsonStrong());
				} else {
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGTEACHERPASSWORD);
				}
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.TEACHERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get teachers by branch
	public static JSONObject getTeachersByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray branchArr = new JSONArray();
				for (Teacher t : TeacherDAO.getTeachersByBranch(branch)) {
					branchArr.add(t.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchArr);
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

	// Get teacher by course
//	public static JSONObject getTeacherByCourse(JSONObject inputJson) {
//		JSONObject returnJson = new JSONObject();
//		try {
//			Course course = CourseDAO.getCourseById((long)inputJson.get(Key.COURSEID));
//			if (course != null) {
//				Teacher teacher = course.getTeacher();
//				returnJson.put(Key.STATUS, Value.SUCCESS);
//				returnJson.put(Key.MESSAGE, teacher.toJson());
//			} else {
//				returnJson.put(Key.STATUS, Value.FAIL);
//				returnJson.put(Key.MESSAGE, Message.COURSENOTEXIST);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			returnJson.put(Key.STATUS, Value.FAIL);
//			returnJson.put(Key.MESSAGE, e);
//		}
//		return returnJson;
//	}
	
	// Get teacher by salary
	public static JSONObject getTeacherBySalary(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Salary salary = SalaryDAO.getSalaryById((long)inputJson.get(Key.SALARYID));
			if (salary != null) {
				Teacher teacher = salary.getTeacher();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.SALARYNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	// Get teacher by pointEvent
	public static JSONObject getTeacherByPointEvent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			PointEvent pointEvent = PointEventDAO.getPointEventById((long)inputJson.get(Key.POINTEVENTID));
			if (pointEvent != null) {
				Teacher teacher = pointEvent.getTeacher();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, teacher.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.POINTEVENTNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

}
