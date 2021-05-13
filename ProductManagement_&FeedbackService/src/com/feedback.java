
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
	
	public String insertItem(String code, String name) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into feedback (`FID`,`FeedbackID`,`FeedBack`)"+ " values (?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, code); 
		 preparedStmt.setString(3, name); 
		// preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 //preparedStmt.setString(5, desc); 
		// execute the statement3
		 preparedStmt.execute(); 
		 con.close(); 
		 String newfeedbacks = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +
					newfeedbacks + "\"}";
			
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
		 output = "<table border='1'><tr><th>FID</th><th>FeedbackID</th>" +
		 "<th>FeedBack</th>" + 
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from feedback"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String FID = Integer.toString(rs.getInt("FID")); 
		 String FeedbackID = rs.getString("FeedbackID"); 
		 String FeedBack = rs.getString("FeedBack"); 
		
		 // Add into the html table
		 output += "<tr><td>" + FID + "</td>"; 
		 output += "<td>" + FeedbackID + "</td>"; 
		 output += "<td>" + FeedBack + "</td>"; 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='FID' type='hidden' value='" + FID 
		 + "'>" + "</form></td></tr>"; 
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
	
	public String updateItem(String name)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE feedback SET FeedBack=? WHERE FID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 
	
		 preparedStmt.setString(1, name); 
		// preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newfeedbacks = readItems();
			output = "{\"status\":\"Updated successfully\", \"data\": \"" +
					newfeedbacks + "\"}";
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
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from feedback where FID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(FID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newfeedbacks = readItems();
			output = "{\"status\":\"Deleted successfully\", \"data\": \"" +
					newfeedbacks + "\"}";
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the feedback.\"}";
				System.err.println(e.getMessage());
		 } 
		 return output; 
 } 
	
} 