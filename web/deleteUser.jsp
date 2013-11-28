<%-- 
    Document   : addUser
    Created on : Nov 10, 2013, 4:37:41 PM
    Author     : sliu1_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="manageFrame">
         <font size="3" color="blue"><strong>Delete user</strong></font>
        <form method="Post" action="deleteUser">
         Userid<input type="text" name="userid"><br/>
        <button type="submit" >Delete user</button>
    </form>
        </div>
    </body>
</html>
