/**
 * 
 */
function imageValidation(form) {
	file = form.elements[0].files[0];
	if (file.type != "image/png" && file.type != "image/jpeg") {
		form.reset();
		alert("only png or jpg");
	}
}

function imageSave(form,articleId){
	 if(form.elements[0].files.length > 0){
		 var formdata = new FormData();
	     file = form.elements[0].files[0];
	     
	   	 formdata.append("file", file);
	   	 formdata.append("id",articleId);
	     
	     
	     $.ajax({dataType : 'json',
	            url : "singleSave",
	            data : formdata,
	            type : "POST",
	            enctype: 'multipart/form-data',
	            processData: false, 
	            contentType:false,
	            success : function(result) {
	            	location.reload();
	            	alert(result.msg);
	            }
	     });
 	} else{
 		alert("Image was not upload");
 	}
	
}