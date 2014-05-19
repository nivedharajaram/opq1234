<%@ page language="java" 
         contentType="text/html"
         %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Login Page</title>
    </head>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
    <body>
        <div id="container" >
            <form action="UserRegistration" method="POST">

                <%
                    if (request.getAttribute("message") != null) {
                        out.println(request.getAttribute("message"));
                    }                       
                 %>    


                <h2>UserRegistration Form</h2>

                <label>Firstname :</label>	
                <br>
                <input type="text" name="firstname"/><br>		


                <label>Lastname :</label> <br>			
                <input type="text" name="lastname"/><br>		


                <label>Email Id :</label> <br>			
                <input type="text" name="email"/><br>		


                <label>Password :</label> <br>			
                <input type="text" name="password" type="password"/><br>		

                <label>Confirmation Password :</label> <br>			
                <input type="text" name="confirmation" type="confirmation"/><br>		


                <input type="submit" value="submit">			
                </div>
            </form>
    </body>
</html>
