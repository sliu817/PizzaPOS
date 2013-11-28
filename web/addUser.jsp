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
        <title>Add user</title>
    </head>
    <body>
         <div id="manageFrame">
        <font size="3" color="blue"><strong>Add user</strong></font>
        <form method="Post" id="form2" action="addUser" onsubmit="return on_submit()">
            <table>
                <tr><td>Userid</td><td><input type="text" name="userid"></td></tr>
                <tr><td>Username</td><td><input type="text" name="username"></td></tr>
                <tr><td>Role</td><td><input type="text" name="role"></td></tr>
                <tr><td>Password</td><td><input type="password" name="password"></td></tr>
               <tr><td colspan="2"><button type="submit" >Add user</button>
            </table>
    </form>	
        </div>
    </body>
</html>