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

import model.Product;

/**
 * Servlet implementation class ProductServiceAPI
 */
@WebServlet("/ProductServiceAPI")
public class ProductServiceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Product itemObj = new Product();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServiceAPI() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String output = itemObj.insertItem(request.getParameter("productID"),
		request.getParameter("sellerName"),
		request.getParameter("productprice"),
		request.getParameter("productDesc"));
		response.getWriter().write(output);
		}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
		Map paras = getParasMap(request);
		
		String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(),
		paras.get("productID").toString(),
		paras.get("sellerName").toString(),
		paras.get("productprice").toString(),
		paras.get("productDesc").toString());
		
		response.getWriter().write(output);
	
		
		
	}

	@SuppressWarnings("rawtypes")
	private Map getParasMap(HttpServletRequest request)
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
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@SuppressWarnings("rawtypes")
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		{
		Map paras = getParasMap(request);
		String output = itemObj.deleteItem(paras.get("sellerID").toString());
		response.getWriter().write(output);
		}
	}

}
