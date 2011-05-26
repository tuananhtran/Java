<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- if cart session variable empty, initialize it --%>
<c:if test="${empty cart}">
    <c:set var="cart" value="<%= new ArrayList()%>" scope="session" />
    <c:set var="total" value="0" scope="session" />
    <c:set var="cart_books" value="<%= new ArrayList()%>" scope="session" />
</c:if>
<%-- if user logged in, redirect to homepage  --%>
<c:if test="${not empty user}">
    <c:redirect url="index.jsp"/>
</c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
        <title>Book Store</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/humanity/jquery-ui-1.8.12.custom.css" />
        <script src="js/jquery-1.5.1.min.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.12.custom.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function(){
                //get and display category list
                $.ajax({
                    type: "GET",
                    url: "ListCategories",
                    dataType: "html",
                    success: function(result) {
                        var dom = $(result);
                        $("#categories").hide().html(dom).fadeIn('def');
                    }
                });

                //format date picker for birthday
                $("#birthday").datepicker();

                //handle fields change event
                $("#email #name #phone #birthday").change(function(){
                    $(this).val(jQuery.trim($(this).val()));
                });
            });

            function validate(){

                //hide all error messages
                $("#loginError").css("display", "none");
                $("#passwordError").css("display", "none");
                $("#passwordError1").css("display", "none");
                $("#passwordError2").css("display", "none");
                $("#emailError").css("display", "none");
                $("#termError").css("display", "none");

                //declare check variable
                var valid = true;

                //if user name is blank, display error
                if($("#login").val().length == 0){
                    $("#loginError").css("display", "block");
                    valid = false;
                }

                //if password is blank, display error
                if($("#password").val().length == 0){
                    $("#passwordError").css("display", "block");
                    valid = false;
                }

                //if password confirmation is blank, display error
                if($("#password_confirmation").val().length == 0){
                    $("#passwordError1").css("display", "block");
                    valid = false;
                }

                //if password fields not matched, display error
                if($("#password").val() != $("#password_confirmation").val()){
                    $("#passwordError2").css("display", "block");
                    valid = false;
                }

                //if email is blank, display error
                if($("#email").val().length == 0){
                    $("#emailError").css("display", "block");
                    valid = false;
                }                

                //if term is not agreed, display error
                if(!$("#term").is(":checked")){
                    $("#termError").css("display", "block");
                    valid = false;
                }
                
                //return check result
                return valid;

            }
        </script>

        <div id="wrap">

            <div class="header">
                <div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
                <div id="menu">
                    <ul>
                        <li><a href="index.jsp">home</a></li>
                        <li><a href="about.jsp">about us</a></li>
                        <li><a href="category.jsp">books</a></li>
                        <li><a href="search.jsp">search</a></li>
                        <li><a href="login.jsp">login</a></li>
                        <li class="selected"><a href="register.jsp">register</a></li>
                        <li><a href="contact.jsp">contact</a></li>
                    </ul>
                </div>


            </div>


            <div class="center_content">
                <div class="left_content">
                    <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Register</div>

                    <div class="feat_prod_box_details">
                        <p class="details">
                            Please fill in these fields to create new account!<br/>
                            All fields are mandatory to complete registration request.
                        </p>

                        <div class="contact_form">
                            <div class="form_subtitle">create new account</div>
                            <form name="register" action="Register" onsubmit="return validate();" method="POST">
                                <span class="red" style="display: none" id="loginError">Username is required!</span>
                                <span class="red" style="display: none" id="passwordError">Password is required!</span>
                                <span class="red" style="display: none" id="passwordError1">Password confirmation is required!</span>
                                <span class="red" style="display: none" id="passwordError2">Password fields not matched!</span>
                                <span class="red" style="display: none" id="emailError">Email is required!</span>
                                <span class="red" style="display: none" id="termError">You must agree to the terms and conditions to continue!</span>
                                <div class="form_row">
                                    <label class="contact"><strong>Username:</strong></label>
                                    <input type="text" class="contact_input" name="login" id="login" />
                                </div>

                                <div class="form_row">
                                    <label class="contact"><strong>Password:</strong></label>
                                    <input type="password" class="contact_input" name="password" id="password" />
                                </div>

                                <div class="form_row">
                                    <label class="contact"><strong>Password: (confirmation)</strong></label>
                                    <input type="password" class="contact_input" id="password_confirmation" />
                                </div>

                                <div class="form_row">
                                    <label class="contact"><strong>Email:</strong></label>
                                    <input type="text" class="contact_input" name="email" id="email" />
                                </div>


                                <div class="form_row">
                                    <label class="contact"><strong>Full name:</strong></label>
                                    <input type="text" class="contact_input" name="name" id="name" />
                                </div>

                                <div class="form_row">
                                    <label class="contact"><strong>Phone:</strong></label>
                                    <input type="text" class="contact_input" name="phone" id="phone" />
                                </div>

                                <div class="form_row">
                                    <label class="contact"><strong>Birthday:</strong></label>
                                    <input type="text" class="contact_input" name="birthday" id="birthday" />
                                </div>

                                <div class="form_row">
                                    <div class="terms">
                                        <input type="checkbox" id="term"/>
                                        I agree to the <a href="terms.html" target="_blank">terms &amp; conditions</a>
                                    </div>
                                </div>


                                <div class="form_row">
                                    <input type="submit" class="register" value="register" />
                                </div>
                            </form>
                        </div>

                    </div>






                    <div class="clear"></div>
                </div><!--end of left content-->

                <div class="right_content">

                    <div class="languages_box">
                        <span class="red">Welcome, Guest!</span>
                    </div>
                    <div class="login">
                        <a class="selected" href="login.jsp">Login</a>
                    </div>


                    <div class="cart">
                        <%-- if user logged in, show cart details --%>
                        <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /></span>My cart</div>
                        <div class="home_cart_content">
                            <c:choose>
                                <c:when test="${not empty cart}">
                                    <%= ((ArrayList) request.getSession().getAttribute("cart")).size()%> x items |
                                    <span class="red">TOTAL: <fmt:formatNumber value="${total}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></span>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="cart" value="<%= new ArrayList()%>" scope="session" />
                                    <c:set var="total" value="0" scope="session" />
                                    <%= ((ArrayList) request.getSession().getAttribute("cart")).size()%> x items |
                                    <span class="red">TOTAL: <fmt:formatNumber value="${total}" type="currency" currencyCode="USD" currencySymbol="$" minFractionDigits="1" maxFractionDigits="2" /></span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a href="cart.jsp" class="view_cart">view cart</a>
                    </div>

                    <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
                    <div class="about">
                        <p>
                            <img src="images/about.gif" alt="" title="" class="right" />
                            Coming soon!<br/><br/><br/><br/><br/>
                        </p>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>Promotions</div>
                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="#">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="#"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div>

                        <ul class="list" id="categories">
                            <img src="images/loading.gif" alt="Loading..." />
                        </ul>

                        <div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Partners</div>

                        <ul class="list">
                            <li><a href="http://www.amazon.com">Amazon.com</a></li>
                            <li><a href="http://www.csscreme.com/">CSS Creme</a></li>
                            <li><a href="http://www.google.com/">Google</a></li>
                            <li><a href="http://www.netbeans.org/">Netbeans</a></li>
                        </ul>

                    </div>


                </div><!--end of right content-->




                <div class="clear"></div>
            </div><!--end of center content-->


            <div class="footer">
                <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free templates"><img src="images/csscreme.gif" alt="free templates" title="free templates" border="0" /></a></div>
                <div class="right_footer">
                    <a href="index.jsp">home</a>
                    <a href="about.jsp">about us</a>
                    <a href="#">services</a>
                    <a href="#">privacy policy</a>
                    <a href="contact.jsp">contact us</a>

                </div>


            </div>


        </div>

    </body>
</html>