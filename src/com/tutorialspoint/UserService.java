package com.tutorialspoint;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Service")
public class UserService {
	
   UserDao userDao = new UserDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";


   @GET
   @Path("/seva")
   
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> getUsers(@QueryParam("sevaName") String name,@QueryParam("from") String from,
			@QueryParam("to") String to,@QueryParam("searchParam") String searchParam){
       return userDao.getAllUsers(name,from,to,searchParam);
   }
   
   @GET
   @Path("/seva/sevaName")
   
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> getUsers(@QueryParam("name") String name){
       return userDao.getAllUsers(name);
   }
   
   
   @GET
   @Path("/sms")
   
   @Produces(MediaType.APPLICATION_JSON)
   public String sendSms(){
       return userDao.SmsUtlity(); 
   }
   

   @GET
   @Path("/seva/{userid}")
   @Produces(MediaType.APPLICATION_XML)
   public User getUser(@PathParam("userid") int userid){
      return userDao.getUser(userid);
   }

   @POST
   @Path("/seva")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
//   public String createUser(	   
//      @FormParam("devoteeName") String devoteename,
//      @FormParam("cost") int cost,
//      @FormParam("gotra") String gotra,
//      @FormParam("savaName") String savaname,
//      @FormParam("nakshatra") String nakshatra,
//      @Context HttpServletResponse servletResponse) throws IOException{
//      User user = new User(5,devoteename,cost,nakshatra,gotra,savaname);
//      int result = userDao.addUser(user);
//      if(result == 1){
//         return SUCCESS_RESULT;
//      }
//      return FAILURE_RESULT;
//   }
   public Response getUserById( ReadInput   input){
       
       System.out.println("Received order from :"+input);
       if((input.getId()) == null) {
    	   Random rand = new Random();
    	   int num = rand.nextInt(9000000) + 1000000;
    	   input.setId(num);
       };
       User user = new User(input.getId(),input.getDevoteeName(),input.getCost(),input.getNakshatra(),input.getGotra(),input.getSevaName(),input.getSevaDate(),input.getPaymentDate(),input.getContactNum());
       int result = userDao.addUser(user);
		System.out.println("result::"+ result);
       if(result != 0){
    	   String resultString = "{\"success\":true,\"message\": \"success\",\"data\":" +result+"}";

           return Response.status(200).entity(resultString).build(); 
       }
       else {
   	       String resultString = "{\"success\":false,\"message\": \"Duplicate entry for Id. please give another number\",\"data\":null}";
           return Response.status(400).entity(resultString).build(); 
    	   
        } 
   }

   
   @POST
   @Path("/upload_sevas")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addSevas(JsonArray inputJson){
	   int result = 0;
       System.out.println("Received order from :"+inputJson);
       //JsonArray getArray = getObject.getJsonArray("JArray1");
       ReadInput input = new ReadInput();
       for(int i =0; i< inputJson.size(); i++) {
    	 
    	   if(inputJson.getJsonObject(i).containsKey("id")) {
    	       System.out.println("Inside IF :"+inputJson.getJsonObject(i).getInt("id"));
  
    	     input.setId((inputJson.getJsonObject(i).getInt("id")));
    	     
    	   }  
    	   
    	   input.setContactNum(inputJson.getJsonObject(i).getString("contactNum"));
    	   input.setCost(inputJson.getJsonObject(i).getInt("cost"));
    	   input.setDevoteeName(inputJson.getJsonObject(i).getString("devoteeName"));
    	   input.setGotra(inputJson.getJsonObject(i).getString("gotra"));
    	   input.setNakshatra(inputJson.getJsonObject(i).getString("nakshatra"));
    	   input.setSevaDate(inputJson.getJsonObject(i).getString("sevaDate"));
    	   input.setSevaName(inputJson.getJsonObject(i).getString("sevaName"));
       
	       if((input.getId()) == null) {
	    	   Random rand = new Random();
	    	   int num = rand.nextInt(9000000) + 1000000;
	    	   input.setId(num);
	       };
	       User user = new User(input.getId(),input.getDevoteeName(),input.getCost(),input.getNakshatra(),input.getGotra(),input.getSevaName(),input.getSevaDate(),input.getPaymentDate(),input.getContactNum());
	       result = userDao.addUser(user);
	       input.setId(null);
	       
     }
			System.out.println("result::"+ result);
	       if(result != 0){
	    	   String resultString = "{\"success\":true}";
	
	           return Response.status(200).entity(resultString).build(); 
	       }
	       else {
	   	       String resultString = "{\"success\":false}";
	           return Response.status(400).entity(resultString).build(); 
	    	   
	        } 
       
     }
   
   
   
   
   
   
   
   
   @PUT
   @Path("/seva")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
//     User user = new User(3,"qq",1,"DD","FF","ZZ"," "," ");
//      int result = userDao.updateUser(user);
//      if(result == 1){ 
//         return SUCCESS_RESULT;
  //    }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/seva/{userid}")
   @Produces(MediaType.APPLICATION_XML)
   public String deleteUser(@PathParam("userid") int userid){
      int result = userDao.deleteUser(userid);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/seva")
   @Produces(MediaType.APPLICATION_XML)
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }
}