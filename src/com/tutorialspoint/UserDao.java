package com.tutorialspoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class UserDao {
	public List<User> getAllUsers(){
		List<User> userList = null;
		Statement stmt = null;

		try {

			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/temple","root", "root123");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				File file = new File("Users.dat");
				if (1 == 0) {
					//    	            User user = new User(1, "Mahesh",1,"b","a","A");
					//    	            userList = new ArrayList<User>();
					//    	            userList.add(user);
					//    	            saveUserList(userList);		
				}
				else{
					System.out.println("You made it, take control your database now!");
					
					String sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS";
					System.out.println("You made it, take control your database now! 1");
					stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					System.out.println("You made it, take control your database now! 2");
					userList = new ArrayList<User>();
					while(rs.next()){
						//Retrieve by column name
						System.out.println("You made it, take control your database now!");
						int id  = rs.getInt("id");
						String devoteeName = rs.getString("devoteename");
						int cost  = rs.getInt("cost");
						String nakshatra = rs.getString("nakshatra");
						String gotra = rs.getString("gotra");
						String savaname = rs.getString("savaname");
						Date sevaDate = rs.getDate("sevaDate");
						Date paymentDate = rs.getDate("paymentDate");
						String contactNum  = rs.getString("contactnum");
						//Display values
						User user = new User(id, devoteeName, cost,nakshatra,gotra,savaname,sevaDate.toString(),paymentDate.toString(),contactNum);
						userList.add(user);
						saveUserList(userList);
						System.out.print("ID: "+id);
						System.out.print(", savaname: " + savaname);
						System.out.print(", cost: " + cost);
						System.out.println(", devoteename: " + devoteeName);
					}

					rs.close();

				}
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed: "+e.getMessage());
		}		
		return userList;
	}

	// with query 

	public List<User> getAllUsers(String name,String from, String to, String number){
		List<User> userList = null;
		Statement stmt = null;
		try {
			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/temple","root", "root123");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				File file = new File("Users.dat");
				if (1 == 0) {
					//	    	            User user = new User(1, "Mahesh",1,"b","a","A");
					//	    	            userList = new ArrayList<User>();
					//	    	            userList.add(user);
					//	    	            saveUserList(userList);		
				}
				else{
					System.out.println("You made it, take control your database now!");
					// FileInputStream fis = new FileInputStream(file);
					// ObjectInputStream ois = new ObjectInputStream(fis);
					//userList = (List<User>) ois.readObject();
					//ois.close();
					String sql = null;
					System.out.println("name:"+name);
					System.out.println("to:"+to);
					System.out.println("from:"+from);
					System.out.println("number:"+number);
					if(name == null) {
						System.out.println("name is null");
					}
					if(to == null) {
						System.out.println("to is null");
					}
					if(from == null) {
						System.out.println("from is null");
					}
					if(number == null) {
						System.out.println("from is null");
					}
					if(number != null) {
						sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE contactnum="+"\'"+number+"\'";

					}else {
						if((name == null)&&(from == null)&&( to == null)) {
							
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS";	 
						}else if((name == null)&&(to == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate >="+"\'"+from+"\'";
						}else if((name == null)&&(from == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate <="+"\'"+to+"\'";
						}else if((to == null)&&(from == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE savaname="+"\'"+name+"\'";
						}else if((name == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate>="+"\'"+from+"\'"+"and sevadate <="+"\'"+to+"\'";
						}else if((to == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate>="+"\'"+from+"\'"+"and savaname ="+"\'"+name+"\'";
						}else if((from == null)) {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate<="+"\'"+to+"\'"+"and savaname ="+"\'"+name+"\'";
						}else {
							sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate<="+"\'"+to+"\'"+"and savaname ="+"\'"+name+"\'"+"and sevadate>="+"\'"+from+"\'";
						}
					}
					System.out.println("You made it, take control your database now! 1");
					stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					System.out.println("You made it, take control your database now! 2");
					userList = new ArrayList<User>();
					while(rs.next()){
						//Retrieve by column name
						System.out.println("You made it, take control your database now!");
						int id  = rs.getInt("id");
						String devoteeName = rs.getString("devoteename");
						int cost  = rs.getInt("cost");
						String nakshatra = rs.getString("nakshatra");
						String gotra = rs.getString("gotra");
						String savaname = rs.getString("savaname");
						Date sevaDate = rs.getDate("sevaDate");
						Date paymentDate = rs.getDate("paymentDate");
						String contactNum  = rs.getString("contactnum");
						//Display values
						User user = new User(id, devoteeName, cost,nakshatra,gotra,savaname,sevaDate.toString(),paymentDate.toString(),contactNum);
						userList.add(user);
						saveUserList(userList);
						System.out.print("ID: "+id);
						System.out.print(", savaname: " + savaname);
						System.out.print(", cost: " + cost);
						System.out.println(", devoteename: " + devoteeName);
					}

					rs.close();

				}
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed: "+e.getMessage());
		}		
		return userList;
	}


	public List<User> getAllUsers(String name){
		List<User> userList = null;
		Statement stmt = null;
		try {
			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/temple","root", "root123");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				File file = new File("Users.dat");
				if (1 == 0) {
					//	    	            User user = new User(1, "Mahesh",1,"b","a","A");
					//	    	            userList = new ArrayList<User>();
					//	    	            userList.add(user);
					//	    	            saveUserList(userList);		
				}
				else{
					System.out.println("You made it, take control your database now!");
					// FileInputStream fis = new FileInputStream(file);
					// ObjectInputStream ois = new ObjectInputStream(fis);
					//userList = (List<User>) ois.readObject();
					//ois.close();
					String sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE savaname="+name;
					System.out.println("You made it, take control your database now! 1");
					stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					System.out.println("You made it, take control your database now! 2");
					userList = new ArrayList<User>();
					while(rs.next()){
						//Retrieve by column name
						System.out.println("You made it, take control your database now!");
						int id  = rs.getInt("id");
						String devoteeName = rs.getString("devoteename");
						int cost  = rs.getInt("cost");
						String nakshatra = rs.getString("nakshatra");
						String gotra = rs.getString("gotra");
						String savaname = rs.getString("savaname");
						Date sevaDate = rs.getDate("sevaDate");
						Date paymentDate = rs.getDate("paymentDate");
						String contactNum  = rs.getString("contactnum");
						//Display values
						User user = new User(id, devoteeName, cost,nakshatra,gotra,savaname,sevaDate.toString(),paymentDate.toString(),contactNum);
						userList.add(user);
						saveUserList(userList);
						System.out.print("ID: "+id);
						System.out.print(", savaname: " + savaname);
						System.out.print(", cost: " + cost);
						System.out.println(", devoteename: " + devoteeName);
					}

					rs.close();

				}
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed: "+e.getMessage());
		}		
		return userList;
	}


	public User getUser(int id){
		List<User> users = getAllUsers();

		for(User user: users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	public int addUser(User pUser){
		return saveUserList(pUser);
		

	}

	public int updateUser(User pUser){
		List<User> userList = getAllUsers();

		for(User user: userList){
			if(user.getId() == pUser.getId()){
				int index = userList.indexOf(user);			
				userList.set(index, pUser);
				saveUserList(userList);
				return 1;
			}
		}		
		return 0;
	}

	public int deleteUser(int id){
		List<User> userList = getAllUsers();

		for(User user: userList){
			if(user.getId() == id){
				int index = userList.indexOf(user);			
				userList.remove(index);
				saveUserList(userList);
				return 1;   
			}
		}		
		return 0;
	}

	private void saveUserList(List<User> userList){
		try { 
			File file = new File("Users1.dat"); 
			FileOutputStream fos;  
			fos = new FileOutputStream(file); 
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(userList); 
			oos.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 


	}




	private int saveUserList(User pUser){
		int id =0;
		try {
			PreparedStatement preparedStmt = null;
			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();

			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/temple","root", "root123");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();

			}
			//#####################################################
			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				
					System.out.println("You made it, take control your database now!");
					String sql = "insert into SEVAS (devoteename, cost, nakshatra, gotra,sevadate,paymentdate,savaname,contactnum)"+ " values (?, ?, ?, ?, ?,?,?,?)";
					Calendar calendar = Calendar.getInstance();

					//java.sql.Date startDate = new java.sql.Date(calendar.get));
					System.out.println("You made it, take control your database now! 1");
					// create the mysql insert preparedstatement
					preparedStmt =connection.prepareStatement(sql);

					preparedStmt.setString(1,pUser.getDevoteeName());
					preparedStmt.setInt    (2, pUser.getCost());
					preparedStmt.setString (3, pUser.getNakshatra());
					preparedStmt.setString(4,pUser.getGotra());
					preparedStmt.setDate(5,Date.valueOf(pUser.getSevaDate()));
					preparedStmt.setDate(6,java.sql.Date.valueOf(java.time.LocalDate.now()));
					preparedStmt.setString (7,pUser.getSevaName());
					preparedStmt.setString (8,pUser.getContactNum());

					// execute the preparedstatement
					
					int flag= preparedStmt.executeUpdate();
					System.out.println("flag::"+ flag);
					if(flag==1) {
						String sqlRet ="SELECT id FROM sevas ORDER BY id DESC LIMIT 1";
						PreparedStatement preparedStmtR = null;
						preparedStmtR =connection.prepareStatement(sqlRet);
						ResultSet rs = preparedStmtR.executeQuery();
						while(rs.next()){
						  id  = rs.getInt("id");	
						  System.out.println("result::"+ id);
						}
						
					}
					if((pUser.getSevaName()).equals("shaswatha-seva")) {
						String message ="Hello, "+pUser.getDevoteeName()+" Seva Created Successfully for "+pUser.getSevaName()+" on "+pUser.getSevaDate();
						String requestUrl  = "http://sms.ssdindia.com/api/sendhttp.php?mobiles="+pUser.getContactNum()+"&authkey=17650A5fTICfF5d6792b4&route=5&sender=WEBSMS&message="+message+"&country=91";
			        	URL url = new URL(requestUrl);
			        	HttpURLConnection uc = (HttpURLConnection)url.openConnection();
			        	System.out.println(uc.getResponseMessage());
			        	uc.disconnect();
					}
					
					connection.close();

				

			}
			else {
				System.out.println("Failed to make connection!");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

      return id;
	}


	String  SmsUtlity() {
		try {

			System.out.println("-------- MySQL JDBC Connection Testing ------------");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}

			System.out.println("MySQL JDBC Driver Registered!");
			Connection connection = null;

			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/temple","root", "root123");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}



			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				// FileInputStream fis = new FileInputStream(file);
				// ObjectInputStream ois = new ObjectInputStream(fis);
				//userList = (List<User>) ois.readObject();
				//ois.close();
				
				
				
				String sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE sevadate = ? and savaname = 'shaswatha-seva'";
				System.out.println("You made it, take control your database now! 1");
				Statement stmt = connection.createStatement();
				PreparedStatement preparedStmt = null;
				preparedStmt =connection.prepareStatement(sql);
				preparedStmt.setDate(1,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(2)));
				
				ResultSet rs = preparedStmt.executeQuery();
				//STEP 5: Extract data from result set
				System.out.println("You made it, take control your database now! 2");
				List<User> userList = new ArrayList<User>();
				while(rs.next()){
					//Retrieve by column name
					System.out.println("You made it, take control your database now!");
					int id  = rs.getInt("id");
					String devoteeName = rs.getString("devoteename");
					int cost  = rs.getInt("cost");
					String nakshatra = rs.getString("nakshatra");
					String gotra = rs.getString("gotra");
					String savaname = rs.getString("savaname");
					Date sevaDate = rs.getDate("sevaDate");
					Date paymentDate = rs.getDate("paymentDate");
					String contactNum  = rs.getString("contactnum");
					//Display values
					User user = new User(id, devoteeName, cost,nakshatra,gotra,savaname,sevaDate.toString(),paymentDate.toString(),contactNum);
					userList.add(user);

				}

				for(int i =0; i <userList.size();i++ ) {
					String recipient =userList.get(i).getContactNum();
					//String message = " Hi! "+userList.get(i).getDevoteeName();  //+" booked on "+userList.get(i).getSevaDate();

					System.out.println("num: "+userList.get(i).getContactNum());
//					        		String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
//					        		 "username=" + URLEncoder.encode(username, "UTF-8") +
//					        		 "&password=" + URLEncoder.encode(password, "UTF-8") +
//					        		 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
//					        		 "&messagetype=SMS:TEXT" +
//					        		 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
//					        		 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
//					        		 "&serviceprovider=GSMModem1" +
//					        		 "&responseformat=html";
//					        		URL url = new URL(requestUrl);
//					        		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
//					        		System.out.println(uc.getResponseMessage());
//					        		uc.disconnect();
					
					
					
                    String message ="Gentle reminder! "+userList.get(i).getDevoteeName()+", "+userList.get(i).getSevaName()+" is  on "+userList.get(i).getSevaDate();

					String requestUrl  = "http://sms.ssdindia.com/api/sendhttp.php?mobiles="+userList.get(i).getContactNum()+"&authkey=17650A5fTICfF5d6792b4&route=5&sender=WEBSMS&message="+message+"&country=91";
	        		URL url = new URL(requestUrl);
	        		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	        		System.out.println(uc.getResponseMessage());
	        		uc.disconnect();
			        		System.out.println("num HERE: "+userList.get(i).getContactNum());

				}

				rs.close();
				return "success";


			}
			else {
				System.out.println("Failed to make connection!");
				return "fail";
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			
			return "fail";
		}




	}
}

