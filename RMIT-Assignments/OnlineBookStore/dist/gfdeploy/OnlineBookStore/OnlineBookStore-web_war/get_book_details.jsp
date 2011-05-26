<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="crumb_nav">
    <a href="index.jsp">Home</a> &gt;&gt; ${book.title}
</div>
<div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>${book.title}</div>

<div class="feat_prod_box_details">

    <div class="prod_img"><a href="details.jsp?id=${book.id}"><img src="${book.photo}" alt="${book.title}" title="${book.title}" border="0" width="98" /></a>
        <br /><br />
        <a href="${book.photo}" rel="lightbox"><img src="images/zoom.gif" alt="Full resolution image" title="View book image in full resolution" border="0" /></a>
    </div>

    <div class="prod_det_box">
        <div class="box_top"></div>
        <div class="box_center">
            <div class="prod_title">Description</div>
            <p class="details"><%= ((vn.com.onlinebookstore.dto.BookDTO) request.getAttribute("book")).getDescription().split("<br/>|<p class=\"more_details\">")[0]%></p>
            <div class="price"><strong>PRICE:</strong> <span class="red"><fmt:formatNumber value="${book.price}" type="currency" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></span></div>
            <a href="cart.jsp?id=${book.id}" class="more"><img src="images/order_now.gif" alt="Add to cart" title="Order ${book.title}" border="0" /></a>
            <div class="clear"></div>
        </div>

        <div class="box_bottom"></div>
    </div>
    <div class="clear"></div>
</div>

<div id="demo" class="demolayout">

    <ul id="demo-nav" class="demolayout">
        <li><a class="active" href="#tab1">More details</a></li>
        <li><a class="" href="#tab2">Comments</a></li>
    </ul>

    <div class="tabs-container">

        <div style="display: block;" class="tab" id="tab1">

            <ul class="list">
                <li><a></a><b>Title:</b> ${book.title}</li>
                <li><a></a><b>Category:</b> ${category.name}</li>
                <li><a></a><b>Author:</b> ${book.author}</li>
                <li><a></a><b>Publisher:</b> ${book.publisher}</li>
                <li><a></a><b>Publishing date:</b> <fmt:formatDate value="${book.datePublished}" /></li>
                <li><a></a><b>Price:</b> <fmt:formatNumber value="${book.price}" type="currency" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></li>
                <li><a></a><b>Average rating:</b> <fmt:formatNumber value="${book.ratingValue / book.ratingCount}" type="number" minFractionDigits="1" maxFractionDigits="1" /></li>
                <li><a></a><b>Total rating count:</b> ${book.ratingCount}</li>
            </ul>
            <p class="more_details">
                <b>Description:</b><br/>
                ${book.description}
            </p>

        </div>

        <div style="display: none;" class="tab" id="tab2">

            <%-- if user logged in, allow he/she to add new comment --%>
            <c:if test="${not empty user}">
                <script type="text/javascript">
                    var fn = jQuery.noConflict();

                    fn(function(){

                        fn("#increase, #decrease, button").button();

                        fn("#new_comment").dialog({
                            autoOpen: false,
                            modal: true,
                            width: 400,
                            buttons: {
                                "Submit": function(){
                                    fn("#add_new_comment").submit();
                                },
                                "Cancel": function(){
                                    fn(this).dialog("close");
                                }
                            }
                        });
                        
                        fn("#add_comment").click(function(){
                            fn("#new_comment").dialog("open");
                            return false;
                        });

                        fn("#decrease").click(function(){
                            if(fn("#rating").val() > 1){
                                fn("#rating").val(fn("#rating").val() - 1);
                            }
                            return false;
                        });

                        fn("#increase").click(function(){
                            if(fn("#rating").val() < 5){
                                fn("#rating").val(parseInt(fn("#rating").val()) + 1);
                            }
                            return false;
                        });

                    });
                </script>
                <p class="more_details">
                    <button id="add_comment">add comment</button>
                </p>
                <div id="new_comment" title="Add new comment">
                    <form action="AddComment" method="POST" id="add_new_comment">
                        <fieldset>
                            <label for="rating">Rating: </label>
                            <input type="text" value="1" id="rating" name="rating" size="2" readonly="readonly" />
                            <input type="button" id="decrease" value="-" /><input type="button" id="increase" value="+" /><br/>
                            <label for="content">Comment:</label>
                            <textarea cols="45" rows="10"  id="content" name="content"></textarea>
                            <input type="hidden" name="bookId" value="${book.id}" />
                        </fieldset>
                    </form>
                </div>
            </c:if>

            <c:forEach var="comment" items="${comments}" varStatus="status">
                <br/>
                <ul class="list">
                    <li><a></a><b>Author:</b> ${authors[status.index]}</li>
                    <li><a></a><b>Rating:</b> ${comment.rating}</li>
                    <li><a></a><b>Content:</b> ${comment.content}</li>
                    <li><a></a><b>Date:</b> <fmt:formatDate value="${comment.date}" /></li>
                </ul>
            </c:forEach>

        </div>

    </div>

</div>

<%-- format tabbed content --%>
<script type="text/javascript">

    var tabber1 = new Yetii({
        id: 'demo'
    });

</script>
