<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${product != null}">
                        <form action="update" method="post">
                    </c:if>
                    <c:if test="${product == null}">
                        <form action="create" method="post">
                    </c:if>
                        <caption>
                            <h2>
                                <c:if test="${product != null}">
                                    Edytuj produkt
                                </c:if>
                                <c:if test="${product == null}">
                                    Dodaj nowy produkt
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${product != null}">
                            <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Nazwa</label> <input type="text" value="<c:out value='${product.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Opis</label> <input type="text" value="<c:out value='${product.description}' />" class="form-control" name="description" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Cena</label>
                            <input type="number" value="<c:out value='${product.price}' />" class="form-control" name="price" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Dostępna ilość</label> <input type="number" value="<c:out value='${product.availableQuantity}' />" class="form-control" name="quantity" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Zapisz</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<style>
    .currency-wrap{
        position:relative;
    }

    .currency-code{
        position:absolute;
        left:8px;
        top:10px;
    }

    .price{
        padding:10px 20px;
        border:solid 1px #ccc;
        border-radius:5px;
    }
</style>