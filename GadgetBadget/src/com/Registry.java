package com;

import java.sql.*;

public class Registry {
	
	public Connection connect()
	{
	 Connection con = null;
	 
	 try
	 {
			 //Class.forName("com.mysql.jdbc.Driver");
			 //con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/itemdb","root","");
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountsdb","root","");
			 
			 //For testing
			 //System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
		 System.out.println("Some error in DB");
		 e.printStackTrace();
	 }

	 return con;
	}

	public String insertItem(String fname, String lname, String emailadd, String pass)
	{
				String output = "";
				
				PreparedStatement ps = null;
				
				try
				{
					Connection con = connect();
					if (con == null)
					{
							return "Error while connecting to the database for insterting";
					}
	 
					// create a prepared statement
					//String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
					
					String query = " insert into accounts(accountID,firstName,lastName,email,password)"
							 + " values (?, ?, ?, ?, ?)"; 
					
					ps = con.prepareStatement(query);
					
					// binding values
						ps.setInt(1, 0);
						ps.setString(2, fname);
						ps.setString(3, lname);
						ps.setString(4, emailadd);
						ps.setString(5, pass); 
						
					//execute the statement
						ps.execute();
						con.close();
						
						String newItems = readItems(); 
						 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
							 System.err.println(e.getMessage());
				}
				return output;
	}
	
	public String readItems()
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
					output = "<table border='1'><tr><th>Account ID</th>"
							+ "<th>First Name</th><th>Last Name</th>"
							+ "<th>Email</th> "
							+ "<th>Update</th><th>Remove</th></tr>"; 
		 
					String query = "select * from accounts"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
						
						String accountID = Integer.toString(rs.getInt("accountID")); 
						String firstName = rs.getString("firstName"); 
						String lastName = rs.getString("lastName"); 
						String email = rs.getString("email"); 
						String password = rs.getString("password"); 
		 
					// Add into the html table
						output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + accountID
								 + "'>" + accountID + "</td>";  
						output += "<td>" + firstName + "</td>"; 
						output += "<td>" + lastName + "</td>"; 
						output += "<td>" + email + "</td>"; 
					
					// buttons
						output += "<td><input name='btnUpdate'"
								+ "type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-itemid='" + accountID + "'></td>"
								+ "<td><input name='btnRemove' "
								+ "type='button' value='Remove'"
								+ "class='btnRemove btn btn-danger'"
								+ "data-itemid='"
								+ accountID + "'>" + "</td></tr>"; 
					} 
		 
					con.close(); 
		 
					// Complete the html table
					output += "</table>"; 
			} 
		 catch (Exception e) 
		 { 
			 	output = "Error while reading the items."; 
			 	System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String updateItem(String ID,String fname, String lname, String emailadd, String pass)
	{ 
		 String output = ""; 
		 
		 try
		 { 
		 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE accounts SET firstName=?,lastName=?,email=?,password=? WHERE accountID=?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, fname); 
			 	preparedStmt.setString(2, lname); 
			 	preparedStmt.setString(3, emailadd);
			 	preparedStmt.setString(4, pass); 
			 	preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
			 	// execute the statement
			 	preparedStmt.execute(); 
			 	con.close(); 
		 
			 	String newItems = readItems(); 
			 	 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 
					 System.err.println(e.getMessage());  
		 } 
		 return output; 
	}
	
	public String deleteItem(String ID)
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
					String query = "delete from accounts where accountID=?"; 
	 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ID)); 
	 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
	 
					 String newItems = readItems(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";  
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}"; 
						 System.err.println(e.getMessage());  
			} 
		return output; 
	}
}
