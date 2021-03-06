$(document).ready(function() {
	var teacherId = localStorage.getItem('teacherId');
	if (teacherId == null) {
		window.location.replace('adminLogin.jsp');
	} else {
		displayCourses();
		$("#selectCourse").change(function() {
			getSchedules();
		});
		$("#selectScheduleEvent").change(function() {
			getStudents();
		});
	}
});

var COURSES;


function getStudents() {
	var SCHEDULEEVENTS = JSON.parse(localStorage.getItem("scheduleEvents"));
	$.fn.dataTable.ext.errMode = 'none';
	var scheduleEventPlanStartDate = $("#selectScheduleEvent").val();
	for(var j = 0; j < SCHEDULEEVENTS.length; j++){
//		console.log(SCHEDULEEVENTS[j].name);
		if(scheduleEventPlanStartDate == SCHEDULEEVENTS[j].planStartDate){
			var scheduleEventId = SCHEDULEEVENTS[j].scheduleEventId;

			var input = {};
			input.scheduleEventId = Number(scheduleEventId);
		
			var inputStr = JSON.stringify(input);
			inputStr = encodeURIComponent(inputStr);
		
			var table = $('#attendanceTable')
					.on(
							'error.dt',
							function(e, settings, techNote, message) {
								console.log(
										'An error has been reported by DataTables: ',
										message);
							})
					.DataTable(
							{
								ajax : {
									url : '../VI/GetScheduleEventByIdServlet?input='
											+ inputStr,
									// dataSrc: 'message'
									dataSrc : function(json) {
										console.log(json);
										var attendances = json.message.attendances;
										console.log(attendances);
										var return_data = new Array();
										//attendance (not array)
										for (var i = 0; i < attendances.length; i++) {
											if (attendances[i].attendanceStatus == 0) {
												return_data
														.push({
															'attendanceId' : attendances[i].attendanceId,
															'studentName' : attendances[i].student.name,
															'studentNric' : attendances[i].student.studentNric,
															'attendanceStatus' : attendances[i].attendanceStatus,
															'button' : "<button class='btn btn-sm btn-success fa fa-check' onclick='getValue();'></button>"
														})
											} else {
												return_data
														.push({
															'attendanceId' : attendances[i].attendanceId,
															'studentName' : attendances[i].student.name,
															'studentNric' : attendances[i].student.studentNric,
															'attendanceStatus' : attendances[i].attendanceStatus,
															'button' : "present"
														})
											}
										}
										return return_data;
									}
								},
								columns : [ {
									"data" : 'attendanceId'
								}, {
									"data" : 'studentName'
								}, {
									"data" : 'studentNric'
								}, {
									"data" : 'attendanceStatus'
								}, {
									"data" : 'button'
								}
		
								]
							});
		}
	}
}

function getValue() {
	var table = $('#attendanceTable').DataTable();
	$('#attendanceTable tbody').off('click').on('click', 'button', function() {
		var attendanceStatus = 1;
		var input = {}
		var tr = $(this).closest('tr');
		var row = table.row(tr);
		var attendanceId = row.data().attendanceId;
		console.log(row.data().attendanceId);
		var miliseconds = new Date();
		var actualStartDate = moment().format();
		
		input.attendanceId = Number(attendanceId);
		input.attendanceStatus = Number(attendanceStatus);
		input.actualStartDate = actualStartDate;
		
		var inputStr = JSON.stringify(input);

		inputStr = encodeURIComponent(inputStr);

		console.log(inputStr);

		$.ajax({
			url : '../VI/UpdateAttendanceServlet?input=' + inputStr, 
			method : 'POST',
			dataType : 'json',
			error : function(err) {
				console.log(err);
				$("#message").html("System has some error. Please try again.");
			},
			success : function(data) {
//				console.log(data);
				var status = data.status;
				var message = data.message;
//				console.log(message);

				if (status == 1) {
					bootbox.alert("Update is successful!")
					table.ajax.reload();
					
					//call the send email servlet to send the email
					sendEmail(message);
					sendSMS(message);

				} else {
					$("#message").html("Something's wrong, please try again!");
				}
			}
		});
	});
}

function submit() {
	bootbox.confirm({
		title : "Confirm submission",
		message : "Are you sure you want to submit attendance? ",
		callback : function(result) {
			if (result) {
				updateAttendance();
				// bootbox.confirm({
				// title: "Confirm submission",
				// message: "Attendance updated",
				// callback: function(){
				//							
				// //window.location = 'adminMain.jsp';
				// }
				// });
				//				
			}
		},
		onEscape : function() {
		}
	});
}

function updateAttendance() {
	// var rows_selected = [];
	var attendance = document.getElementsByName("attendance");
	var table = $('#attendanceTable').DataTable();
	$('#attendanceTable tbody').on(
			'click',
			'input[type="checkbox"]',
			function() {
				console.log("what?");
				var tr;
				var row;
				var attendanceId;
				var attendanceStatus;
				var actualStartDate;
				var attendancesArr = [];
				var input = {}
				console.log("hello");
				num = 0
				for (i = 0; i < attendance.length; i++) {
					if (attendance[i].type == "checkbox"
							&& attendance[i].checked == true) {
						num++;
						// rows_selected.push(attendance[i].value);

						tr = $(this).closest('tr');
						row = table.row(tr);
						console.log(row.data().attendanceId);
						attendanceId = row.data().attendanceId;
						attendanceStatus = row.data().attendanceStatus;

						var miliseconds = new Date();
						actualStartDate = miliseconds.toUTCString();

						// attendancesArr.attendances.push(Number(attendanceId),
						// attendanceStatus, actualStartDate);

						input.attendances = attendancesArr;
					}
				}
				// console.log(num);

				// {"scheduleId":1,"attendanceId":1,"studentId":5,"attendanceStatus":0,"actualStartDate":"2015-10-06T17:50:18.000Z"}

				// pause
				var inputStr = JSON.stringify(input);

				inputStr = encodeURIComponent(inputStr);

				console.log(inputStr);

				$.ajax({
					url : '../VI/UpdateAttendanceServlet?input=' + json,
					method : 'POST',
					dataType : 'json',
					error : function(err) {
						console.log(err);
						$("#message").html("System has some error. Please try again.");
					},
					success : function(data) {
						console.log(data);
						var status = data.status;
						var message = data.message;

						if (status == 1) {
							bootbox.alert("Update is successful!")
							table.ajax.reload();

						} else {
							$("#message").html(
									"Something's wrong, please try again!");
						}
					}
				});
			});
}

function displayCourses() {
	var teacherId = Number(localStorage.getItem("teacherId"));
	var $courseDDL = $('#selectCourse');
	$courseDDL.html('');
	var input = {};
	input.teacherId = teacherId
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
		url : '../VI/GetCoursesByTeacherServlet?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			$courseDDL.html('<option id="-1">No Courses Available</option>');
		},
		success : function(data) {
			var status = data.status; 
			var courses = data.message;
			if (status == 1) {
				$courseDDL.html('<option id="0">Select Course</option>');

				for (var c = 0; c < courses.length; c++){
					console.log(courses[c].course.name);
					console.log(courses[c].course.courseId);
					$courseDDL.append('<option value=' + courses[c].course.courseId + '>' + courses[c].course.name + '</option>' );
				}
			} else{
				$courseDDL.html('<option id="-1">Select Course</option>');
			}
		}
	});
}

function getSchedules() {
	//scheduleid - courseid, teacherid (GetSchedulesByTeacherAndCourseServlet)
	var courseId = Number($("#selectCourse").val());
	var teacherId = Number(localStorage.getItem("teacherId"));
	var input = {};
	input.teacherId = teacherId;
	input.courseId = courseId;
	var inputStr = JSON.stringify(input);
	inputStr = encodeURIComponent(inputStr);
	
	$.ajax({
			url : '../VI/GetSchedulesByTeacherAndCourseServlet?input=' + inputStr, 
			dataType : 'json',
			error : function(err) {
				console.log(err);
			},
			success : function(data) {
				var status = data.status; 
				if (status == 1) {
					var scheduleIds = [];
						
					for (var i = 0; i < data.message.length; i++) {
						var obj = data.message[i];
						localStorage.setItem("teacherName",obj.teacher.name);
						scheduleIds.push(obj.scheduleId);
					}
					localStorage["scheduleIds"] = JSON.stringify(scheduleIds);
					getScheduleEvents();
				} else {
					console.log(message);
				}
			}
		});
	}

function getScheduleEvents() {
	//scheduleeventid - scheduleid (GetScheduleEventsBySchedule)
	//i have scheduleIds not scheduleId
	var storedIds = JSON.parse(localStorage["scheduleIds"]);
	for(var j = 0; j < storedIds.length; j++){
		var scheduleId = Number(storedIds[j]);
	
		var input = {};
		input.scheduleId = scheduleId;
		var inputStr = JSON.stringify(input);
		inputStr = encodeURIComponent(inputStr);
	
		$.ajax({
			url : '../VI/GetScheduleEventsByScheduleServlet?input=' + inputStr, // this part sends to
			// the servlet
			method : 'POST',
			dataType : 'json',
			error : function(err) {
				console.log(err);
			},
			success : function(data) {
				;
				var status = data.status; // shows the success/failure of the
				// servlet request
				if (status == 1) {
					// store scheduleEventIds
					var scheduleEvents = [];
					
					for (var i = 0; i < data.message.length; i++) {
						var obj = data.message[i];
						console.log(obj);
						scheduleEvents.push(obj);
					}
					localStorage["scheduleEvents"] = JSON.stringify(scheduleEvents);	
					displayScheduleEvents();
				} else {
					console.log(message);
				}
			}
		});
	}
}

function displayScheduleEvents() {
	var select = document.getElementById("selectScheduleEvent");
	var storedEvents = JSON.parse(localStorage["scheduleEvents"]);
	var options = [];
	for(var j = 0; j < storedEvents.length; j++){
		//console.log(storedIds[j])
		var scheduleEvent = storedEvents[j];
		options.push(scheduleEvent);
	}
	
	SCHEDULEEVENTS = storedEvents;

	for(var i = 0; i < options.length; i++) {
	    var opt = options[i].planStartDate;
	    var el = document.createElement("option");
	    el.textContent = opt;
	    el.value = opt;
	    select.appendChild(el);
	}
}



function sendEmail(message){
	var studentName = message.student.name;
	var parentName = message.student.parent.name;
	var email = message.student.parent.email;
	var teacherName = localStorage.getItem("teacherName");

	var input = {};
	input.studentName = studentName;
	input.parentName = parentName;
	input.email = email;
	input.teacherName = teacherName;
	
	var inputMsg = JSON.stringify(input);
	console.log(inputMsg);
	$.ajax({
		url : '../VI/system/SendEmailServlet?input=' + inputMsg, 
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
			//$("#message").html(err);
		},
		success : function(data) {
			console.log(data);
			var status = data.status; //shows the  success/failure of the servlet request
			var message = data.message;
			console.log("email sent");
		}
	});
}

function sendSMS(message){
	//appid=123&appsecret=e0b637e3-e218-4e9e- a52f-3967d9d2cb81&receivers= 6511111111%2C6522222222&content=This%20is%20a%20test%20message.&responseformat =JSON
	//http://www.smsdome.com/api/http/sendsms.aspx?appid=1408&appsecret=4c76978e-01c3-43b4-bc57-9d82681f1485&receivers=6591267284&content=%5BExplore%20And%20Learn%5D%20Attendance%20notification.%20Your%20child%20Amanda%20has%20attended%20class.&responseformat=JSON
	var appId = "appid=1408";
	var appSecret = "appsecret=4c76978e-01c3-43b4-bc57-9d82681f1485";
	var receivers = "receivers=65" + message.student.parent.contact;
	var studentName = message.student.name;
	var teacherName = localStorage.getItem("teacherName");
	//ADD IN TEACHER NAME
	var content = "content=[Explore And Learn] Attendance notification." + " Your child " + studentName + " has attended class. Best regards," + teacherName + ".&responseformat=JSON";	
//	var content = "content=[Explore%20And%20Learn]%20Attendance%20notification." + "%20Your child%20" + studentName + "%20has attended class.";
	
	var input = appId + "&" + appSecret + "&" + receivers + "&" + content;
	var url = encodeURI(input);
//	console.log(url);
	
//	var theUrl = "https://www.smsdome.com/api/http/sendsms.aspx?" + url;

//	var xhr = new XMLHttpRequest();
//	xhr.open("GET", theUrl, true);
//	xhr.onload = function (e) {
//	  if (xhr.readyState === 4) {
//	    if (xhr.status === 200) {
//	      console.log(xhr.responseText);
//	    } else {
//	      console.error(xhr.statusText);
//	    }
//	  }
//	};
//	xhr.onerror = function (e) {
//	  console.error(xhr.statusText);
//	};
//	xhr.send(null);

	$.ajax({
		url : 'https://www.smsdome.com/api/http/sendsms.aspx?' + url, 
		method : 'GET',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {
			var result = data.result;
			var content = data.content;
			var credit = data.creadit;
			var recivers = data.recivers;
			
			console.log(data);
			console.log("sms sent");
		}
	});
}