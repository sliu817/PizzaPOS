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
        <title>Main Menu</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
      <% UserBean user = null;%>
      <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print(user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp");
                    }
                %>
        <%! ArrayList list = null;%>
        <form action="searchMenuDetail" method="post">
            Category:
            <select name="category">
					<option value="pizza"  selected>
						pizza
					</option>
					<option value="salad">
						salad
					</option>
					<option value="drink">
						drink
					</option>
				</select>
            
            
             <input type="submit" value="seach"/>
         </form>
       
       
        <form action="searchOrder" method="post">
            Search order:<input type="text" name="phone">
             <input type="submit" value="seach"/>
             </form>
        <h1>Our ALL menus!</h1>
         <table>
          
          <%
            if (session.getAttribute("menuList") != null) {
                list = (ArrayList) session.getAttribute("menuList");
                Iterator it = list.iterator();
                int i =0;
                while (it.hasNext()) {
                    MenuBean menu;
                            menu = (MenuBean) it.next();
                    i++;
                    
        %>
          <tr align=center bgcolor="green">
                 <td>
                    <form action="checkCart" method="post" onSubmit="checkCart()">
                        <input type="hidden" name="mID" value=<%=menu.getMid()%>  />
                        <input type="hidden" name="menuName"
                               value= <%=menu.getName()%> />
                        <input type="hidden" name="price" value=<%=menu.getPrice()%> />
              
                        <input type="hidden" name="description"
                               value= <%=menu.getDescription()%> />
                        <input type="submit" value="<%=menu.getName()%>"  />
                         
                    </form>
                        
                </td>
         </tr>
         <%
                    session.removeAttribute("menuList");
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
