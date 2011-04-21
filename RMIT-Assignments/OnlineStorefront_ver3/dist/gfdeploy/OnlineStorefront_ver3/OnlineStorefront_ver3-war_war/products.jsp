<%@page import="javax.rmi.PortableRemoteObject"%>
<%@page import="vn.edu.rmit.s3269999.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping Store - View All Products</title>
    </head>
    <body>
        <%
                    Object lookup = new InitialContext().lookup("ejb/Management");
                    ManagementRemoteHome productHome =
                            (ManagementRemoteHome) PortableRemoteObject.narrow(
                            lookup, ManagementRemoteHome.class);
                    ManagementRemote productAdmin = productHome.create();
        %>
        <h1>Available To Products In Database</h1>
        <%@taglib prefix="my" uri="/WEB-INF/tlds/custom_tags.tld"%>
        <my:ListProductsTag products="<%=productAdmin.getAllProducts()%>" name="name" order="ascending" />
        <br />
        <a href="<%=request.getContextPath()%>">Back to Home!</a>
    </body>
</html>
