<%@ page import="pl.edu.pk.ztpprojekt1.model.Product" %>
<%@ page import="java.util.List" %>
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
                    <a class="navbar-brand">System Zarządzania Zamówieniami i Produktami</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/products" class="nav-link">Produkty</a></li>
                    <li><a href="<%=request.getContextPath()%>/orders" class="nav-link">Zamówienia</a></li>
                </ul>
            </nav>
        </header>
        <br/>
        <div class="row">
            <div class="container">
                <h3 class="text-center">Lista zamówień</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/orders/create" class="btn btn-success">Dodaj nowe zamówienie</a>
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
                        <th>Zamówione produkty</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>
                                <c:out value="${order.uuid}" />
                            </td>
                            <td>
                                <c:out value="${order.createdOn}" />
                            </td>
                            <td>
                                <c:out value="${order.deliveryStrategy.getDeliveryDetails()}" />
                            </td>
                            </td>
                            <td>
                                <fmt:setLocale value="pl_PL"/>
                                <fmt:formatNumber value="${order.price}" type="currency"/>
                            </td>
                            <td>
                                <c:out value="${order.deliveryStrategy.getDeliveryStatus().getStatus()}" />
                            </td>

                            <td>
                                <c:if test="${order.deliveryStrategy.getDeliveryStatus().getStatus() != 'wysłane'}">
                                <a href="<%=request.getContextPath()%>/orders/send?id=<c:out value='${order.uuid}' />">Wyślij</a>
                                </c:if>
                            </td>
                            <td>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Ilość</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${order.getOrderedProducts()}">
                                            <tr>
                                                <td>
                                                    <c:out value="${item.getKey()}" />
                                                </td>
                                                <td>
                                                    <c:out value="${item.getValue()}" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>