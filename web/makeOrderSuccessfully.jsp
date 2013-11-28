<%-- 
    Document   : makeOrderSuccessfully
    Created on : Nov 14, 2013, 7:40:34 PM
    Author     : sliu1_000
--%>

<%@page import="bean.UserBean"%>
<%@page import="bean.MenuBean"%>
<%@page import="db.dbOperation"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="bean.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <title>Receipt</title>
    </head>
    <body>
        <div id="container">
        <h2>Pizza POS System</h2> 
        <div id="centerFrame">
        <h1>Receipt</h1>
        
        <% UserBean user = null;%>
        <% user = (UserBean) session.getAttribute("user");
                if (user != null) {
                        out.print("Operator: " +user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp");
                }
        %> <br/>
        <% Cart myCart = (Cart) session.getAttribute("myCart");%>
        <%  if(session.getAttribute("order_no")!=null)
            {
                String ono = session.getAttribute("order_no").toString();
         %>
                <%=session.getAttribute("type")%>
                <font size="4" color="blue"><strong>Your order number is: <%=ono %></strong></font> <br/>
                <%=session.getAttribute("time")%>
                <%
            }else
            {
            %>
                <font size="4" color="blue"><strong>Order number is not generated!</strong></font>
            <%
            }
            %>
            
        <table border="1">
        <% int count = 0; double totalPrice = 0;
            Hashtable buyedItems = myCart.listMyItems();
            Enumeration enume = buyedItems.keys();
            while (enume.hasMoreElements()) {
                String itemKey = (String) enume.nextElement();
                MenuBean item = dbOperation.getItemInfo(itemKey);
                String itemName = item.getName();
                double price = item.getPrice();
                count = ((Integer) buyedItems.get(itemKey)).intValue();
                totalPrice += price * count;

        %>
       
        <tr>
            <td colSpan=2> <%=itemName%> </td>
            <td> <%=price%> </td>
            <td> <%=count%> </td>
        </tr>
            <%
            }
            %>
                
        <tr>
            <td colSpan=2 width=50>
                <div align=right> <strong>Total:</strong></div>     
            </td>
            <td width=30>
                <div align=center>
                    <font color=black><%=totalPrice %></font>
                    <input type="hidden" id="totalPrice" value="<%=totalPrice %>" >
                </div>    
            </td>
            <td width=30>
                <div align=center><strong></strong></div>
            </td>
        </tr>
        <%       myCart.clear();
                session.removeAttribute("myCart");
        %>
    </table>

          <br>
        <a href=checkCart.jsp>Main Menu</a>
        <a href=index.jsp>Home</a>
        </div>
    </div>
    </body>
</html>
