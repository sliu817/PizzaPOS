<%-- 
    Document   : userManage
    Created on : Nov 10, 2013, 4:26:22 PM
    Author     : sliu1_000
--%>

<%@page import="bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
       
        <div id="container">
          <h2>Pizza POS System</h2>  
  
            <div id="time"><h4 style="text-align:left;" id="time"></h4></div>
            <div id="welcome">
            <% UserBean user = null;%>
            <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print("Operator: " +user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp <a href=index.jsp>Home</a>");
                    }
                %>
                
                 &nbsp&nbsp <a href="searchMenu">Menu</a>
              </div>
        <br/>
        <br/>
        <div id="centerFrame">
        <input type="button" value="User Management" onclick="location.href='userManage.jsp';" style="background-color:#6699FF;width:120px;height:40px">
        <input type="button" value="Menu Management" onclick="location.href='menuManage.jsp';" style="background-color:#FFFF33;width:120px;height:40px">
        <input type="button" value="Order Management" onclick="location.href='orderManage.jsp';" style="background-color:#FF6666;width:120px;height:40px">
      </div>  
            
            
        <div id="manageFrame">
        <h1><font size="4" color="blue"><strong>User Management</strong></font></h1>
         <button onclick="location.href='addUser.jsp';">Add user</button> 

         <form method="Post" action="searchUser">
            <button type="submit" >Update user</button>
         </form>

        <button onclick="location.href='deleteUser.jsp';">Delete user</button>
        </div>
         <div id="mainFrame"></div>
         
   <script>

        function adduser()
        {
            var xmlhttp;
            if (window.XMLHttpRequest)
            {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }
            else
            {// code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("mainFrame").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("GET"," addUser.jsp",true);
            xmlhttp.send();
        }    
        
        
        function deleteuser()
        {
            var xmlhttp;
            if (window.XMLHttpRequest)
            {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }
            else
            {// code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("mainFrame").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("GET"," deleteUser.jsp",true);
            xmlhttp.send();
        }    

        function on_submit(){
            var formid = document.getElementById("form2");

            if(!formid.userid.value){
                alert("Coustomer id cannot be empty.");
                return false;
            }
            else{
                var userid = formid.userid.value;
                if (isNaN(userid)){
                    alert("User id should be a number.");
                    return false;                    
                }                
            }
            
            if(!formid.username.value){
                alert("Coustomer name cannot be empty.");
                return false;
            }

            if(!formid.role.value){
                alert("Role cannot be empty.");
                return false;
            }
            if(!formid.password.value){
                alert("Password cannot be empty.");
                return false;
            }	
        }	
        </script>
        <br />
        
        </div>
    </body>
</html>
