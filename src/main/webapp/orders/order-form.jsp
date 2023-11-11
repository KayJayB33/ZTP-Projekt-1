<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<script>
    function checkDeliveryType() {
        const selectBox = document.getElementById("delivery-type");
        const selected = selectBox.value;
        const parcelLockerFieldset = document.getElementById("parcel-locker-fieldset");
        if (selected === 'parcel-delivery') {
            parcelLockerFieldset.style.display = "block";
        } else {
            parcelLockerFieldset.style.display = "none";
        }
    }
    window.onload = checkDeliveryType
</script>

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
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <form action="create" method="post">
                    <caption>
                        <h2>Dodaj nowe zamówienie</h2>
                    </caption>

                    <fieldset class="form-group">
                        <label for="delivery-type">Sposób dostawy</label>
                        <select id="delivery-type" name="delivery-type" class="form-control" required="required" onchange="checkDeliveryType()">
                            <option value="post-delivery">Poczta Polska</option>
                            <option value="parcel-delivery">Paczkomat</option>
                        </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Adresat</label> <input type="text" class="form-control" name="addressee" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Adres</label> <input type="text" class="form-control" name="address" required="required">
                    </fieldset>

                    <fieldset class="form-group" id="parcel-locker-fieldset">
                        <label>Paczkomat</label> <input type="text" class="form-control" name="parcel-locker-id">
                    </fieldset>


                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nazwa</th>
                                <th>Cena</th>
                                <th>Dostępna ilość</th>
                                <th>Ilość</th>
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
                                    <td>
                                        <fmt:setLocale value="pl_PL"/>
                                        <fmt:formatNumber value="${product.price}" type="currency"/>
                                    </td>
                                    <td>
                                        <c:out value="${product.availableQuantity}" />
                                    </td>
                                    <td>
                                        <input name="${product.id}-quantity" type="number" value="0" min="0" max="${product.availableQuantity}" class="form-control" required="required">
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <button type="submit" class="btn btn-success">Zapisz</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>