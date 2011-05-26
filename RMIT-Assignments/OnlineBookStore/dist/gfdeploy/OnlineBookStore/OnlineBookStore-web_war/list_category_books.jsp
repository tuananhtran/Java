<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" uri="/WEB-INF/tlds/tag_libs" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="crumb_nav">
    <a href="index.jsp">Home</a> &gt;&gt;
    <c:choose>
        <c:when test="${not empty category}">
            ${category.name} books
        </c:when>
        <c:otherwise>
            all books
        </c:otherwise>
    </c:choose>
</div>
<div class="title">
    <span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>
        <c:choose>
            <c:when test="${not empty category}">
                ${category.name} books
        </c:when>
        <c:otherwise>
            All books
        </c:otherwise>
    </c:choose>
</div>

<div class="new_products">

    <my:BookListTag books="${books}" page="${page}">
        <div class="new_prod_box">
            <a href="details.jsp?id=${book.id}">${book.title}</a>
            <div class="new_prod_bg">
                <a href="details.jsp?id=${book.id}"><img src="${book.photo}" alt="" title="${book.title}" class="thumb" border="0" /></a>
            </div>
        </div>
    </my:BookListTag>

    <my:PaginationTag page="${page}" numberOfPages="${numberOfPages}" URL="${URL}" />

</div>