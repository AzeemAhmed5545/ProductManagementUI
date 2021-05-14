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


// SAVE ============================================
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
 url : "ProductServiceAPI",
type : type,
data : $("#formItem").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================

$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("sellerID"));
$("#productID").val($(this).closest("tr").find('td:eq(0)').text());
$("#sellerName").val($(this).closest("tr").find('td:eq(1)').text());
$("#productprice").val($(this).closest("tr").find('td:eq(2)').text());
$("#productDesc").val($(this).closest("tr").find('td:eq(3)').text());
});

//function======================================
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



//remove==============================================
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "ProductServiceAPI",
type : "DELETE",
data : "sellerID=" + $(this).data("sellerid"),
dataType : "text",
complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});

//delete===========================================


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

// CODE
if ($("#productID").val().trim() == "")
{
return "Insert Product ID.";
}

// NAME
if ($("#sellerName").val().trim() == "")
{
return "Insert Seller Name.";
}

// PRICE-------------------------------
if ($("#productprice").val().trim() == "")
{
return "Insert Product Price.";
}
// is numerical value
var tmpPrice = $("#productprice").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Product Price.";
}
// convert to decimal price
$("#productprice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#productDesc").val().trim() == "")
{
return "Insert Product Description.";
}
return true;

}


