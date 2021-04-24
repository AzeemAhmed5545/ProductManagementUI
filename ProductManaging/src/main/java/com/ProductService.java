package com;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Product;


@Path("/Items")
public class ProductService {
	
	Product itemObj = new Product();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("productID") String productID,
	@FormParam("sellerName") String sellerName,
	@FormParam("productprice") String productprice,
	@FormParam("productDesc") String productDesc)
	{
	String output = itemObj.insertItem(productID, sellerName, productprice, productDesc);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String sellerID = itemObject.get("sellerID").getAsString();
	String productID = itemObject.get("productID").getAsString();
	String sellerName = itemObject.get("sellerName").getAsString();
	String productprice = itemObject.get("productprice").getAsString();
	String productDesc = itemObject.get("productDesc").getAsString();
	String output = itemObj.updateItem(sellerID, productID, sellerName, productprice, productDesc);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String sellerID = doc.select("sellerID").text();
	String output = itemObj.deleteItem(sellerID);
	return output;
	}
}
