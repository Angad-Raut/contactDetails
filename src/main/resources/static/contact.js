/**
 * This is backend host url.
 */
var REST_HOST="http://localhost:2021";
var EditData;

/**
 * This is entry function.
 */
$(document).ready(function() {
	getAllContatctDetails();
});

/**
 * This event used for edit operation.
 */
$('#table_id').on("click",".edit_contact_Id",function(){
	getdataForEdit({"entityId":$(this).attr("data-id")});
});

/**
 * This event used for delete operation.
 */
$('#table_id').on("click",".delete_contact_Id",function(){
	getdataForDelete($(this).attr("data-id"));
});

/**
 * This event used for status update operation.
 */
$('#table_id').on("click",".status_contact_Id",function(){
	updateStatus($(this).attr("data-id"));
});

/**
 * This function used for to fetch eidt details.
 * @param EditData
 * @returns
 */
function setDataForEdit(EditData){
	$("#id").val(EditData.cid || "");
	$("#firstname").val(EditData.firstName || "");
	$("#lastname").val(EditData.lastName || "");
	$("#email").val(EditData.email || "");
	$("#mobile").val(EditData.mobile || "");
}

/**
 * This event used for to add/update operation.
 */
$('#save_id').click(function(){
	      var id=document.getElementById("id").value;
	      var firstName=document.getElementById("firstname").value;
	      var lastName=document.getElementById("lastname").value;
	      var email=document.getElementById("email").value;
	      var mobile=document.getElementById("mobile").value;
		  var flag=0;
		  var filter=/^[0-9]+$/; 
		  if(firstName=="")
		   {
			   flag=1;
			   sweetAlert('Caution!!', 'Please enter first name!!', 'warning'); 
		   }
		   else if(lastName=="")
		   {
			   flag=1;
			   sweetAlert('Caution!!', 'Please enter last name!!', 'warning');
		   }
		   else if(email=="")
		   {
			   flag=1;
			   sweetAlert('Caution!!', 'Please enter email!!', 'warning'); 
		   
		   }
		   else if(mobile=="")
		   {
			   flag=1;
			   sweetAlert('Caution!!', 'Please enter phone number!!', 'warning'); 
		   }
		   else if(!(mobile.length==10 || mobile==0))
		   {    		   		
			   flag=1;
			   sweetAlert('Caution!!', 'Enter valid phone number!!', 'warning');
		   }
		   if(flag==0){
				   var formData={
	    				   id: id,
	    			       firstName: firstName,
	    			       lastName: lastName,
	    			       email: email,
	    			       mobile: mobile
	    	   	  };
			      addContactDetails(formData);
		  }
});

/**
 * This function used for clear data.
 * @returns
 */
function clearData(){
	document.getElementById("id").value="";
	document.getElementById("firstname").value="";
    document.getElementById("lastname").value="";
    document.getElementById("email").value="";
    document.getElementById("mobile").value="";
}

/**
 * This event used for to reset the data.
 */
$('#reset_id').click(function(){
	clearData();
});