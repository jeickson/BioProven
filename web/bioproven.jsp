<%-- 
    Document   : bioproven
    Created on : 30-ene-2017, 13:57:33
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Bioproven</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/bioproven.css">
</head>
<body>
    <%
        if(session.getAttribute("user")==null){
            response.sendRedirect("index.jsp");
        }
        %>
  <header>
    <img src="img/bosque.jpg" alt="disco">
  </header>
  <nav>
    <ul>
      <li><a href="MainController?actionForm=logout">Logout</a></li>
      <li><a href="MainController?actionForm=createForm">Create Form</a></li>
      <li><a href="MainController?actionForm=searchForm">Seach Form</a></li>
    </ul>
  </nav>
    <main>
        ${formBuilded}
    </main>
</body>
</html>