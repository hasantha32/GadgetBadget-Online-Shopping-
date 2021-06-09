$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE
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
	url : "investigatorsAPI",
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
	$("#FirstName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#LastName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#ContactNumber").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Location").val($(this).closest("tr").find('td:eq(4)').text());
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "investigatorsAPI",
	type : "DELETE",
	data : "InvestID=" + $(this).data("itemid"),
	dataType : "text",
	complete : function(response, status)
	{
	onItemDeleteComplete(response.responseText, status);
	}
	});
})


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
		} 
		else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
	$("#alertError").text("Error while deleting.");
	$("#alertError").show();
	} 
	else
	{
	$("#alertError").text("Unknown error while deleting..");
	$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateItemForm()
{
// FName
	if ($("#FirstName").val().trim() == "")
	{
	return "Insert First Name.";
	}
	// NAME
	if ($("#FirstName").val().trim() == "")
	{
	return "Insert First Name.";
}

// Last Name
	if ($("#LastName").val().trim() == "")
	{
	return "Insert Last Name.";
	}
	// NAME
	if ($("#LastName").val().trim() == "")
	{
	return "Insert Last Name.";
}

// Email
	if ($("#Email").val().trim() == "")
	{
	return "Insert Email.";
	}
	// NAME
	if ($("#Email").val().trim() == "")
	{
	return "Insert Email.";
}


// ContactNumber-------------------------------
if ($("#ContactNumber").val().trim() == "")
{
return "Insert Contact Number.";
}

// is numerical value
var tmpContactNumber = $("#ContactNumber").val().trim();
if (!$.isNumeric(tmpContactNumber))
{
return "Insert a numerical value for Contact Number.";
}


// Location------------------------
if ($("#Location").val().trim() == "")
{
return "Insert  Location.";
}
return true;
}