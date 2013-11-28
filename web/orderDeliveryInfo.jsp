<%-- 
    Document   : searchMenuResult
    Created on : Sep 19, 2013, 5:28:39 PM
    Author     : sliu1_000
--%>
<%@page import="db.dbOperation"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.*"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
      
        <%! ArrayList delivery_list = dbOperation.getOrders("Delivery");%>
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
          <h2>Delivery Orders!</h2>
            
             
            <%
            if (delivery_list != null) {
              
                Iterator it =delivery_list.iterator();
                int i =0;
                while (it.hasNext()) {
                    OrderBean order;
                            order = (OrderBean) it.next();
                    i++;
        %>
        <table border="1">
            <tr align=center>
                <td>
                    <input type="button" name ="ono" value= <%=order.getOrderNo()%> /> 
                </td>
                <td>
                    <%=order.getCustomerID()%>
                </td>
                <td>
                    <%=order.getAddTime()%>
                </td>
                <td>
                   <form action="updateStatus" method="post">
                        <input type="hidden" name ="ono" value= <%=order.getOrderNo()%> />   
                        <select name="status">
					<option value ="<%=order.getStatus()%>"  selected> <%=order.getStatus()%> </option>
					<option value="accepted"> accepted </option>
					<option value="making"> making </option>
                                        <option value="finished"> finished </option>
                                        <option value="on the road"> on the road </option>
                                        <option value="delivered"> delivered </option>
                        </select>
                        <input type="submit" value="update status" /> 
                   </form>
                </td>
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
                <td> <%=quantity%>  </td>
            </tr>
        
         <%
                 }
                    session.removeAttribute("delivery_list");
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
