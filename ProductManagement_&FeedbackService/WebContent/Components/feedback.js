/**
 * 
 */
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
	url : "feedbackAPI",
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
	$("#hidItemIDSave").val($(this).data("feedbackid"));
	$("#CustomerID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ItemID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#FeedbackID").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Feedback").val($(this).closest("tr").find('td:eq(3)').text());
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "feedbackAPI",
	type : "DELETE",
	data : "FID=" + $(this).data("feedbackid"),
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
// customerID
	if ($("#CustomerID").val().trim() == "")
	{
	return "Insert Customer ID.";
	}
	// ItemID
	if ($("#ItemID").val().trim() == "")
	{
	return "Insert Item ID.";
}

// FeedbackID-------------------------------
if ($("#FeedbackID").val().trim() == "")
{
return "Insert feedbackID.";
}




// feedback------------------------
if ($("#Feedback").val().trim() == "")
{
return "Insert Feedback.";
}
return true;
}