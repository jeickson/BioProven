<%-- 
    Document   : index.jsp
    Created on : 20-ene-2017, 17:13:47
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if(session.getAttribute("user")!=null){
            response.sendRedirect("bioproven.jsp");
        }
        %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BioProven</title>
        <link href="css/reset.css" rel="stylesheet" />  
        <link href="css/index.css" rel="stylesheet" />  
    </head>
    <body>
        <header>
            <h1 id="logo">BioProven</h1>
            <form action="MainController" method="post" >
                <h1 class="formTitle">Login</h1>
                <label for="nickLogin">Nick:</label>
                <input type="text" name="nickLogin" required />
                <label for="passwdLogin">Password:</label>
                <input type="password" name="passwdLogin" required/>
                <input type="submit" name="initBotton" value="Login"/>
                 <%
                     if(request.getAttribute("message")!=null){
                           out.println(request.getAttribute("message"));
                        }
                 %>
            </form>
        </header>
        <main>
            <img src="img/ADN.jpeg"/>
            <aside>
                <h1>Or</h1>
                <h1 class="formTitle">Register Now!</h1>
                
                <form action="MainController" method="post">
                    <ul>
                         <li>
                            <%
                                if(request.getAttribute("stringResult")!=null){
                                    out.println(request.getAttribute("stringResult"));
                                }
                                %>
                        </li>
                        <li class="text">
                            <label for="user"><b>User*:</b></label>
                            <input type="text" name="user" id="user" required/>
                        </li>
                        <li class="text">
                            <label for="passwd"><b>Password*:</b></label>
                            <input type="password" name="passwd" id="passwd" required/>
                        </li>  
                        <li class="text">
                            <label for="passwd2"><b>Confirm Password*:</b></label>
                            <input type="password" name="passwd2" id="passwd2" required/>
                        </li >
                        <li class="text">
                            <label for="name"><b>Name*:</b></label>
                            <input type="text" name="name" required/>
                        </li>
                        <li class="text">
                            <label for="surname"><b>Surname*:</b></label>
                             <input type="text" name="surname" required/>
                        </li>
                        <li class="text">
                            <label for="dni"><b>DNI*:</b></label>
                            <input type="text" name="dni" required/>
                        </li>
                        <li class="text">
                            <label for="email"><b>E-mail*:</b></label>
                            <input type="text" name="email"  required/>
                        </li>
                        <li class="text">
                            <label for="date"><b>Birthday Date*:(yyyy-mm-dd)</b></label>
                            <input type="text" name="date" required/>
                        </li>
                        <li class="text">
                             <label for="business">Business:</label>
                             <input type="text" name="business"/>
                        </li>
                        <li class="text">
                             <label for="depart"><b>Department*:</b></label>
                                <input type="text" name="depart" required/>
                        </li>
                        <li>
                            <label for=""><b>Sex*:</b></label>
                             <input type="radio" name="sexe" value="male" checked  > Male
                             <input type="radio" name="sexe" value="female" /> Female
                        </li>
                        <li>
                            <label for="interests"><b>Professional Interests*:</b></label><br><br>
                            <input type="checkbox" name="interests" value="medicine"/>Medicine
                            <input type="checkbox" name="interests" value="investigation"/>Investigation
                            <input type="checkbox" name="interests" value="education"/>Education
                        </li>
                        <li id="liReg">
                            <input type="submit" name="initBotton" value="Register" id="register"/>
                        </li>
                       
                    </ul>                                 
                </form>
            </aside>
        </main>
    </body>
</html>
