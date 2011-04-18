<%@page import="vn.edu.rmit.s3269999.dto.ProductDTO"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping Store - View Shopping Cart</title>
    </head>
    <body>
        <jsp:useBean id="productAdmin" class="onlinestorefront.Product" scope="request" />
        <h1>Your Shopping Cart</h1>
        <table border="5" cellpadding="5">
            <tr>
                <th bgcolor="lightgray" align="center">No.</th>
                <th bgcolor="lightgray" align="center">Product Name</th>
                <th bgcolor="lightgray" align="center">Price</th>
            </tr>
            <%
                        if (session.getAttribute("cart") == null) {
                            session.setAttribute("cart", new java.util.ArrayList());
                        }
                        Collection cart = (Collection) session.getAttribute("cart");

                        int counter = 1;
                        double sum = 0;
                        ProductDTO newProduct = null;

                        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                            Long newProductId = new Long(request.getParameter("id"));
                            newProduct = productAdmin.getProduct(newProductId);
                            if (newProduct != null) {
                                cart.add(newProductId);
                            }
                        }

                        java.util.Iterator iterator = cart.iterator();
                        while (iterator.hasNext()) {
                            ProductDTO product = productAdmin.getProduct((Long) iterator.next());
            %>
            <tr>
                <td align = "right" ><%=counter%></td>
                <td align="left"><%=product.getName()%></td>
                <td align="right">$<%=product.getPrice().doubleValue()%></td>
            </tr>
            <%
                            counter++;
                            sum += product.getPrice().doubleValue();
                        }
            %>
            <tr>
                <th bgcolor="lightgray" align="center">#</th>
                <th bgcolor="lightgray" align="right">Total</th>
                <th bgcolor="yellow" align="right">$<%=sum%></th>
        </table>
        <br />
        <%
                    if (newProduct != null) {
        %>
        Successfully added product with name <%=newProduct.getName()%>
        and price $<%=newProduct.getPrice().doubleValue()%> to cart!
        <% }%>
        <br />
        <a href="<%=request.getContextPath()%>">Back to Home!</a>
    </body>
</html>
