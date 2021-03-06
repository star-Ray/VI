package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.ScheduleDAO;
import dataManager.PointEventDAO;
import dataManager.BillDAO;
import dataManager.TeacherStudentCourseDAO;
import system.Config;
import system.Key;
import system.Value;

public class Student {
	private long studentId;
	private String name;
	private String gender;
	private Date birthDate;
	private String homeContact;
	private String emergencyContact;
	private String address;
	private String postalCode;
	private double latitude;
	private double longitude;
	private String schoolName;
	private String schoolLevel;
	private String studentNric;
	private String profilePic;
	private double points;
	private long takenDiagnostic;

	private Parent parent;
	private Branch branch;
	private Campaign campaign;
	
	private Set<Bill> bills;
	private Set<Attendance> attendances;
	private Set<PointEvent> pointEvents;
	private Set<TeacherStudentCourse> teacherStudentCourses;
	private Set<Diagnostic> diagnostics;
	private Set<GiftTransaction> giftTransactions;

	private long objStatus;
	private Date createDate;
	private String remark;

	public Student() {
	}
	
	//without campaign
	public Student(String name, String gender, Date birthDate, String homeContact, String emergencyContact, 
			String address, String postalCode, double latitude, double longitude, String schoolName, String schoolLevel, String studentNric, 
			String profilePic, long takenDiagnostic, Parent parent, Branch branch) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.homeContact = homeContact;
		this.emergencyContact = emergencyContact;
		this.address = address;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.schoolName = schoolName;
		this.schoolLevel = schoolLevel;
		this.studentNric = studentNric;
		this.profilePic = profilePic;
		this.points = 0.0d;
		this.parent = parent;
		this.branch = branch;
		this.takenDiagnostic = takenDiagnostic;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());	
	}
	
	//full creation
	public Student(String name, String gender, Date birthDate, String homeContact, String emergencyContact, 
			String address, String postalCode, double latitude, double longitude,String schoolName, String schoolLevel, String studentNric, 
			String profilePic, long takenDiagnostic, Parent parent, Branch branch, Campaign campaign) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.homeContact = homeContact;
		this.emergencyContact = emergencyContact;
		this.address = address;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.schoolName = schoolName;
		this.schoolLevel = schoolLevel;
		this.studentNric = studentNric;
		this.profilePic = profilePic;
		this.points = 0.0d;
		this.parent = parent;
		this.branch = branch;
		this.campaign = campaign;
		this.takenDiagnostic = takenDiagnostic;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());	
	}
	
	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the homeContact
	 */
	public String getHomeContact() {
		return homeContact;
	}

	/**
	 * @param homeContact the homeContact to set
	 */
	public void setHomeContact(String homeContact) {
		this.homeContact = homeContact;
	}

	/**
	 * @return the emergencyContact
	 */
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * @param emergencyContact the emergencyContact to set
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the schoolLevel
	 */
	public String getSchoolLevel() {
		return schoolLevel;
	}

	/**
	 * @param schoolLevel the schoolLevel to set
	 */
	public void setSchoolLevel(String schoolLevel) {
		this.schoolLevel = schoolLevel;
	}

	/**
	 * @return the studentNric
	 */
	public String getStudentNric() {
		return studentNric;
	}

	/**
	 * @param studentNric the studentNric to set
	 */
	public void setStudentNric(String studentNric) {
		this.studentNric = studentNric;
	}

	/**
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}

	/**
	 * @param profilePic the profilePic to set
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	/**
	 * @return the points
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}

	/**
	 * @return the takenDiagnostic
	 */
	public long getTakenDiagnostic() {
		return takenDiagnostic;
	}

	/**
	 * @param takenDiagnostic the takenDiagnostic to set
	 */
	public void setTakenDiagnostic(long takenDiagnostic) {
		this.takenDiagnostic = takenDiagnostic;
	}

	/**
	 * @return the parent
	 */
	public Parent getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * @return the campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/**
	 * @return the bills
	 */
	public Set<Bill> getBills() {
		return bills;
	}

	/**
	 * @param bills the bills to set
	 */
	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	/**
	 * @return the attendances
	 */
	public Set<Attendance> getAttendances() {
		return attendances;
	}

	/**
	 * @param attendances the attendances to set
	 */
	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

	/**
	 * @return the pointEvents
	 */
	public Set<PointEvent> getPointEvents() {
		return pointEvents;
	}

	/**
	 * @param pointEvents the pointEvents to set
	 */
	public void setPointEvents(Set<PointEvent> pointEvents) {
		this.pointEvents = pointEvents;
	}

	/**
	 * @return the teacherStudentCourses
	 */
	public Set<TeacherStudentCourse> getTeacherStudentCourses() {
		return teacherStudentCourses;
	}

	/**
	 * @param teacherStudentCourses the teacherStudentCourses to set
	 */
	public void setTeacherStudentCourses(
			Set<TeacherStudentCourse> teacherStudentCourses) {
		this.teacherStudentCourses = teacherStudentCourses;
	}

	/**
	 * @return the diagnostics
	 */
	public Set<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	/**
	 * @param diagnostics the diagnostics to set
	 */
	public void setDiagnostics(Set<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}

	/**
	 * @return the giftTransactions
	 */
	public Set<GiftTransaction> getGiftTransactions() {
		return giftTransactions;
	}

	/**
	 * @param giftTransactions the giftTransactions to set
	 */
	public void setGiftTransactions(Set<GiftTransaction> giftTransactions) {
		this.giftTransactions = giftTransactions;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus the objStatus to set
	 */
	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.GENDER, this.gender);
		returnJson.put(Key.BIRTHDATE, Config.SDF.format(this.birthDate));
		returnJson.put(Key.HOMECONTACT, this.homeContact);
		returnJson.put(Key.EMERGENCYCONTACT, this.emergencyContact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.LATITUDE, this.latitude);
		returnJson.put(Key.LONGITUDE, this.longitude);
		returnJson.put(Key.SCHOOLNAME, this.schoolName);
		returnJson.put(Key.SCHOOLLEVEL, this.schoolLevel);
		returnJson.put(Key.STUDENTNRIC, this.studentNric);
		returnJson.put(Key.PROFILEPIC, this.profilePic);
		returnJson.put(Key.POINTS, this.points);
		returnJson.put(Key.TAKENDIAGNOSTIC, this.takenDiagnostic);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonParent() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.GENDER, this.gender);
		returnJson.put(Key.BIRTHDATE, Config.SDF.format(this.birthDate));
		returnJson.put(Key.HOMECONTACT, this.homeContact);
		returnJson.put(Key.EMERGENCYCONTACT, this.emergencyContact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.LATITUDE, this.latitude);
		returnJson.put(Key.LONGITUDE, this.longitude);
		returnJson.put(Key.SCHOOLNAME, this.schoolName);
		returnJson.put(Key.SCHOOLLEVEL, this.schoolLevel);
		returnJson.put(Key.STUDENTNRIC, this.studentNric);
		returnJson.put(Key.PROFILEPIC, this.profilePic);
		returnJson.put(Key.POINTS, this.points);
		returnJson.put(Key.TAKENDIAGNOSTIC, this.takenDiagnostic);

		returnJson.put(Key.PARENT, this.parent.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.GENDER, this.gender);
		returnJson.put(Key.BIRTHDATE, Config.SDF.format(this.birthDate));
		returnJson.put(Key.HOMECONTACT, this.homeContact);
		returnJson.put(Key.EMERGENCYCONTACT, this.emergencyContact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.LATITUDE, this.latitude);
		returnJson.put(Key.LONGITUDE, this.longitude);
		returnJson.put(Key.SCHOOLNAME, this.schoolName);
		returnJson.put(Key.SCHOOLLEVEL, this.schoolLevel);
		returnJson.put(Key.STUDENTNRIC, this.studentNric);
		returnJson.put(Key.PROFILEPIC, this.profilePic);
		returnJson.put(Key.POINTS, this.points);
		returnJson.put(Key.TAKENDIAGNOSTIC, this.takenDiagnostic);

		returnJson.put(Key.PARENT, this.parent.toJson());
		returnJson.put(Key.BRANCH, this.branch.toJson());
		returnJson.put(Key.CAMPAIGN, this.campaign.toJsonSimple());

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonCourses() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.STUDENTID, this.studentId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.GENDER, this.gender);
		returnJson.put(Key.BIRTHDATE, Config.BIRTHDATE.format(this.birthDate));
		returnJson.put(Key.HOMECONTACT, this.homeContact);
		returnJson.put(Key.EMERGENCYCONTACT, this.emergencyContact);
		returnJson.put(Key.ADDRESS, this.address);
		returnJson.put(Key.POSTALCODE, this.postalCode);
		returnJson.put(Key.LATITUDE, this.latitude);
		returnJson.put(Key.LONGITUDE, this.longitude);
		returnJson.put(Key.SCHOOLNAME, this.schoolName);
		returnJson.put(Key.SCHOOLLEVEL, this.schoolLevel);
		returnJson.put(Key.STUDENTNRIC, this.studentNric);
		returnJson.put(Key.PROFILEPIC, this.profilePic);
		returnJson.put(Key.POINTS, this.points);
		returnJson.put(Key.TAKENDIAGNOSTIC, this.takenDiagnostic);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		JSONArray TeacherStudentCourseArr = new JSONArray();
		for (TeacherStudentCourse tsc : TeacherStudentCourseDAO.getTeacherStudentCoursesByStudent(this)) {
			TeacherStudentCourseArr.add(tsc.toJsonCourseAndResults());
		}
		returnJson.put(Key.COURSES, TeacherStudentCourseArr);
		
		
		return returnJson;
	}

}
