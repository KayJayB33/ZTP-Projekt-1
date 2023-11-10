<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Błąd!</title>
    </head>
    <body>
        <div style="text-align: center;">
            <h1>Błąd!</h1>
            <h2><%=exception.getMessage() %><br/></h2>
        </div>
    </body>
</html>