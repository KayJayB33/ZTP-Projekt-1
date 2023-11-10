<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
        <br>
        <h1>Witaj w systemie do zarządzania zamówieniami i produktami!</h1>
        <h5>Autor: Krzysztof Bętkowski</h5>
    </body>
</html>