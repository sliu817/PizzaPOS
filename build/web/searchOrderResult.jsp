<%-- 
    Document   : searchMenuResult
    Created on : Sep 19, 2013, 5:28:39 PM
    Author     : sliu1_000
--%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.*"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
        <%! ArrayList list = null;%>
        <%! MenuBean menu = null;%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% UserBean user = null;%>
        <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print(user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp");
                    }
        %>
        <h1>Your ALL Orders!</h1>
        
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
        <table>
            <tr align=center>
                <td> <%=order.getOrderNo()%> </td>
                <td> <%=order.getCustomerID()%> </td>
                <td> <%=order.getAddTime()%> </td>
                <td> <%=order.getStatus()%>  </td>
          
            </tr>
         </table>
        
        <table>
            <tr>
                <td>name</td>
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
       
        <tr align=center>
            <td> <%=menu.getName()%> </td>
            <td> <%=menu.getPrice()%> </td>
            <td> <%=quantity%> </td>
        </tr>
        
         <%
                 }
                    session.removeAttribute("orderList");
                }
                
                
            %>
     </table>
         
            <%
               }
                 else {
                    out.println("<center><font color=brown>Sorry, No results found!</font></center>");
                }
            
        %>
    <a href=checkCart.jsp>Main Menu</a>
    <a href=index.jsp>Home</a>
    </body>
</html>
