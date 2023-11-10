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
            <h3 class="text-center">Lista produktów</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/products/create" class="btn btn-success">Dodaj nowy produkt</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nazwa</th>
                        <th>Opis</th>
                        <th>Cena</th>
                        <th>Dostępna ilość</th>
                        <th>Akcje</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>
                                <c:out value="${product.id}" />
                            </td>
                            <td>
                                <c:out value="${product.name}" />
                            </td>
                            <td style="text-align: justify">
                                <c:out value="${product.description}" />
                            </td>
                            <td>
                                <fmt:setLocale value="pl_PL"/>
                                <fmt:formatNumber value="${product.price}" type="currency"/>
                            </td>
                            <td>
                                <c:out value="${product.availableQuantity}" />
                            </td>
                            <td>
                                <a href="<%=request.getContextPath()%>/products/update?id=<c:out value='${product.id}' />">Edytuj</a>
                                <a href="<%=request.getContextPath()%>/products/delete?id=<c:out value='${product.id}' />">Usuń</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </body>
</html>