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
            <th>Versions</th>
            <th>Analogs</th>
        </tr>
        <c:forEach items="${requestScope.medicine}" var="med" begin="${requestScope.begin}" end="${requestScope.end}">
            <tr>
                <td><c:out value="${med.name}"/></td>
                <td><c:out value="${med.pharm}"/></td>
                <td><c:out value="${med.group}"/></td>

                <td>
                    <c:forEach items="${med.analogs}" var="analog">
                        <c:out value="${analog}"/><br>
                    </c:forEach>
                </td>

                <td>
                <c:forEach items="${med.versions}" var="version">
                    type: <c:out value="${version.type}"/><br>
                    package type: <c:out value="${version.packageType}"/><br>
                    count: <c:out value="${version.count}"/><br>
                    price: <c:out value="${version.price}"/><br>
                    dosage: <c:out value="${version.dosage}"/><br>
                    pharmacy sale: <c:out value="${version.pharmacySale}"/><br>
                </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="${requestScope.parser}"/>
        <input type="submit" name="page" value="${requestScope.first}"/>
    </form>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="${requestScope.parser}"/>
        <input type="submit" name="page" value="${requestScope.prev}"/>
    </form>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="${requestScope.parser}"/>
        <input type="submit" name="page" value="${requestScope.next}"/>
    </form>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="${requestScope.parser}"/>
        <input type="submit" name="page" value="${requestScope.last}"/>
    </form>
    <form action="../../index.jsp">
        <p id="button"> <input type="submit" value="Return" /></p>
    </form>
</div>
</body>
</html>