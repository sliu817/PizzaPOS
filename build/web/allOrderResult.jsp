<%-- 
    Document   : allOrderResult
    Created on : Oct 19, 2013, 5:28:39 PM
    Author     : 
--%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.*"%>


<!DOCTYPE html>
<html>
    <head>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <title>Search Results</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="container">
        <h2>Pizza POS System</h2>  
        <% UserBean user = null;%>
        <% user = (UserBean) session.getAttribute("user");
            if (user != null) {
                out.print(user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp;&nbsp;");
            }
        %>
        <% ArrayList list = null;%>&nbsp;&nbsp;
        <a href=checkCart.jsp>Main Menu</a>
        <a href=index.jsp>Home</a>
        <h1> <font size="6" color="blue"><strong>ALL Orders!</strong></font></h1>
          <form action="searchOrderStatus" method="post" onSubmit="checkStatus()">
            View order by status:
            <select name="status">
                <option value="accepted"  selected> accepted </option>
                <option value="making"> making </option>
                <option value="finished"> finished </option>
                <option value="on_the_road"> on the road </option>
                <option value="delivered"> delivered </option>
            </select>
            <input type="submit" value="seach"/>
         </form>
        <div id="mainFrame">
        
          <%
            if (session.getAttribute("orderList") != null) {
                list = (ArrayList) session.getAttribute("orderList");
                Iterator it = list.iterator();
                int i =0;
                while (it.hasNext()) {
                    OrderBean order;
                            order = (OrderBean) it.next();
                    i++;
        %>
        
        <table cellSpacing=1 cellPadding=2 width="50%" border=1 >
            <tr align=center bgColor=#d7ebff >
            <td>
                    <%=order.getOrderNo()%>
            </td>
            <td>
                    <%=order.getCustomerID()%>
            </td>
            <td>
                    <%=order.getAddTime()%>
            </td>    
             <td>
                    <%=order.getStatus()%>
             </td>      
        </tr>
         </table>
        
        <table>    
         <tr>
              <td>item</td>
              <td>price</td>
              <td>quantity</td>        
          </tr>
        <% Hashtable items = order.getItemsTable().getTable();
            Enumeration e = items.keys();
            while(e.hasMoreElements())
            {
                MenuBean menu =(MenuBean) e.nextElement();
                String quantity = (String)items.get(menu);
                 
        %>
       
        <tr>
            <td>
                    <%=menu.getName()%>
            </td>
            <td>
                    <%=menu.getPrice()%>
            </td>
            <td>
                    <%=quantity%>
            </td>
               
        </tr>
        
         <%
                 }
                    session.removeAttribute("orderList");
                  
              }
                
            %>
            </table>
            <hr/>
            <%
               }
                // else {
                  //  out.println("<center><font color=brown>Sorry, No results found!</font></center>");
             // }
            
        %>
      </div>
      <script>
           function checkStatus()
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
            xmlhttp.open("GET","orderStatusResult.jsp",true);
            xmlhttp.send();
        }    
        
       </script>
       </div>
    </body>
</html>
