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
			url : "PayeeAPI", 
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
 		$("#cardType").val($(this).closest("tr").find('td:eq(1)').text()); 
 		$("#nameOnCard").val($(this).closest("tr").find('td:eq(2)').text()); 
 		$("#cardNo").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#expireDate").val($(this).closest("tr").find('td:eq(4)').text());
 		$("#cvv").val($(this).closest("tr").find('td:eq(5)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "PayeeAPI", 
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
	
	// CARD TYPE
	if ($("#cardType").val().trim() == "") 
	 { 
	 		return "Select Card Type."; 
	 }

	// Name on card-------------------------------
	if ($("#nameOnCard").val().trim() == "") 
	 { 
	 		return "Insert Name On Card."; 
	 }
	 
	 // Card No-------------------------------
	if ($("#cardNo").val().trim() == "") 
	 { 
	 		return "Insert Name Card No."; 
	 } 
	 
	 // Expire Date-------------------------------
	if ($("#expireDate").val().trim() == "") 
	 { 
	 		return "Insert Expire Date."; 
	 } 
	 
	 // CVV-------------------------------
	if ($("#cvv").val().trim() == "") 
	 { 
	 		return "Insert Name On Card."; 
	 }  
	
	// is ID numerical value
	var tmpid = $("#accountID").val().trim(); 
	if (!$.isNumeric(tmpid)) 
	 { 
	 		return "Insert a numerical value for Account ID."; 
	 }
	 
	 // is card No numerical value
	var tmpno = $("#cardNo").val().trim(); 
	if (!$.isNumeric(tmpno)) 
	 { 
	 		return "Insert a numerical value for Card No."; 
	 }
	  
	  // is CVV numerical value
	var tmpcvv = $("#cvv").val().trim(); 
	if (!$.isNumeric(tmpcvv)) 
	 { 
	 		return "Insert a numerical value for CVV."; 
	 }
	
	return true; 
}

 		
