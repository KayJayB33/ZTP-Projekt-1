<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>System Zarządzania Zamówieniami i Produktami</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: dodgerblue">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand">System Zarządzania Zamówieniami i Produktami</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/products" class="nav-link">Produkty</a></li>
            <li><a href="<%=request.getContextPath()%>/orders" class="nav-link">Zamówienia</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">Lista zamówień</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/products/create" class="btn btn-success">Dodaj nowe zamówienie</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Data złożenia zamówienia</th>
                <th>Szczegóły dostawy</th>
                <th>Cena zamówienia</th>
                <th>Status</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>
                        <c:out value="${order.id}" />
                    </td>
                    <td>
                        <c:out value="${order.name}" />
                    </td>
                    <td>
                        <c:out value="${order.createdOn}" />
                    </td>
                    <td>
                        <c:out value="${order.deliveryStrategy.getDeliveryDetails}" />
                    </td>
                    <td>
                        <c:out value="${order.deliveryStatus.getStatus()}" />
                    </td>
                    <td>
                        <fmt:setLocale value="pl_PL"/>
                        <fmt:formatNumber value="${order.price}" type="currency"/>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/products/send?id=<c:out value='${order.id}' />">Wyślij</a>
                        <a href="<%=request.getContextPath()%>/products/update?id=<c:out value='${order.id}' />">Edytuj</a>
                        <a href="<%=request.getContextPath()%>/products/delete?id=<c:out value='${order.id}' />">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>