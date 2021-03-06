package controller;

import model.Admin;
import model.Branch;
import model.BranchManager;
import model.Classroom;
import model.Student;
import model.Teacher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AdminDAO;
import dataManager.BranchDAO;
import dataManager.BranchManagerDAO;
import dataManager.ClassroomDAO;
import dataManager.StudentDAO;
import dataManager.TeacherDAO;
import system.Key;
import system.Message;
import system.Value;

public class BranchCtrl {
	/**
	 * CRUD
	 */
	public static JSONObject createBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			if (admin != null) {
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				String contact = (String) inputJson.get(Key.CONTACT);
				double latitude = Double.valueOf((String) inputJson.get(Key.LATITUDE));
				double longitude = Double.valueOf((String) inputJson.get(Key.LONGITUDE));
				
				Branch branch = new Branch(name, location, postalCode, latitude, longitude, contact, admin);
				BranchDAO.addBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}

		return returnJson;
	}

	// Get branch by id
	public static JSONObject getBranchById(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
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

	// Get all branch
	public static JSONObject getAllBranches() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray branchJArr = new JSONArray();
			for (Branch a : BranchDAO.getAllBranches()) {
				branchJArr.add(a.toJson());
			}
			returnJson.put(Key.STATUS, Value.SUCCESS);
			returnJson.put(Key.MESSAGE, branchJArr);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	public static JSONObject updateBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();

		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String name = (String) inputJson.get(Key.NAME);
				String location = (String) inputJson.get(Key.LOCATION);
				String postalCode = (String) inputJson.get(Key.POSTALCODE);
				double latitude = Double.valueOf((String) inputJson.get(Key.LATITUDE));
				double longitude = Double.valueOf((String) inputJson.get(Key.LONGITUDE));
				String contact = (String) inputJson.get(Key.CONTACT);
				
				branch.setName(name);
				branch.setLocation(location);
				branch.setPostalCode(postalCode);
				branch.setContact(contact);
				branch.setLatitude(latitude);
				branch.setLongitude(longitude);

				BranchDAO.modifyBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
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

	public static JSONObject deleteBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				branch.setObjStatus(Value.DELETED);
				BranchDAO.modifyBranch(branch);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
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

	// features
	// Get branches by admin
	public static JSONObject getBranchesByAdmin(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Admin admin = AdminDAO.getAdminById((long) inputJson.get(Key.ADMINID));
			if (admin != null) {
				JSONArray branchArr = new JSONArray();
				for (Branch branch : BranchDAO.getBranchesByAdmin(admin)) {
					branchArr.add(branch.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchArr);
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.ADMINNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get branch by student
	public static JSONObject getBranchByStudent(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Student student = StudentDAO.getStudentById((long)inputJson.get(Key.STUDENTID));
			if (student != null) {
				Branch branch = student.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
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

	// Get branch by branch manager
	public static JSONObject getBranchByBranchManager(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			BranchManager branchManager = BranchManagerDAO.getBranchManagerById((long)inputJson.get(Key.BRANCHMANAGERID));
			if (branchManager != null) {
				Branch branch = branchManager.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHMANAGERNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}

	// Get branch by classroom
	public static JSONObject getBranchByClassroom(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Classroom classroom = ClassroomDAO.getClassroomById((long)inputJson.get(Key.CLASSROOMID));
			if (classroom != null) {
				Branch branch = classroom.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
			} else {
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.CLASSROOMNOTEXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
	// Get branch by teacher
	public static JSONObject getBranchByTeacher(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Teacher teacher = TeacherDAO.getTeacherById((long)inputJson.get(Key.TEACHERID));
			if (teacher != null) {
				Branch branch = teacher.getBranch();
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branch.toJson());
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

}
