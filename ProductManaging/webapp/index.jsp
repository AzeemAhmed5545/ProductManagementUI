<%@ page import ="model.Product" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Managment</title>
<link rel="stylesheet" href="View/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Product Management</h1>
				<form id="formItem" name="formItem">
					Product ID: <input id="productID" name="productID" type="text"
						class="form-control form-control-sm"> <br> Seller
					Name: <input id="sellerName" name="sellerName" type="text"
						class="form-control form-control-sm"> <br> Product
					price: <input id="productprice" name="productprice" type="text"
						class="form-control form-control-sm"> <br> Product
					Description: <input id="productDesc" name="productDesc" type="text"
						class="form-control form-control-sm"> <br> <input
						
						id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
				 <%
				 Product itemObj = new Product();
                   out.print(itemObj.readItems());
                  
                  %>
                     
                 </div>
			</div>
		</div>
	</div>
</body>
</html>















