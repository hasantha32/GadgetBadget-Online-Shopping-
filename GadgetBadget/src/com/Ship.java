package com;

import java.sql.*;

public class Ship {
	
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

	public String insertItem(int accID, String fname, String lname, String uname, String email, String add1, String add2, String cty, String state, String zip) 
	{ 

		String output = ""; 

		try
		{ 

			Connection con = connect(); 

			if (con == null) 

			{return "Error while connecting to the database for inserting."; } 

			// create a prepared statement
			String query = " insert into shippingdetails (`accountID`,`firstName`,`lastName`,`userName`,`email`,`address1`,`address2`,`country`,`state`,`zipCode`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, accID); 
			 preparedStmt.setString(2, fname); 
			 preparedStmt.setString(3, lname); 
			 preparedStmt.setString(4, uname); 
			 preparedStmt.setString(5, email);
			 preparedStmt.setString(6, add1);
			 preparedStmt.setString(7, add2); 
			 preparedStmt.setString(8, cty); 
			 preparedStmt.setString(9, state);
			 preparedStmt.setString(10, zip);

			 // execute the statement
			 preparedStmt.execute(); 
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
					output = "<table width='1280px' border='1'><tr><th>Account ID</th>"
							+ "<th>First Name</th><th>Last Name</th>"
							+ "<th>User Name</th>"
							+ "<th>Email</th>"
							+ "<th>Address 1</th>"
							+ "<th>Address 2</th>"
							+ "<th>Country</th>"
							+ "<th>State</th>"
							+ "<th>Zip Code</th>"
							+ "<th>Update</th><th>Remove</th></tr>"; 
		 
					String query = "select * from shippingdetails"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
						String accountID = Integer.toString(rs.getInt("accountID")); 
						String firstName = rs.getString("firstName"); 
						String lastName = rs.getString("lastName");
						String userName = rs.getString("userName");
						String email = rs.getString("email"); 
						String address1 = rs.getString("address1");
						String address2 = rs.getString("address2");
						String country = rs.getString("country");
						String state = rs.getString("state");
						String zipCode = rs.getString("zipCode"); 
		 
					// Add into the html table
						output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + accountID
								 + "'>" + accountID + "</td>";   
						output += "<td>" + firstName + "</td>"; 
						output += "<td>" + lastName + "</td>";
						output += "<td>" + userName + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + address1 + "</td>";
						output += "<td>" + address2 + "</td>";
						output += "<td>" + country + "</td>";
						output += "<td>" + state + "</td>";
						output += "<td>" + zipCode + "</td>";  
					
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

	public String updateItem(String ID, String fName, String lName, String uname,  String emailadd, String add1, String add2, String cty, String ste, String zip)
	{ 
		 String output = ""; 
		 
		 try
		 { 
		 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE shippingdetails SET firstName=?,lastName=?,userName=?,email=?,address1=?,address2=?,country=?,state=?,zipCode=? WHERE accountID=?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, fName); 
			 	preparedStmt.setString(2, lName);
			 	preparedStmt.setString(3, uname);
			 	preparedStmt.setString(4, emailadd);
			 	preparedStmt.setString(5, add1);
			 	preparedStmt.setString(6, add2);
			 	preparedStmt.setString(7, cty);
			 	preparedStmt.setString(8, ste);
			 	preparedStmt.setString(9, zip);
			 	preparedStmt.setInt(10, Integer.parseInt(ID));  
		 
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

	public String deleteItem(String accountID)
	{ 
	 	String output = ""; 
	 
	 	try
	 	{ 
	 
	 			Connection con = connect(); 
	 
	 			if (con == null) 
	 			{return "Error while connecting to the database for deleting."; } 
	 
	 			// create a prepared statement
	 			String query = "delete from shippingdetails where accountID=?";  
	 			
	 			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 			// binding values
	 			preparedStmt.setInt(1, Integer.parseInt(accountID)); 
	 
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
