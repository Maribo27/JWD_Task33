<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<link href="css/container.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Get Info Page</title>
</head>

<body>
<div class="container">
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="SAX"/>
        <input type="submit" value="SAX"/>
    </form>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="STAX"/>
        <input type="submit" value="StAX"/>
    </form>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="DOM"/>
        <input type="submit" value="DOM"/>
    </form>
</div>
</body>
</html>