package com;

import java.sql.*;
public class investigators {

	///A common method to connect to the DB
		private Connection connect() 
			{ 
		
					Connection con = null; 
		
					try
					{ 
						///Class.forName("com.mysql.jdbc.Driver"); 
						Class.forName("com.mysql.jdbc.Driver");
						
						///Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/investigators", "root", "");
						
						
					} 
					catch (Exception e) 
					{ 
						e.printStackTrace();
					} 
		
					return con; 
			}
		
		public String insertItem(String Fname, String Lname, String email, String contact,String location) 
		{ 

			String output = ""; 

			try
			{ 

				Connection con = connect(); 

				if (con == null) 

				{return "Error while connecting to the database for inserting."; } 

				// creating a prepared statement
				
				String query = " insert into investigatorss (`InvestID`,`FirstName`,`LastName`,`Email`,`ContactNumber`,`Location`)"
							+ " values (?, ?, ?, ?, ?,?)"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				 /// binding values
				
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, Fname); 
				 preparedStmt.setString(3, Lname); 
				 preparedStmt.setString(4, email); 
				 preparedStmt.setString(5, contact); 
				 preparedStmt.setString(6, location); 

				 /// executing the statement
				 
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				 
				output = "Error while inserting the item."; 
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
		 
					/// Prepare the html table to be display
					
					output = "<table border='1'><tr><th>First Name</th>" +
							"<th>Last Name</th>" + 
							"<th>Email</th>" +
							"<th>ContactNumber</th>" +
							"<th>Location</th>" +
							"</tr>"; 
		 
					String query = "select * from investigatorss"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					/// iterate through the rows in the result set
					
					while (rs.next()) 
					{ 
							String InvestID = Integer.toString(rs.getInt("InvestID")); 
							String FirstName = rs.getString("FirstName"); 
							String LastName = rs.getString("LastName"); 
							String Email = rs.getString("Email"); 
							String ContactNumber = rs.getString("ContactNumber");
							String Location = rs.getString("Location"); 
		 
							// Add data into the html table
							
							output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + InvestID
									+ "'>" + FirstName + "</td>";
							//output += "<td>" + FirstName + "</td>"; 
							output += "<td>" + LastName + "</td>"; 
							output += "<td>" + Email + "</td>"; 
							output += "<td>" + ContactNumber + "</td>"; 
							output += "<td>" + Location + "</td>"; 
							
							  output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-itemid='" + InvestID + "'></td>"
					            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
					            		+ "class = 'btnRemove btn btn-danger' data-itemid='" + InvestID + "'>"
					            		+"</td></tr>";
					            		
							
					} 
		 
					con.close(); 
		 
					// Complete the html table .
					
					output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 	output = "Error while reading the items."; 
			 	System.err.println(e.getMessage()); 
		 } 
		 
				return output; 
	} 
		
		public String updateItem(String ID, String Fname, String Lname, String email, String contact , String location)
		{ 
			 String output = ""; 
			 
			 try
			 { 
			 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement .
				 	
				 	String query = "UPDATE investigatorss SET FirstName=?,LastName=?,Email=?,ContactNumber=?,Location=? WHERE InvestID=?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values.
				 	 
					 preparedStmt.setString(1, Fname); 
					 preparedStmt.setString(2, Lname); 
					 preparedStmt.setString(3, email); 
					 preparedStmt.setString(4, contact); 
					 preparedStmt.setString(5, location); 
				 	preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 
				 	// execute the statement.
				 	
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
		
		public String deleteItem(String InvestID)
		{ 
		 	String output = ""; 
		 
		 	try
		 	{ 
		 
		 			Connection con = connect(); 
		 
		 			if (con == null) 
		 			{return "Error while connecting to the database for deleting."; } 
		 
		 			// create a prepared statement
		 			String query = "delete from investigatorss where InvestID=?"; 
		 			
		 			PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 			// binding values
		 			preparedStmt.setInt(1, Integer.parseInt(InvestID)); 
		 
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