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
 * Servlet implementation class PayeeAPI
 */
@WebServlet("/PayeeAPI")
public class PayeeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Payee payObj = new Payee();
	
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
    public PayeeAPI() {
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
		
		String output = payObj.insertItem(id,
				request.getParameter("cardType"),
				 request.getParameter("nameOnCard"), 
				 request.getParameter("cardNo"),
				 request.getParameter("expireDate"),
				 request.getParameter("cvv"));
		
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = payObj.updateItem(paras.get("hidItemIDSave").toString(), 
							paras.get("cardType").toString(), 
							paras.get("nameOnCard").toString(), 
							paras.get("cardNo").toString(),
							paras.get("expireDate").toString(),
							paras.get("cvv").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		
		String output = payObj.deleteItem(paras.get("accountID").toString()); 
		
		response.getWriter().write(output);
	}

}
