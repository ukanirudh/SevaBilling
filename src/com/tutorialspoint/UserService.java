package com.tutorialspoint;

import java.io.IOException;
import java.util.List;

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
   public Response getUserById(ReadInput input){
       
       System.out.println("Received order from :"+input.getDevoteeName());
       User user = new User(5,input.getDevoteeName(),input.getCost(),input.getNakshatra(),input.getGotra(),input.getSevaName(),input.getSevaDate(),input.getPaymentDate(),input.getContactNum());
       int result = userDao.addUser(user);
		System.out.println("result::"+ result);
       if(result != 0){
    	   String resultString = "{\"success\":true,\"message\": \"success\",\"data\":" +result+"}";

           return Response.status(200).entity(resultString).build(); 
       }
       else {
    	   return Response.status(500).entity("Error").build();
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