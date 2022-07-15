
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserInfoPage</title>
</head>
<body>
<form action="./rm/userinfo" method="post">
    Name:<input name="name" /><br />
    Age:<input name="age" /><br />
    Phone1:<input name="phones[0]" /><br />
    Phone2:<input name="phones[1]" /><br />
    Phone3:<input name="phones[2]" /><br />
    <button type="submit">Submit</button>
</form>
</body>
</html>
