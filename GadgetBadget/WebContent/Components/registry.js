$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ===========================================
$(document).on("click", "#btnSave", function(event)
{ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 

		// Form validation-------------------
		var status = validateItemForm(); 
		if (status != true) 
		 { 
			 $("#alertError").text(status); 
			 $("#alertError").show(); 
		 	return; 
		 } 

		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 
 		$.ajax( 
 		{ 
			url : "RegistryAPI", 
			type : type, 
			data : $("#formItem").serialize(), 
			dataType : "text", 
			complete : function(response, status) 
			{ 
				 onItemSaveComplete(response.responseText, status); 
			} 
 		}); 
});

function onItemSaveComplete(response, status)
{ 
		if (status == "success") 
 		{ 
 			var resultSet = JSON.parse(response); 
 
 			if (resultSet.status.trim() == "success") 
 			{ 
 				$("#alertSuccess").text("Successfully saved."); 
 				$("#alertSuccess").show(); 
 				
 				$("#divItemsGrid").html(resultSet.data); 
 			} else if (resultSet.status.trim() == "error") 
 			{ 
				 $("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
 			} 
 		} else if (status == "error") 
 		{ 
 			$("#alertError").text("Error while saving."); 
 			$("#alertError").show(); 
 		} else
 		{ 
 			$("#alertError").text("Unknown error while saving.."); 
 			$("#alertError").show(); 
 		} 
 		 
 		 	$("#hidItemIDSave").val(""); 
 			$("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
		$("#hidItemIDSave").val($(this).data("itemid")); 
 		$("#firstName").val($(this).closest("tr").find('td:eq(1)').text()); 
 		$("#lastName").val($(this).closest("tr").find('td:eq(2)').text()); 
 		$("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
 		$("#password").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "RegistryAPI", 
 		type : "DELETE", 
 		data : "accountID=" + $(this).data("itemid"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemDeleteComplete(response.responseText, status); 
 		} 
 	}); 
});

function onItemDeleteComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show();
			 
 			$("#divItemsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") 
 		{ 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		}
 		 
 	} else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else
	{ 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
 	} 
}

// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
	// FirstName
	if ($("#firstName").val().trim() == "") 
	 { 
	 		return "Insert First Name."; 
	 } 
	
	// LastName
	if ($("#lastName").val().trim() == "") 
	 { 
	 		return "Insert Last Name."; 
	 }

	// EMAIL-------------------------------
	if ($("#email").val().trim() == "") 
	 { 
	 		return "Insert Email Address."; 
	 } 
	
	// is numerical value
	/*var tmpPrice = $("#itemPrice").val().trim(); 
	if (!$.isNumeric(tmpPrice)) 
	 { 
	 		return "Insert a numerical value for Item Price."; 
	 }
	 */ 
	
	// convert to decimal price
	 //$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
	
	// DESCRIPTION------------------------
	if ($("#password").val().trim() == "") 
	 { 
	 		return "Insert Password."; 
	 } 
	
	return true; 
}

 		
