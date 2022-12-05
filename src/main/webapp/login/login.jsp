<%--
  Created by IntelliJ IDEA.
  User: SON VUONG
  Date: 12/2/2022
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style-login.css">
    <link rel="stylesheet" href="bootstrap.login.js">
</head>
<body>
<div class="overlay">
    <form action="${pageContext.request.contextPath}/login?action=login" method="post">
        <div class="con">
            <header class="head-form">
                <h2>Log In</h2>
                <p>login here using your username and password</p>
            </header>
            <br>
            <div class="field-set">
                <span class="input-item">
           <i class="fa fa-user-circle"></i>
         </span>
                <input class="form-input" id="txt-input" type="text" name="account" placeholder="UserName">
                <p class="text-danger m-0 p-0" style="font-size: 14px"><c:if test="${not empty messageLoginAccount}">
                    <c:out value="${messageLoginAccount}"/>
                    <c:set var="messageLoginAccount" value="" scope="session"/>
                </c:if>
                </p>
                <br>
                <span class="input-item">
        <i class="fa fa-key"></i>
       </span>
                <input class="form-input" type="password" placeholder="Password" id="pwd"  name="password">
                <p class="text-danger m-0 p-0" style="font-size: 14px"><c:if test="${not empty messageLoginPassword}">
                    <c:out value="${messageLoginPassword}"/>
                    <c:set var="messageLoginPassword" value="" scope="session"/>
                </c:if>
                </p>
                <br>
                <button class="log-in"> Log In </button>
            </div>
            <div class="other">
                <button class="btn submits frgt-pass">Forgot Password</button>
                <a href="http://localhost:8080/register/register.jsp" style="text-decoration: none;color:black"><button type="button" class="btn submits">Sign Up<i class="fa fa-user-plus" aria-hidden="true"></i></button></a>


            </div>
        </div>
    </form>
</div>
</body>
</html>
