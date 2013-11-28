<%-- 
    Document   : administration
    Created on : Nov 10, 2013, 4:17:53 PM
    Author     : sliu1_000
--%>

<%@page import="bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <title>administration</title>
    </head>
    <body onload="time()">
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

        </div>
                
                <script>
                     function time(){
	var d = new Date();
	var h = d.getHours();
	var m = d.getMonth() + 1;
	if (h >= 0 && h < 12){
		document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "AM " + m + "/" + d.getDate() + "/" + d.getFullYear();
	}
	else{
		h -= 12;
		document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "PM " + m + "/" + d.getDate() + "/" + d.getFullYear();
	}
	setTimeout("time()", 500);
    }

                 </script>
    </body>
</html>
