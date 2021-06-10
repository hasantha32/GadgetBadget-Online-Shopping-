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
			url : "ShipAPI", 
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
 		$("#accountID").val($(this).closest("tr").find('td:eq(0)').text()); 
 		$("#firstName").val($(this).closest("tr").find('td:eq(1)').text()); 
 		$("#lastName").val($(this).closest("tr").find('td:eq(2)').text()); 
 		$("#userName").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#email").val($(this).closest("tr").find('td:eq(4)').text());
 		$("#address1").val($(this).closest("tr").find('td:eq(5)').text());
 		$("#address2").val($(this).closest("tr").find('td:eq(6)').text());
 		$("#country").val($(this).closest("tr").find('td:eq(7)').text());
 		$("#state").val($(this).closest("tr").find('td:eq(8)').text());
 		$("#zipCode").val($(this).closest("tr").find('td:eq(9)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "ShipAPI", 
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
	// ACCOUNT ID
	if ($("#accountID").val().trim() == "") 
	 { 
	 		return "Insert Account ID."; 
	 } 
	
	// FirstName
	if ($("#firstName").val().trim() == "") 
	 { 
	 		return "Insert First Name."; 
	 }

	// LastName-------------------------------
	if ($("#lastName").val().trim() == "") 
	 { 
	 		return "Insert Last Name."; 
	 }
	 
	 // User Name-------------------------------
	if ($("#userName").val().trim() == "") 
	 { 
	 		return "Insert User Name."; 
	 } 
	 
	 // Email-------------------------------
	if ($("#email").val().trim() == "") 
	 { 
	 		return "Insert Email Address."; 
	 } 
	 
	 // Address1-------------------------------
	if ($("#address1").val().trim() == "") 
	 { 
	 		return "Insert Address 1."; 
	 }
	 
	  // Address2-------------------------------
	if ($("#address2").val().trim() == "") 
	 { 
	 		return "Insert Address 2."; 
	 }  
	
	 // Country-------------------------------
	if ($("#country").val().trim() == "") 
	 { 
	 		return "Insert Country."; 
	 }
	 
	  // State-------------------------------
	if ($("#state").val().trim() == "") 
	 { 
	 		return "Insert State."; 
	 }
	 
	  // ZipCode-------------------------------
	if ($("#zipCode").val().trim() == "") 
	 { 
	 		return "Insert zipCode."; 
	 }
	// is ID numerical value
	var tmpid = $("#accountID").val().trim(); 
	if (!$.isNumeric(tmpid)) 
	 { 
	 		return "Insert a numerical value for Account ID."; 
	 }
	 
	 // is Zip numerical value
	var tmpzip = $("#zipCode").val().trim(); 
	if (!$.isNumeric(tmpzip)) 
	 { 
	 		return "Insert a numerical value for Zip Code."; 
	 }
	  
	
	return true; 
}

 		
