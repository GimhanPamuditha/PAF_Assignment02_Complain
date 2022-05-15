package model; 
import java.sql.*; 


public class Complain 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/complain", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readComplains() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Complain Category</th>" 
		 +"<th>Complain Subject</th><th>Complain Message</th>"
		 +"<th>Complain Date</th><th>Contact Number</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from complains"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String complainID = Integer.toString(rs.getInt("complainID")); 
 String complainCategory = rs.getString("complainCategory"); 
 String complainSubject = rs.getString("complainSubject");  
 String complainMessage = rs.getString("complainMessage"); 
 String complainDate = rs.getString("complainDate");
 String contactNumber = rs.getString("contactNumber");
 
 // Add into the html table
 output += "<tr><td>" + complainCategory + "</td>"; 
 output += "<td>" + complainSubject + "</td>"; 
 output += "<td>" + complainMessage + "</td>";
 output += "<td>" + complainDate + "</td>";
 output += "<td>" + contactNumber + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-complainid='" + complainID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-complainid='" + complainID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the complains."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertComplain(String category, String subject, 
		 String message, String date, String contactNumber) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into complains (`complainID`,`complainCategory`,`complainSubject`,`complainMessage`,`complainDate`,`contactNumber`)"
		 + " values (?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, category); 
		 preparedStmt.setString(3, subject);
		 preparedStmt.setString(4, message);
		 preparedStmt.setString(5, date);		  
		 preparedStmt.setString(6, contactNumber); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newComplains = readComplains(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newComplains + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the complain.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateComplain(String ID, String category, String subject, 
		 String message, String date, String contactNumber) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE complains SET complainCategory=?,complainSubject=?,complainMessage=?,complainDate=?,contactNumber=? WHERE complainID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, category); 
		 preparedStmt.setString(2, subject);
		 preparedStmt.setString(3, message);
		 preparedStmt.setString(4, date);
		 preparedStmt.setString(5, contactNumber); 
		 preparedStmt.setInt(6, Integer.parseInt(ID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newComplains = readComplains(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newComplains + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the complain.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteComplain(String complainID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from complains where complainID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(complainID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newComplains = readComplains(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newComplains + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the complain.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


