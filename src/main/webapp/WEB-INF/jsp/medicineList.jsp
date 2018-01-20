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
            <th>ID</th>
            <th>Название</th>
            <th>Фирма</th>
            <th>Группа</th>
            <th>Аналоги</th>
            <th>Версии</th>
        </tr>

        <c:forEach items="${requestScope.medicine}" var="med">
            <tr>
                <td><c:out value="${med.id}"/></td>
                <td><c:out value="${med.name}"/></td>
                <td><c:out value="${med.pharm}"/></td>
                <td><c:out value="${med.group}"/></td>

                <td>
                    <c:forEach items="${med.analogs}" var="analog">
                        <c:out value="${analog}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <c:set var = "last" scope = "request" value = "${med.versions.size() - 1}"/>
                    <c:forEach items="${med.versions}" var="version">
                        <c:out value="${version.type}"/><br>
                        Упаковка: <c:out value="${version.packageType}"/><br>
                        Количество: <c:out value="${version.count}"/><br>
                        Цена: <c:out value="${version.price}"/><br>
                        Дозировка: <c:out value="${version.dosage}"/><br>
                        Отпуск из аптеки: <c:out value="${version.pharmacySale}"/><br>
                        <c:if test = "${last > 0}">
                            <br>
                            <c:set var = "last" scope = "request" value = "${last - 1}"/>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>

    </table>

    <c:choose>

        <c:when test = "${requestScope.current.size <= requestScope.current.page}">
        </c:when>

        <c:when test = "${requestScope.current.next == 1}">
            Страница: <c:out value="${requestScope.current.next}"/>/<c:out value="${requestScope.current.last + 1}"/>
            <form action="Controller" method="get">
                <input type="submit" name="page" value="${requestScope.current.next + 1}"/>
            </form>
            <form action="Controller" method="get">
                <input type="hidden" name="page" value="${requestScope.current.last + 1}"/>
                <input type="submit" value="На последнюю страницу"/>
            </form>
        </c:when>

        <c:when test = "${requestScope.current.next == requestScope.current.last + 1}">
            <form action="Controller" method="get">
                <input type="hidden" name="page" value="${requestScope.current.first + 1}"/>
                <input type="submit" value="На первую страницу"/>
            </form>
            <form action="Controller" method="get">
                <input type="submit" name="page" value="${requestScope.current.prev + 1}"/>
                Страница: <c:out value="${requestScope.current.next}"/>/<c:out value="${requestScope.current.last + 1}"/>
            </form>
        </c:when>

        <c:otherwise>
            <form action="Controller" method="get">
                <input type="hidden" name="page" value="${requestScope.current.first + 1}"/>
                <input type="submit" value="На первую страницу"/>
            </form>
            <form action="Controller" method="get">
                <input type="submit" name="page" value="${requestScope.current.prev + 1}" />
                Страница: <c:out value="${requestScope.current.next}"/>/<c:out value="${requestScope.current.last + 1}"/>
                <input type="submit" name="page" value="${requestScope.current.next + 1}"/>
            </form>
            <form action="Controller" method="get">
                <input type="hidden" name="page" value="${requestScope.current.last + 1}"/>
                <input type="submit" value="На последнюю страницу"/>
            </form>
        </c:otherwise>
    </c:choose>
    <form action="../../index.jsp">
        <p id="button"> <input type="submit" value="Return" /></p>
    </form>
</div>
</body>
</html>