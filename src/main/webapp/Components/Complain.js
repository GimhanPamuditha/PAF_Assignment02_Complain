
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
var status = validateComplainForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidComplainIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ComplainAPI", 
 type : type, 
 data : $("#formComplain").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onComplainSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidComplainIDSave").val($(this).data("complainid")); 
 $("#complainCategory").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#complainSubject").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#complainMessage").val($(this).closest("tr").find('td:eq(2)').text());
 $("#complainDate").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#contactNumber").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "ComplainAPI", 
 type : "DELETE", 
 data : "complainID=" + $(this).data("complainid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onComplainDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateComplainForm() 
{ 
// CATEGORY 
if ($("#complainCategory").val().trim() == "") 
 { 
 return "Insert Complain Category."; 
 } 
// SUBJECT
if ($("#complainSubject").val().trim() == "") 
 { 
 return "Insert Complain Subject."; 
 } 
// MESSAGE-------------------------------
if ($("#complainMessage").val().trim() == "") 
 { 
 return "Insert Complain Message."; 
 } 
// DATE------------------------
if ($("#complainDate").val().trim() == "") 
 { 
 return "Insert Complain Date."; 
 }
// NUMBER------------------------
if ($("#contactNumber").val().trim() == "") 
 { 
 return "Insert Contact Number."; 
 } 
return true; 
}

function onComplainSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divComplainsGrid").html(resultSet.data); 
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
 $("#hidComplainIDSave").val(""); 
 $("#formComplain")[0].reset(); 
}


function onComplainDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divComplainsGrid").html(resultSet.data); 
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




