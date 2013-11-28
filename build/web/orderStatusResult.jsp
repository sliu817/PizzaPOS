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
        <title>Search Results</title>
        <% ArrayList list = null;%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
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
          <%
            if (session.getAttribute("orderStatusList") != null) {
                list = (ArrayList) session.getAttribute("orderStatusList");
                Iterator it = list.iterator();
                int i =0;
                while (it.hasNext()) {
                    OrderBean order;
                            order = (OrderBean) it.next();
                    i++;
        %>
        
        <table cellSpacing=1 cellPadding=2 width="50%" border=1>
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
                    session.removeAttribute("orderStatusList");
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
    </body>
</html>
