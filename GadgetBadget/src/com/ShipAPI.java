package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShipAPI
 */
@WebServlet("/ShipAPI")
public class ShipAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Ship shipObj = new Ship();
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				String queryString = scanner.hasNext() ? 
						scanner.useDelimiter("\\A").next() : ""; 
				scanner.close(); 
	 
				String[] params = queryString.split("&"); 
				for (String param : params) 
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				} 
		} 
		catch (Exception e) 
		{ 
		} 
		return map; 
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShipAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("accountID"));
		
		String output = shipObj.insertItem(id,
				request.getParameter("firstName"),
				 request.getParameter("lastName"), 
				 request.getParameter("userName"),
				 request.getParameter("email"),
				 request.getParameter("address1"),
				 request.getParameter("address2"),
				 request.getParameter("country"),
				 request.getParameter("state"),
				 request.getParameter("zipCode"));
		
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map paras = getParasMap(request);
		
		String output = shipObj.updateItem(paras.get("hidItemIDSave").toString(), 
							paras.get("firstName").toString(), 
							paras.get("lastName").toString(), 
							paras.get("userName").toString(),
							paras.get("email").toString(),
							paras.get("address1").toString(),
							paras.get("address2").toString(),
							paras.get("country").toString(),
							paras.get("state").toString(),
							paras.get("zipCode").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map paras = getParasMap(request); 
		
		String output = shipObj.deleteItem(paras.get("accountID").toString()); 
		
		response.getWriter().write(output);
	}

}
