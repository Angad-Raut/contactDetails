
/**
 * This function used for to fetch all contact details.
 * @returns
 */
function getAllContatctDetails(){
	$(function() {
		    var datatable2Rest = function(sSource, aoData, fnCallback) {
			var paramMap = {};
			for ( var i = 0; i < aoData.length; i++) {
				paramMap[aoData[i].name] = aoData[i].value;
			}
			var pageSize = paramMap.iDisplayLength;
			var start = paramMap.iDisplayStart;
			var pageNum = (start == 0) ? 1 : (start / pageSize) + 1; 
			
			var sortCol = paramMap.iSortCol_0;
			var sortDir = paramMap.sSortDir_0;
			var sortName = paramMap['mDataProp_' + sortCol];
			
			var formData={					
					size:pageSize,
					page:pageNum,
					sortNamedir:sortDir,
					sort:sortName
			};
			
			var url = sSource;
			if (paramMap.sSearch != '') {
				formData={						
						size:pageSize,
						page:pageNum,
						sortNamedir:sortDir,
						sort:sortName,
						searchParam:paramMap.sSearch
					};
			}	

			$(".bd-example").LoadingOverlay("show"); 
		   	$.ajax({
				type : "POST",
				contentType: "application/json; charset=utf-8",		
				url:REST_HOST+ url,
				dataType : "json",	
				data : JSON.stringify(formData),
				success : function(resp) {	
					var data=resp.result;
					data.iTotalRecords = data.totalElements;
					data.iTotalDisplayRecords = data.totalElements;		
					fnCallback(data);
				},
				complete:function(e){
					$(".bd-example").LoadingOverlay("hide"); 
				},
				error : function(result) {
					sweetAlert('Error!!', result.status, 'error');
				}			    
		   	});								
		};
		$('#table_id').dataTable({
			"sAjaxSource" : "/contactDetails/getAllContactDetails",
			"sAjaxDataProp" : 'content',			
			"aoColumns" : [ {
				mDataProp : 'srNo',
				"bSortable": false	
			},{
				mDataProp : 'firstName',
				"bSortable": false
			},{
				mDataProp : 'lastName',
				"bSortable": false
			},{
				mDataProp : 'email',
				"bSortable": false
			},{
				mDataProp : 'mobile',
				"bSortable": false
			},{
				mDataProp : 'isActive',
				"bSortable": false
			},{
				mDataProp : function(data){
					if(data.status){
						return '<button type="button" class="btn btn-success btn-sm edit_contact_Id" data-id="'+data.id+'">Edit</button> '+
						              '<button type="button" class="btn btn-primary btn-sm status_contact_Id" data-id="'+data.id+'">Enable</button>';
					}else{
					    return '<button type="button" class="btn btn-success btn-sm edit_contact_Id" data-id="'+data.id+'">Edit</button> '+
					                  '<button type="button" class="btn btn-danger btn-sm delete_contact_Id" data-id="'+data.id+'">Delete</button> '+
			                          '<button type="button" class="btn btn-primary btn-sm status_contact_Id" data-id="'+data.id+'">Disable</button>';
				   }
				},
				"bSortable": false						
			}],
			"bServerSide" : true,
			"destroy": true,
			"searching": true,
			"fnServerData" : datatable2Rest
		});					
	});	
}

/**
 * This function used for to edit contact details by Id.
 * @param formData
 * @returns
 */
function getdataForEdit(formData){
	$("#card_id").LoadingOverlay("show"); 
	$.ajax({
		url:REST_HOST+ "/contactDetails/getContactDetailsById",
		data:JSON.stringify(formData),
        contentType: "application/json",
 	    accept:"json",
	    type:'POST',
	    success:function(resp){
	    	setDataForEdit(resp.result);
	    },
	    complete: function(){
			$('#card_id').LoadingOverlay("hide");
	    },
	    error:function(e){
	    	sweetAlert('Error!!', e.status, 'error');
	    }
   });
}

/**
 * This function used for to delete contact details by Id.
 * @param id
 * @returns
 */
function getdataForDelete(id){
	$("#card_id").LoadingOverlay("show"); 
	$.ajax({
		url:REST_HOST+ "/contactDetails/deleteContactById",
		data:JSON.stringify({entityId:id}),
        contentType: "application/json",
 	    accept:"json",
	    type:'POST',
	    success:function(resp) {
	    	if(resp.result==true){
		    		 toastr.success('Contact Details Deleted Successfully');
		    		 getAllContatctDetails();
	    	}else{
		    	sweetAlert('Error!!', resp.errorMessage, 'error');
	    	}
	    },
	    complete: function(){
			$('#card_id').LoadingOverlay("hide");
	    },
	    error:function(e){
	    	sweetAlert('Error!!', e.status, 'error');
	    }
   });
}

/**
 * This function used for to update contact details status by Id.
 * @param id
 * @returns
 */
function updateStatus(id){
	$("#card_id").LoadingOverlay("show"); 
	$.ajax({
		url:REST_HOST+ "/contactDetails/updateContactStatus",
		data:JSON.stringify({entityId:id}),
        contentType: "application/json",
 	    accept:"json",
	    type:'POST',
	    success:function(resp) {
	    	if(resp.result==true){
	    		toastr.success('Status Updated Successfully');
			   getAllContatctDetails();
	    	}else{
		    	sweetAlert('Error!!', resp.errorMessage, 'error');
	    	}
	    },
	    complete: function(){
			$('#card_id').LoadingOverlay("hide");
	    },
	    error:function(e){
	    	sweetAlert('Error!!', e.status, 'error');
	    }
   });
}

/**
 * This function used for to add/update contact details.
 * @param formdata
 * @returns
 */
function addContactDetails(formdata){
	$("#card_id").LoadingOverlay("show"); 
	$.ajax({
		url:REST_HOST+ "/contactDetails/saveContactDetails",
		data:JSON.stringify(formdata),
        contentType: "application/json",
 	    accept:"json",
	    type:'POST',
	    success:function(resp) {
	    	if(resp.result==true){
	    		  if(formdata.id==null){
	    			  toastr.success('Contact Details Inserted Successfully!!');
	    		  }else{
	    			  toastr.success('Contact Details Updated Successfully!!');
	    		  }
	    		  clearData();
	    		  getAllContatctDetails();
	    	}else{
		    	sweetAlert('Error!!', resp.errorMessage, 'error');
	    	}
	    },
	    complete: function(){
			$('#card_id').LoadingOverlay("hide");
	    },
	    error:function(error){
	    	if(error.status=="406" && error.responseJSON){
	    		var isFocused=false;
	    		$.each(error.responseJSON.errorValidations, function(key, value) {   
	    			$("#"+(key.replace(/[^a-zA-Z0-9]/g, "_"))+"Error").text(value);
	    			if(!isFocused && $("#"+key)){
	    				$("#"+key).focus();
	    				isFocused=!isFocused;
	    			}
	    		});
	    	}	
	    	else
	    		sweetAlert('Error!!', error.status, 'error');
	    }
   });
}
