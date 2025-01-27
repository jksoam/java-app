<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello Web App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Hello, ${username}!</h1>
        <p>Current time: ${currentTime}</p>
        <form action="${pageContext.request.contextPath}/hello" method="get">
            <input type="text" name="name" placeholder="Enter your name">
            <button type="submit">Greet Me</button>
        </form>
    </div>
</body>
</html>
