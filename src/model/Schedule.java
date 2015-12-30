package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataManager.AttendanceDAO;
import system.Config;
import system.Key;
import system.Value;

public class Schedule {
	private long scheduleId;
	private String name;
	private String description;
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	
	private TeacherCourse teacherCourse;
	private Student student;
	
	private Set<Attendance> attendances; 
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	public Schedule(){}

	public Schedule(String name, String description, Date scheduleStartDate, Date scheduleEndDate, TeacherCourse teacherCourse,
			Student student) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setScheduleStartDate(scheduleStartDate);
		this.setScheduleEndDate(scheduleEndDate);
		this.setTeacherCourse(teacherCourse);
		this.setStudent(student);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the scheduleId
	 */
	public long getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the scheduleStartDate
	 */
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * @param scheduleStartDate the scheduleStartDate to set
	 */
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the scheduleEndDate
	 */
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * @param scheduleEndDate the scheduleEndDate to set
	 */
	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the teacherCourse
	 */
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}

	/**
	 * @param teacherCourse the teacherCourse to set
	 */
	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
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

	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.SCHEDULEID, this.scheduleId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.DESCRIPTION, description);
		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
		
		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
		returnJson.put(Key.STUDENT, this.student.toJson());
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	//May not need it anymore, delete once confirmation is made
//	public JSONObject toCalendarJson() {
//		JSONObject returnJson = new JSONObject();
//		returnJson.put(Key.ID, this.scheduleId);
//		returnJson.put(Key.TITLE, this.name);
//		returnJson.put(Key.DESCRIPTION, description);
//		returnJson.put(Key.START, Config.SDF.format(this.scheduleStartDate));
//		returnJson.put(Key.END, Config.SDF.format(this.scheduleEndDate));
//		returnJson.put(Key.ALLDAY, false);
//		
//		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
//		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
//		
//		returnJson.put(Key.OBJSTATUS, this.objStatus);
//		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
//		returnJson.put(Key.REMARK, this.remark);
//		
//		return returnJson;
//	}
	
	//TODO finish the method
//	public JSONObject toJsonStrong() {
//		JSONObject returnJson = new JSONObject();
//		returnJson.put(Key.SCHEDULEID, this.scheduleId);
//		returnJson.put(Key.NAME, this.name);
//		returnJson.put(Key.DESCRIPTION, description);
//		returnJson.put(Key.SCHEDULESTARTDATE, Config.SDF.format(this.scheduleStartDate));
//		returnJson.put(Key.SCHEDULEENDDATE, Config.SDF.format(this.scheduleEndDate));
//		
//		returnJson.put(Key.TEACHERCOURSE, this.teacherCourse.toJson());
//		returnJson.put(Key.CLASSROOM, this.classroom.toJson());
//		
//		returnJson.put(Key.OBJSTATUS, this.objStatus);
//		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
//		returnJson.put(Key.REMARK, this.remark);
//		
//		JSONArray attendanceArr = new JSONArray();
//		for (Attendance a : AttendanceDAO.getAttendancesBySchedule(this)) {
//			if(a.getActualStartDate() != null){
//				attendanceArr.add(a.toScheduleJsonMark());
//			} else {
//				attendanceArr.add(a.toScheduleJson());
//			}
////			attendanceArr.add(a.toJson());
//		}
//		returnJson.put(Key.ATTENDANCES, attendanceArr);
//		
//		return returnJson;
//	}
}
