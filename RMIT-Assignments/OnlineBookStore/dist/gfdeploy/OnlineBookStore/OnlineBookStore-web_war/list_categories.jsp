<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- loop through all items in category list and show their names --%>
<c:forEach var="category" items="${categories}">
    <li><a href="category.jsp?id=${category.id}&page=1">${category.name}</a></li>
    <span class="hidden">${category.id}</span>
</c:forEach>
