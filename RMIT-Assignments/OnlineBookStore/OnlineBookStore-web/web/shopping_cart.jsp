<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(document).ready(function(){
        //update buttons
        $("button").button().click(function(){
            var dom = $("input#" + $(this).attr("id"));
            
            if($(this).attr("class").indexOf("decrease") > -1){
                if(dom.val() > 1){
                    dom.val(dom.val() - 1);
                }
            } else {
                dom.val(parseInt(dom.val()) + 1);
            }
        });
        
        $("#update").click(function(){
            var update = "";

            $(".info").each(function(){
                if($(this).attr("type") == "hidden" || $(this).attr("type") == "text"){
                    update += $(this).val();
                } else {
                    update += $(this).attr("checked");
                }
                if($(this).index(".info") < $(".info").size() - 1){
                    update += ",";
                }
            });

            //if update string not empty, update shopping cart details
            if(update.length > 0){
                window.location = 'UpdateCart?update=' + update;
            }
        });
    });
</script>

<table class="cart_table">
    <tr class="cart_title">
        <td>Title</td>
        <td>Unit price</td>
        <td>Quantity</td>
        <td>Total</td>
        <td>Remove?</td>
    </tr>

    <%-- loop through each order line in the cart to add item to cart table --%>
    <c:forEach var="line" items="${cart}" varStatus="status">
        <input type="hidden" class="info" value="${line.bookId}" id="a${status.index}" />
        <tr>
            <td>${cart_books[status.index].title}</td>
            <td><fmt:formatNumber value="${line.unitPrice}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></td>
            <td><button id="q${status.index}" class="decrease">-</button><input type="text" class="info" value="<fmt:formatNumber value="${line.quantity}" type="number" />" id="q${status.index}" size="3" readonly="readonly" /><button id="q${status.index}" class="increase">+</button></td>
            <td><fmt:formatNumber value="${line.unitPrice * line.quantity}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></td>
            <td><input type="checkbox" class="info" /></td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="3" class="cart_total"><span class="red">TOTAL:</span></td>
        <td><fmt:formatNumber value="${total}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></td>
        <td/>
    </tr>

</table>

<a href="category.jsp" class="continue">&lt; continue</a>
<a href="#" class="update" id="update">update</a>
<a href="checkout.jsp" class="checkout">checkout &gt;</a>