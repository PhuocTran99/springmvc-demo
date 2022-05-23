<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/template/login/style.css"/>">
</head>
<body>
    <section style="height: 50vh;">
    <div style="background-image: url(<c:url value="/template/login/clouds-transparent.png"/>); background-attachment: fixed; background-size: cover; width: 100%; height: 100vh; position: relative;"  >
        <div class="baslik">
            <div class="none-click-above"></div>
            <a href="<c:url value="/blog-home"/>">
                <img class="logo" src="https://cdn-icons-png.flaticon.com/256/4359/4359771.png" alt="logo"/>
            </a>
            <div class="none-click-below"></div>
        </div>

        <section>
            <form method="post" action="<c:url value="/j_spring_security_check"/>">
                <div class="arkalogin">
                    <input class="giris" type="text" name="j_username" placeholder="Username">
                    <input class="giris" type="password" name="j_password" placeholder="Password">
                    <input class="butonlogin" type="submit" value="Login">
                </div>
            </form>
            <c:if test="${param.incorrectAccount != null}">
                <h4 style="color: #000000; text-align: center">The username or password is incorrect, please try again!!!</h4>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <h2 style="color: #000000; text-align: center">ACCESS DENIED</h2>
                <h4 style="color: #000000; text-align: center">Please login as admin to reach this page!!!</h4>
            </c:if>
        </section>
    </div>
</section>
</body>
</html>
