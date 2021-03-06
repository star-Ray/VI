//var adminId = localStorage.getItem('adminId');
//if (adminId == null) {
//    window.location.replace('../pages/login.html');;
//} else {
 $(document).ready(function() {
    	getStudent();
    });
//}

function getStudent() {
	
	var studentId = Number(localStorage.getItem("studentId"));
	var input = {};
	input.studentId = studentId;
	var inputStr = JSON.stringify(input);
	var i = encodeURIComponent(inputStr);
	
	
	$.ajax({
		url: '../VI/GetStudentById?input=' + inputStr, //this part sends to the servlet
		method : 'POST',
		dataType : 'json',
		error : function(err) {
			console.log(err);
		},
		success : function(data) {;
			var status = data.status; //shows the  success/failure of the servlet request
			if (status == 1) {
				var name = data.message.name;
				var address = data.message.address;
				var contact = data.message.contact;
				document.getElementById('name').innerHTML = name;
				document.getElementById('address').innerHTML = address;
				document.getElementById('phone').innerHTML = contact;
				
			} else {
				console.log(message);
			}
		}
	});
}
function editParent(){
	var table = $('#parentTable').DataTable();
	$('#parentTable tbody').on( 'click', 'button', function () {
		var tr = $(this).closest('tr');
        var row = table.row( tr );
       
        bootbox.dialog({
        	title: "Edit Details",
    		message: '<div class="row">  ' +
			'<div class="col-md-12"> ' +
			'<form class="form-horizontal" method="post"> ' +
			'<div class="form-group"> ' +
			'<div><font color="red" id="updatedMessage"></font></div>'+
			'<label class="col-md-2 control-label" for="name">Name</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="name" name="name" type="text" class="form-control input-md" placeholder= ' + row.data().name + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="contact">Contact</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="contact" name="contact" type="text" class="form-control input-md" placeholder= ' + row.data().contact + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="email">Email</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="email" name="email" type="text" class="form-control input-md" placeholder= ' + row.data().email + '> ' +
			'</div> ' +
			'<label class="col-md-2 control-label" for="address">Address</label> ' +
			'<div class="col-md-10"> ' +
			'<input id="address" name="address" type="text" class="form-control input-md" placeholder= ' + row.data().address + '> ' +
			'</div> ' +
			'</div> ' +
			'</form> ' +
			'</div> ' +
			'</div>',
    		onEscape: function() {},
    		buttons: {
    			success:{
    				label: "Save!",
    				className: "btn-success",
    				
    				callback: function(){
    					var parentId = row.data().parentId;
    					var updatedName = $("#name").val();
    					var updatedContact = $("#contact").val();
    					var updatedAddress = $("#address").val();
    					var updatedEmail = $("#email").val();
    					
    					var input = {}
    					input.parentId = Number(parentId);
    					input.name = updatedName;
    					input.contact = updatedContact;
    					input.address = updatedAddress;
    					input.email = updatedEmail;
    					var inputStr = JSON.stringify(input);
    					
    					inputStr = encodeURIComponent(inputStr);
    					$.ajax({
    						url : '../VI/UpdateParentServlet?input=' + inputStr, //this part sends to the servlet
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
    								$("#message").html("Something's wrong, please try again!");
    							}
    						}
    					});
    				}
    		
    			}
    		}	
    	}); 
	});
}


