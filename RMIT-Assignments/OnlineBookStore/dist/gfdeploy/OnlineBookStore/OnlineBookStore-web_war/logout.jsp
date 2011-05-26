<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- if user logged in, remove all session variables --%>
<c:if test="${not empty user}">

    <c:remove var="user" scope="session" />
    <c:remove var="cart" scope="session" />
    <c:remove var="total" scope="session" />
    <c:remove var="manager" scope="session" />

    <script type="text/javascript">
        <%-- display confirmation message --%>
            alert("Successfully logged out!");
    </script>

</c:if>

<script type="text/javascript">
    <%-- redirect to homepage --%>
        window.location = "index.jsp";
</script>