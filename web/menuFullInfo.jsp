<%-- 
    Document   : searchMenuResult
    Created on : Sep 19, 2013, 5:28:39 PM
    Author     : sliu1_000
--%>
<%@page import="bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.MenuBean"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
        <%! ArrayList list = null;%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% UserBean user = null;%>
        <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print(user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp");
                    }
                %>
       <h2> <%=session.getAttribute("category")%></h2>
       <table border="1" bgColor=#d7ebff>
        <tr>
            <td>Item</td>
            <td>Price</td>
            <td>Description</td>
        </tr>
          <%
            if (session.getAttribute("menuinfo") != null) {
                list = (ArrayList) session.getAttribute("menuinfo");
                Iterator it = list.iterator();
                int i =0;
                while (it.hasNext()) {
                    MenuBean menu;
                            menu = (MenuBean) it.next();
                    i++;
        %>
        
            
        <tr>
            <td>
                    <form action="checkCart" method="post" >
                        <input type="hidden" name="mID" value=<%=menu.getMid()%>  />
                        <input type="hidden" name="menuName" value= <%=menu.getName()%> />
                        <input type="hidden" name="price" value=<%=menu.getPrice()%> />
                        <input type="hidden" name="description" value= <%=menu.getDescription()%> />
                        <%
                        String type = menu.getCategory();
                        
                        String hue="";
                        if(type.equals("pizza"))
                            hue = "#6699FF";
                        else if(type.equals("salad"))
                             hue = "#FFFF33";
                        else if(type.equals("drink"))
                             hue = "#FF6666";
                        else if(type.equals("sides"))
                             hue = "#66FF66";
                        else 
                             hue = "#F8F8F8";
     
                        %>
                     
                        <input type="submit" value="<%=menu.getName()%>" style="background-color:<%=hue%>;width:150px;height:60px" />
                         
                    </form>        
            </td>
            <td>
                    <%=menu.getPrice()%>
            </td>
                
            <td>
                    <%=menu.getDescription()%>
            </td>
        </tr>
         <%
                    session.removeAttribute("menuinfo");
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
