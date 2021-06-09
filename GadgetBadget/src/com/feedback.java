
/**
 * 
 */
package com;

/**
 * @author Hasantha
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 


public class feedback 
{ //A common method to connect to the DB
	private Connection connect() 
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
	 } 
	
	public String insertItem(String cusid, String itemid, String feedbackid, String feed) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into feedback(`FID`,`CustomerID`,`ItemID`,`FeedbackID`,`FeedBack`)"+ " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cusid);
			preparedStmt.setString(3, itemid);
			preparedStmt.setString(4,feedbackid);
			preparedStmt.setString(5, feed);
		// preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 //preparedStmt.setString(5, desc); 
		// execute the statement3
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +
					newItems + "\"}";
			
		}
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the feedback.\"}";
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
		 output = "<table border='1'><tr><th>CustomerID</th>" +
				
				 "<th>ItemID</th>" + 
				 "<th>FeedbackID</th>" + 
		 "<th>FeedBack</th>" + 
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from feedback"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String FID = Integer.toString(rs.getInt("FID")); 
		 String CustomerID = rs.getString("CustomerID"); 
		 String ItemID = rs.getString("ItemID"); 
		 String FeedbackID = rs.getString("FeedbackID"); 
		 String FeedBack = rs.getString("FeedBack"); 
		
		 // Add into the html table
		
		 
		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + FID
					+ "'>" + CustomerID + "</td>";
					output += "<td>" + ItemID + "</td>";
					output += "<td>" + FeedbackID + "</td>";
					output += "<td>" + FeedBack + "</td>";
		 
		 // buttons
					  output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-feedbackid='" + FID + "'></td>"
			            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
			            		+ "class = 'btnRemove btn btn-danger' data-feedbackid='" + FID + "'>"
			            		+"</td></tr>";
			}
			
			con.close();
			
			// Complete the html table
		
	
			output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the feedbacks.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


		public String updateItem(String fid, String cusid, String itemid, String feedbackid, String feed)
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
						
						String query = "UPDATE feedback SET CustomerID=?,ItemID=?,FeedbackID=?,FeedBack=?WHERE FID=?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						preparedStmt.setString(1, cusid);
						preparedStmt.setString(2, itemid);
						preparedStmt.setString(3, feedbackid);
						preparedStmt.setString(4, feed);
						preparedStmt.setInt(5, Integer.parseInt(fid));
						// execute the statement
						preparedStmt.execute();
						
						con.close();
						
						String newItems = readItems();
						output = "{\"status\":\"success\", \"data\": \"" +
						newItems + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the feedback.\"}";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		
	public String deleteItem(String FID)
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
				
				String query = "delete from feedback where FID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(FID));
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