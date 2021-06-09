package com;
import java.sql.*;
public class Seller {

	//A common method to connect to the DB
		private Connection connect() 
			{ 
		
					Connection con = null; 
		
					try
					{ 
						//Class.forName("com.mysql.jdbc.Driver"); 
						Class.forName("com.mysql.jdbc.Driver");
						
						//Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sellers", "root", "");
						
						
					} 
					catch (Exception e) 
					{ 
						e.printStackTrace();
					} 
		
					return con; 
			}
		
		public String insertItem(String Fname, String Lname, String contact, String email , String Companyname) 
		{ 

			String output = ""; 

			try
			{ 

				Connection con = connect(); 

				if (con == null) 

				{return "Error while connecting to the database for inserting."; } 

				// create a prepared statement
				String query = " insert into sellerss (`SellersId`,`FirstName`,`LastName`,`ContactNumber`,`Email`,`CompanyName`)"
							+ " values (?, ?, ?, ?, ?,?)"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, Fname); 
				 preparedStmt.setString(3, Lname); 
				 preparedStmt.setString(4, contact); 
				 preparedStmt.setString(5, email); 
				 preparedStmt.setString(6, Companyname);

				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newItems = readItems();
					output = "{\"status\":\"success\", \"data\": \"" +
							newItems + "\"}";
			} 
			catch (Exception e) 
			{ 
				 
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
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
					{return "Error while connecting to the database for reading."; } 
		 
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" +
							"<th>ContactNumber</th>" + 
							"<th>Email</th>" +
							"<th>CompanyName</th>" +
							
							"<th>Update</th><th>Remove</th></tr>"; 
		 
					String query = "select * from sellerss"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
							String SellersId = Integer.toString(rs.getInt("SellersId")); 
							String FirstName = rs.getString("FirstName"); 
							String LastName = rs.getString("LastName"); 
							String Email = rs.getString("ContactNumber"); 
							String ContactNumber = rs.getString("Email");
							String CompanyName = rs.getString("CompanyName"); 
		 
							// Add into the html table
							output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + SellersId
									+ "'>" + FirstName + "</td>";
							output += "<td>" + LastName + "</td>"; 
							output += "<td>" + ContactNumber + "</td>"; 
							output += "<td>" + Email + "</td>"; 
							output += "<td>" + CompanyName + "</td>"; 
		 
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-itemid='" + SellersId + "'></td>"
				            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
				            		+ "class = 'btnRemove btn btn-danger' data-itemid='" + SellersId + "'>"
				            		+"</td></tr>";
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
		
		public String updateItem(String ID, String Fname, String Lname, String contact, String email, String Companyname)
		{ 
			 String output = ""; 
			 
			 try
			 { 
			 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement
				 	String query = "UPDATE sellerss SET FirstName=?,LastName=?,ContactNumber=?,Email=?,CompanyName=? WHERE SellersId=?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values
				 	 
					 preparedStmt.setString(1, Fname); 
					 preparedStmt.setString(2, Lname); 
					 preparedStmt.setString(3, contact); 
					 preparedStmt.setString(4, email); 
					 preparedStmt.setString(5, Companyname); 
				 	preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
			 
				 	String newItems = readItems();
					output = "{\"status\":\"success\", \"data\": \"" +
					newItems + "\"}";
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
					System.err.println(e.getMessage()); 
			 } 
			 return output; 
	}	
		
		public String deleteItem(String SellersId )
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
		 			String query = "delete from sellerss where SellersId =?"; 
		 			
		 			PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 			// binding values
		 			preparedStmt.setInt(1, Integer.parseInt(SellersId)); 
		 
		 			// execute the statement
		 			preparedStmt.execute(); 
		 			con.close(); 
		 			
		 			String newItems = readItems();
					output = "{\"status\":\"success\", \"data\": \"" +
					newItems + "\"}";
		  
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
				System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
}