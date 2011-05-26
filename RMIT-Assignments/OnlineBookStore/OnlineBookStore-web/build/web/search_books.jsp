<%@taglib prefix="my" uri="/WEB-INF/tlds/tag_libs" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<my:BookListTag books="${books}" page="${page}">
    <div class="new_prod_box">
        <a href="details.jsp?id=${book.id}">${book.title}</a>
        <div class="new_prod_bg">
            <a href="details.jsp?id=${book.id}"><img src="${book.photo}" alt="" title="${book.title}" class="thumb" border="0" /></a>
        </div>
    </div>
</my:BookListTag>

<my:PaginationTag page="${page}" numberOfPages="${numberOfPages}" URL="${URL}" />