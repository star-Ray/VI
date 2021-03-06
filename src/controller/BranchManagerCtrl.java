package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.BranchDAO;
import dataManager.BranchManagerDAO;
import model.Branch;
import model.BranchManager;
import system.Encrypt;
import system.Key;
import system.Message;
import system.Value;

public class BranchManagerCtrl {

	/**
	 * CRUD
	 */
	public static JSONObject createBranchManager(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				String email = (String) inputJson.get(Key.EMAIL);
				String password = (String) inputJson.get(Key.PASSWORD);
				String contact = (String) inputJson.get(Key.CONTACT);
				String branchManagerNric = (String) inputJson.get(Key.BRANCHMANAGERNRIC);

				String passwordSalt = Encrypt.nextSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);

				BranchManager branchManager = new BranchManager(email, passwordSalt, passwordHash,
						contact, branchManagerNric, branch);
				BranchManagerDAO.addBranchManager(branchManager);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchManager.toJson());
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

	// Get branch managers by id
	public static JSONObject getBranchManagerId(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			BranchManager bm = BranchManagerDAO.getBranchManagerById((long) inputJson.get(Key.BRANCHMANAGERID));
			if (bm != null) {
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, bm.toJson());
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

	// Get all branch managers
	public static JSONObject getAllBranchManagers() {
		JSONObject returnJson = new JSONObject();
		try {
			JSONArray branchJArr = new JSONArray();
			for (BranchManager a : BranchManagerDAO.getAllBranchManagers()) {
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

	public static JSONObject updateBranchManager(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			BranchManager branchManager = BranchManagerDAO.getBranchManagerById((long) inputJson.get(Key.BRANCHMANAGERID));
			if (branchManager != null) {
				String email = (String) inputJson.get(Key.EMAIL);
				String contact = (String) inputJson.get(Key.CONTACT);
				String branchManagerNric = (String) inputJson.get(Key.BRANCHMANAGERNRIC);
				Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));

				branchManager.setEmail(email);
				branchManager.setContact(contact);
				branchManager.setBranchManagerNric(branchManagerNric);
				branchManager.setBranch(branch);

				BranchManagerDAO.modifyBranchManager(branchManager);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchManager.toJson());
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
			BranchManager branchManager = BranchManagerDAO.getBranchManagerById((long) inputJson.get(Key.BRANCHMANAGERID));
			if (branchManager != null) {
				branchManager.setObjStatus(Value.DELETED);
				BranchManagerDAO.modifyBranchManager(branchManager);

				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchManager.toJson());
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

	// features
	// Get branch managers by branch
	public static JSONObject getBranchManagersByBranch(JSONObject inputJson) {
		JSONObject returnJson = new JSONObject();
		try {
			Branch branch = BranchDAO.getBranchById((long) inputJson.get(Key.BRANCHID));
			if (branch != null) {
				JSONArray branchManagerArr = new JSONArray();
				for (BranchManager bm : BranchManagerDAO.getBranchManagersByBranch(branch)) {
					branchManagerArr.add(bm.toJson());
				}
				returnJson.put(Key.STATUS, Value.SUCCESS);
				returnJson.put(Key.MESSAGE, branchManagerArr);
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
	//login
	public static JSONObject loginBranchManager(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String email = (String)inputJson.get(Key.EMAIL);
			BranchManager branchManager = BranchManagerDAO.getBranchManagerByEmail(email);
			if(branchManager != null){
				String password = (String)inputJson.get(Key.PASSWORD);
				String passwordSalt = branchManager.getPasswordSalt();
				String passwordHash = Encrypt.generateSaltedHash(password, passwordSalt);
				String checkHash = branchManager.getPasswordHash();
				if(checkHash.equals(passwordHash)){
					returnJson.put(Key.STATUS, Value.SUCCESS);
					returnJson.put(Key.MESSAGE, branchManager.toJson());
				}else{
					returnJson.put(Key.STATUS, Value.FAIL);
					returnJson.put(Key.MESSAGE, Message.WRONGBRANCHMANAGERPASSWORD);
				}
			}else{
				returnJson.put(Key.STATUS, Value.FAIL);
				returnJson.put(Key.MESSAGE, Message.BRANCHMANAGERNOTEXIST);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL)  ;
			returnJson.put(Key.MESSAGE, e);
		}
		return returnJson;
	}
}
