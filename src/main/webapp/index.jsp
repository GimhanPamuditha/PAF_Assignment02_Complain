<%@page import="model.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (request.getParameter("complainCategory") != null) 
{ 
 Complain complainObj = new Complain(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidComplainIDSave") == "") 
 { 
 stsMsg = complainObj.insertComplain(request.getParameter("complainCategory"), 
 request.getParameter("complainSubject"), 
 request.getParameter("complainMessage"),
 request.getParameter("complainDate"),
 request.getParameter("contactNumber")); 
 } 
else//Update----------------------
 { 
 stsMsg = complainObj.updateComplain(request.getParameter("hidComplainIDSave"), 
 request.getParameter("complainCategory"), 
 request.getParameter("complainSubject"), 
 request.getParameter("complainMessage"),
 request.getParameter("complainDate"),
 request.getParameter("contactNumber")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidComplainIDDelete") != null) 
{ 
 Complain complainObj = new Complain(); 
 String stsMsg = 
 complainObj.deleteComplain(request.getParameter("hidComplainIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complains Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Complain.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Complains Management</h1>
<form id="formComplain" name="formComplain">
 Complain Category: 
 <input id="complainCategory" name="complainCategory" type="text" 
 class="form-control form-control-sm">
 <br> Complain Subject: 
 <input id="complainSubject" name="complainSubject" type="text" 
 class="form-control form-control-sm">
 <br> Complain Message: 
 <input id="complainMessage" name="complainMessage" type="text" 
 class="form-control form-control-sm">
 <br> Complain Date: 
 <input id="complainDate" name="complainDate" type="text" 
 class="form-control form-control-sm">
 <br> Contact Number: 
 <input id="contactNumber" name="contactNumber" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidComplainIDSave" 
 name="hidComplainIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divComplainsGrid">
 <%
 	Complain complainObj = new Complain(); 
  out.print(complainObj.readComplains());
 %>
</div>
</div> </div> </div> 
</body>
</html>
