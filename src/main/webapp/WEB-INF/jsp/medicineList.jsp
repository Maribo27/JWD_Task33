<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<link href="../../css/table.css" rel="stylesheet">
<link href="../../css/button.css" rel="stylesheet">
<head>
    <title>User data</title>
</head>
<body>
<div class="table-container">
    <table>
        <tr>
            <th>Name</th>
            <th>Pharm</th>
            <th>Group</th>
        </tr>
        <c:forEach items="${requestScope.medicine}" var="med">
            <tr>
                <td><c:out value="${med.name}"/></td>
                <td><c:out value="${med.pharm}"/></td>
                <td><c:out value="${med.group}"/></td>
            </tr>
        </c:forEach>

    </table>
    <form action="../../index.jsp">
        <p id="button"> <input type="submit" value="Return" /></p>
    </form>
</div>
</body>
</html>