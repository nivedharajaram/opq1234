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
            <form id="form" action="UserRegistration" method="POST">

                <%
                    if (request.getAttribute("SuccessMsg") != null && request.getAttribute("SuccessMsg") != "" ) {
                         out.println("Registration Succeded");
                        out.println(request.getAttribute("SuccessMsg"));
                    }   
                    if (request.getAttribute("ErrorMsg") != null && request.getAttribute("ErrorMsg") != "") {
                         out.println("Registration Failed");
                        out.println(request.getAttribute("ErrorMsg"));
                    }   
                                        
                 %>    


                <h2>UserRegistration Form</h2>

                <label>Firstname :</label>	
                <br>
                <input id ="firstname" type="text" name="firstname"/><br>		


                <label>Lastname :</label> <br>			
                <input id ="lastname" type="text" name="lastname"/><br>		


                <label>Email Id :</label> <br>			
                <input id ="email" type="text" name="email"/><br>		


                <label>Password :</label> <br>			
                <input id ="password" type="password" name="password" type="password"/><br>		

                <label>Confirmation Password :</label> <br>			
                <input id ="confirmation" type="password" name="confirmation" type="confirmation"/><br>		


                <input id="submit" name="submit" type="submit" value="submit">			
                </div>
            </form>
    </body>
</html>

