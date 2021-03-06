package com.tutorialspoint;
//Champions@1
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class UserDao {
	String PortNumber="3305";
	String password = "Champions@1";
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password); // Champions@1

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

	public List<User> getAllUsers(String name,String from, String to, String searchParam){
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

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
					System.out.println("searchParam:"+searchParam);
					if(name == null) {
						System.out.println("name is null");
					}
					if(to == null) {
						System.out.println("to is null");
					}
					if(from == null) {
						System.out.println("from is null");
					}
					if(searchParam == null) {
						System.out.println("searchParam is null");
					}
					if(searchParam != null) {
						sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE contactnum="+"\'"+searchParam+"\'";

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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

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
	
	public int addSevaType(SevaType pUser){
		return addSevaTypeList(pUser);
	}
	
	public int addExpenses(Expenses pUser){
		return addExpensesList(pUser);
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
		int retId =0;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();

			}
			//#####################################################
			if (connection != null) {
					System.out.println("You made it, take control your database now!");
					String sql = "insert into SEVAS (id,devoteename, cost, nakshatra, gotra,sevadate,paymentdate,savaname,contactnum)"+ " values (?,?, ?, ?, ?, ?,?,?,?)";
					System.out.println("You made it, take control your database now! 1");
					// create the mysql insert preparedstatement
					preparedStmt =connection.prepareStatement(sql);
					preparedStmt.setLong(1, pUser.getId());
					preparedStmt.setString(2,pUser.getDevoteeName());
					preparedStmt.setInt(3, pUser.getCost());
					preparedStmt.setString (4, pUser.getNakshatra());
					preparedStmt.setString(5,pUser.getGotra());
					preparedStmt.setDate(6,Date.valueOf(pUser.getSevaDate()));
					preparedStmt.setDate(7,java.sql.Date.valueOf(java.time.LocalDate.now()));
					preparedStmt.setString (8,pUser.getSevaName());
					preparedStmt.setString (9,pUser.getContactNum());

					// execute the preparedstatement
					
					int flag= preparedStmt.executeUpdate();
					System.out.println("flag::"+ flag);
					if(flag==1) {
//						String sqlRet ="SELECT id FROM sevas ORDER BY id DESC LIMIT 1";
//						PreparedStatement preparedStmtR = null;
//						preparedStmtR =connection.prepareStatement(sqlRet);
//						ResultSet rs = preparedStmtR.executeQuery();
//						while(rs.next()){
//						  retId  = rs.getInt("id");	
//						  System.out.println("result::"+ retId);
//						}
						retId = pUser.getId();
						
					}
					if((pUser.getSevaName()).equals("shaswatha-seva")) {
						String message ="Hello, "+pUser.getDevoteeName()+" Seva Created Successfully for "+pUser.getSevaName()+" on "+pUser.getSevaDate();
						String requestUrl  = "http://sms.ssdindia.com/api/sendhttp.php?mobiles="+pUser.getContactNum()+"&authkey=17806AgdJyKYq5e0ae70aP20&route=4&sender=WEBSMS&message="+message+"&country=91";
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

      return retId;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}



			if (connection != null) {
				System.out.println("You made it, take control your database now!");		
				String sql = "SELECT id,devoteename,cost,nakshatra,gotra,sevadate,paymentdate,savaname,contactnum FROM SEVAS WHERE DATE_FORMAT(sevadate,'%m-%d') = DATE_FORMAT('"+java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(2))+"','%m-%d') and savaname = 'shaswatha-seva'";
				System.out.println("You made it, take control your database now! 1");
				Statement stmt = connection.createStatement();
				PreparedStatement preparedStmt = null;
				preparedStmt =connection.prepareStatement(sql);
				ResultSet rs = preparedStmt.executeQuery();
				System.out.println("You made it, take control your database now! 2");
				List<User> userList = new ArrayList<User>();
				while(rs.next()){
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
					User user = new User(id, devoteeName, cost,nakshatra,gotra,savaname,sevaDate.toString(),paymentDate.toString(),contactNum);
					userList.add(user);

				}

				for(int i =0; i <userList.size();i++ ) {
					String recipient =userList.get(i).getContactNum();
					System.out.println("Number: "+userList.get(i).getContactNum());
					System.out.println("Seva Date: "+userList.get(i).getSevaDate());				
					System.out.println("Name: "+userList.get(i).getDevoteeName());				 

                    String message ="Gentle reminder! "+userList.get(i).getDevoteeName()+", "+userList.get(i).getSevaName()+" is  on "+userList.get(i).getSevaDate().substring(8)+"/"+userList.get(i).getSevaDate().substring(5,7);
					String requestUrl  = "http://sms.ssdindia.com/api/sendhttp.php?mobiles="+userList.get(i).getContactNum()+"&authkey=17806AgdJyKYq5e0ae70aP20&route=4&sender=WEBSMS&message="+message+"&country=91";
	        		URL url = new URL(requestUrl); 
	        		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	        		System.out.println(uc.getResponseMessage());
	        		uc.disconnect();
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

	private int addSevaTypeList(SevaType pUser){
		int retId =0;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();

			}
			//#####################################################
			if (connection != null) {
					System.out.println("You made it, take control your database now!");
					String sql = "insert into SEVATYPE (SEVANAME, SEVALABEL)"+ " values (?,?)";
					System.out.println("You made it, take control your database now! 1");
					// create the mysql insert preparedstatement
					preparedStmt =connection.prepareStatement(sql);
					preparedStmt.setString(1,pUser.getSevaName());
					preparedStmt.setString (2, pUser.getSevaLabel());

					// execute the preparedstatement
					
					int flag= preparedStmt.executeUpdate();
					System.out.println("flag::"+ flag);
					if(flag==1) {
						retId = pUser.getId();						
					}
					connection.close();			

			}
			else {
				System.out.println("Failed to make connection!");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

      return retId;
	}
	
	
	public List<SevaType> getSevatype(){
		List<SevaType> sevaTypeList = null;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

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
					sql = "SELECT ID,SEVANAME,SEVALABEL  FROM SEVATYPE";	 
					stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					sevaTypeList = new ArrayList<SevaType>();
					while(rs.next()){
						//Retrieve by column name
						int id  = rs.getInt("id");
						String sevaName = rs.getString("SEVANAME");
						String sevaLabel = rs.getString("SEVALABEL");
						SevaType sevaType = new SevaType(id, sevaName, sevaLabel);
						sevaTypeList.add(sevaType);
					}

					rs.close();

				}
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed: "+e.getMessage());
		}		
		return sevaTypeList;
	}

	public List<Expenses> getExpenses(String from, String to){
		List<Expenses> expensesList = null;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}

			if (connection != null) {
					String sql = null;
					    if((from == null)&&( to == null)) {
							sql = "SELECT id,description,amount,paymentDate,paidto FROM EXPENSES";	 
						}else if((to == null)) {
							sql = "SELECT id,description,amount,paymentDate,paidto FROM EXPENSES WHERE paymentDate>="+"\'"+from+"\'";
						}else if((from == null)) {
							sql = "SELECT id,description,amount,paymentDate,paidto FROM EXPENSES WHERE paymentDate<="+"\'"+to+"\'";
						}else {
							sql = "SELECT id,description,amount,paymentDate,paidto FROM EXPENSES WHERE paymentDate<="+"\'"+to+"\'"+"and paymentDate>="+"\'"+from+"\'";
						}
					
					System.out.println("You made it, take control your database now! 1");
					stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					System.out.println("You made it, take control your database now! 2");
					expensesList = new ArrayList<Expenses>();
					while(rs.next()){
						//Retrieve by column name
						System.out.println("You made it, take control your database now!");
						int id  = rs.getInt("id");
						String description = rs.getString("description");
						int amount  = rs.getInt("amount");
						String paidto = rs.getString("paidto");
						Date paymentDate = rs.getDate("paymentDate");
						//Display values
						Expenses expenses = new Expenses(id,amount,description,paidto,paymentDate.toString());
						expensesList.add(expenses);	
					}
					rs.close();

				
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed: "+e.getMessage());
		}		
		return expensesList;
	}

	private int addExpensesList(Expenses pUser){
		int retId =0;
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
						.getConnection("jdbc:mysql://localhost:"+PortNumber+"/temple","root", password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();

			}
			//#####################################################
			if (connection != null) {
					System.out.println("You made it, take control your database now!");
					String sql = "insert into EXPENSES (DESCRIPTION,AMOUNT,PAYMENTDATE,PAIDTO)"+ " values (?,?,?,?)";
					System.out.println("You made it, take control your database now! 1");
					// create the mysql insert preparedstatement
					preparedStmt =connection.prepareStatement(sql);
					preparedStmt.setString(1,pUser.getDescription());
					preparedStmt.setInt (2, pUser.getAmount());
					preparedStmt.setDate(3,Date.valueOf(pUser.getPaymentDate()));
					preparedStmt.setString(4,pUser.getPaidTo());
					

					// execute the preparedstatement
					
					int flag= preparedStmt.executeUpdate();
					System.out.println("flag::"+ flag);
					if(flag==1) {
						retId = pUser.getId();						
					}
					connection.close();			

			}
			else {
				System.out.println("Failed to make connection!");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

      return retId;
	}

}

