package com; 
import model.Complain; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Complains") 
public class ComplainService 
{ 
 Complain complainObj = new Complain(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readComplains()   
  { 
  return complainObj.readComplains(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertComplain(@FormParam("complainCategory") String complainCategory, 
  @FormParam("complainSubject") String complainSubject,
  @FormParam("complainMessage") String complainMessage,
  @FormParam("complainDate") String complainDate,
  @FormParam("contactNumber") String contactNumber) 
 { 
  String output = complainObj.insertComplain(complainCategory, complainSubject, complainMessage, complainDate, contactNumber); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateComplain(String complainData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject complainObject = new JsonParser().parse(complainData).getAsJsonObject(); 
 //Read the values from the JSON object
  String complainID = complainObject.get("complainID").getAsString(); 
  String complainCategory = complainObject.get("complainCategory").getAsString(); 
  String complainSubject = complainObject.get("complainSubject").getAsString(); 
  String complainMessage = complainObject.get("complainMessage").getAsString(); 
  String complainDate = complainObject.get("complainDate").getAsString();
  String contactNumber = complainObject.get("contactNumber").getAsString();
 
  String output = complainObj.updateComplain(complainID, complainCategory, complainSubject, complainMessage, complainDate, contactNumber); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteComplain(String complainData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(complainData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String complainID = doc.select("complainID").text(); 
  String output = complainObj.deleteComplain(complainID); 
 return output; 
 }
}
