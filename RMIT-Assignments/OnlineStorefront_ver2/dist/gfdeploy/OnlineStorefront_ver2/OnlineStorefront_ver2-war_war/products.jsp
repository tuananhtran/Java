<%@page import="vn.edu.rmit.s3269999.dto.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping Store - View All Products</title>
    </head>
    <body>
        <jsp:useBean id="productAdmin" class="onlinestorefront.Product" scope="request" />
        <h1>Available To Products In Database</h1>
        <table border="5" cellpadding="5">
            <tr>
                <th bgcolor='lightgray' align='center'>No.</th>
                <th bgcolor='lightgray' align='center'>Name</th>
            </tr>
            <%
                        int count = 1;
                        java.util.Collection products = productAdmin.getAllProducts();
                        java.util.Iterator iterator = products.iterator();

                        while (iterator.hasNext()) {
                            ProductDTO product = (ProductDTO) iterator.next();
            %>
            <tr>
                <td align='right'><%=count%></td>
                <td align='left'>
                    <a href="<%=request.getContextPath()%>\product.jsp?id=<%=product.getId().intValue()%>">
                        <%=product.getName()%>
                    </a>
                </td>
            </tr>
            <%
                            count++;
                        }
            %>
        </table>
        <br />
        <a href="<%=request.getContextPath()%>">Back to Home!</a>
    </body>
</html>
