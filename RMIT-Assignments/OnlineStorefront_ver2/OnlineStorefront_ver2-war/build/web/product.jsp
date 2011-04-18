<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping Store - View Product Details</title>
    </head>
    <body>
        <jsp:useBean id="productAdmin" class="onlinestorefront.Product" scope="request" />
        <h1>Product Information</h1>        
        <%
                    vn.edu.rmit.s3269999.dto.ProductDTO product =
                            productAdmin.getProduct(new Long(request.getParameter("id")));
        %>
        <ul>
            <li><b>ID:</b> <%=product.getId().longValue()%></li>
            <li><b>Name:</b> <%=product.getName()%></li>
            <li><b>Price:</b> <%=product.getPrice().doubleValue()%></li>
            <li><a href="<%=request.getContextPath()%>\shopping_cart.jsp?id=<%=product.getId().longValue()%>">Add to cart!</a></li>
        </ul>
        <br />
        <a href="<%=request.getContextPath()%>">Back to Home!</a>
    </body>
</html>
