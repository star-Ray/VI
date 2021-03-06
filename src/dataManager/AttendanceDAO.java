package dataManager;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import model.Student;
import model.ScheduleEvent;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import system.Key;
import system.Value;

public class AttendanceDAO {
	// a. Attendance class method: C R U D
	public static ArrayList<Attendance> getAllAttendances() {
		ArrayList<Attendance> attendances = new ArrayList<Attendance>();
		DetachedCriteria dc = DetachedCriteria.forClass(Attendance.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for (Object o : list) {
			attendances.add((Attendance) o);
		}
		return attendances;
	}

	public static Attendance getAttendanceById(long id) {
		return (Attendance) HibernateUtil.get(Attendance.class, id);
	}

	public static void addAttendance(Attendance attendance) {
		HibernateUtil.save(attendance);
	}

	public static void modifyAttendance(Attendance modifiedAttendance) {
		HibernateUtil.update(modifiedAttendance);
	}

	public static void deleteAttendance(Attendance attendance) {
		HibernateUtil.delete(attendance);
	}
	
	//features
	
	public static ArrayList<Attendance> getAttendancesByScheduleEvent(ScheduleEvent scheduleEvent){
		ArrayList<Attendance> results = new ArrayList<Attendance>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attendance.class);
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULEEVENT, scheduleEvent));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			results.add((Attendance) o);
		}
		return results;
	}
	
	public static ArrayList<Attendance> getAttendancesByStudent(Student student){
		ArrayList<Attendance> results = new ArrayList<Attendance>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attendance.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			results.add((Attendance) o);
		}
		return results;
	}
	
	public static Attendance getAttendanceByStudentAndScheduleEvent(Student student, ScheduleEvent scheduleEvent){
		Attendance attendance = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attendance.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULEEVENT, scheduleEvent));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			attendance = (Attendance)o;
		}
		return attendance;
	}
	
	public static ArrayList<Attendance> getAttendancesByStudentAndScheduleEvent(Student student, ScheduleEvent scheduleEvent){
		ArrayList<Attendance> results = new ArrayList<Attendance>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attendance.class);
		detachedCriteria.add(Restrictions.eq(Key.STUDENT, student));
		detachedCriteria.add(Restrictions.eq(Key.SCHEDULEEVENT, scheduleEvent));
		detachedCriteria.add(Restrictions.eq(Key.OBJSTATUS, Value.ACTIVED));
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		for(Object o : list){
			results.add((Attendance) o);
		}
		return results;
	}

}
