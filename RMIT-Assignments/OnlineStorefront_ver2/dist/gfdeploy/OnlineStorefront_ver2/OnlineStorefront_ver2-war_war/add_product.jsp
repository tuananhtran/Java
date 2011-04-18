<%@page import="vn.edu.rmit.s3269999.dto.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping Store - Add Product To Database</title>
    </head>
    <body>
        <jsp:useBean id="productAdmin" class="onlinestorefront.Product" scope="request" />
        <h1>Add New Product</h1>
        <form method="post" action="#">
            <fieldset style="float:left">
                <legend>Product Details</legend>

                <label for="name">Name:</label>
                <input type="text" name="name" id="name" size="30"
                       <%
                                   ProductDTO product = new ProductDTO();
                                   String name = request.getParameter("name");
                                   String price = request.getParameter("price");

                                   if (request.getMethod().equals("POST")) {
                                       if (name.isEmpty()) {
                       %>
                       /><span style='color:red'> *Product name is required!</span>
                <% } else if (name.matches("^[-+]?\\d*(\\.\\d+)?$")) {%>
                /><span style='color:red'> *Product name cannot be numeric!</span>
                <% } else {%>
                value="<%=name%>" />
                <% product.setName(name);
                                                       }
                                                   } else {%>
                />
                <% }%>
                <br />
                <label for="price">Price:</label>
                <input type="text" name="price" id="price" size="10"
                       <%
                                   if (request.getMethod().equals("POST")) {
                                       if (price.isEmpty()) {
                       %>
                       /><span style='color:red'> *Product price is required!</span>
                <% } else if (!price.matches("^[-+]?\\d*(\\.\\d+)?$")) {%>
                value="<%=price%>" /><span style='color:red'> *Product price must be numeric!</span>
                <% } else {
                                                           double productPrice = Double.parseDouble(price);
                                                           if (productPrice <= 0) {
                %>
                value="<%=price%>" /><span style='color:red'> *Product price must be a positive number!</span>
                <% } else {%>
                value="<%=price%>" />
                <% product.setPrice(new Double(price));
                                                           }
                                                       }
                                                   } else {%>
                />
                <% }%>
                <br />
                <input type="submit" value="Add new product!" />
            </fieldset>
        </form>
        <br style="float:left;clear:left" />
        <%
                    if (product.getName() != null & product.getPrice() != null) {
                        productAdmin.addProduct(product);
        %>
        <p>Successfully added product with name <%=name%> and price <%=price%>!</p>
        <% }%>
        <br />
        <a href="<%=request.getContextPath()%>">Back to Home</a>
    </body>
</html>
