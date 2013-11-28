<%-- 
    Document   : searchUserResult
    Created on : Nov 17, 2013, 12:37:18 PM
    Author     : zhenhua
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.MenuBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript">
            function on_submit(str){
                var formid = document.getElementById(str);
 
                if(!formid.category.value){
                    alert("Category cannot be empty.");
                   
                    return false;
                }
                
                if(!formid.name.value){
                    alert("Name cannot be empty.");
                    return false;
                }
                if(!formid.price.value){
                    alert("Price cannot be empty.");
                    return false;
                }
                else{
                    var price = formid.price.value;                  
                    if (isNaN(price)){
                        alert("Price should be a number.");
                        return false;                    
                    }
                }
                
                if(!formid.description.value){
                    alert("Description cannot be empty.");
                    return false;
                }                
            }	
        </script>      
    </head>
    <body>
        <h1></h1>
        <%! ArrayList list = null;      
        %>
         <table>
                <tr align=center>                
                    <th>MenuID</th><th>&nbsp;</th><th>Category</th><th>Name</th><th>Price</th><th>Description</th><th>&nbsp;</th>
                </tr>
          <%
            if (session.getAttribute("menuList") != null) {
                list = (ArrayList) session.getAttribute("menuList");
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    MenuBean menu;
                            menu = (MenuBean) it.next();
                    i++;
                    
        %>
          <tr align=center >
              <form id="<%=menu.getMid()%>" name="<%=menu.getMid()%>" 
                    action="updateMenu?param=<%=session.getAttribute("param")%>" 
                    method="post" onsubmit="return on_submit(this.getAttribute('id'))">
                               
                  <td>                    
                        <%=menu.getMid()%>
                 </td>
                 <td>
                        <input type="hidden" name="menuid"
                               value= "<%=menu.getMid()%>" />
                 </td>
                 <td>
                        <input type="text" name="category" 
                               value= "<%=menu.getCategory()%>" />
                 </td>                 
                 <td>
                        <input type="text" name="name" size=30 value="<%=menu.getName()%>" />
                 </td>
                 <td>
                        <input type="text" name="price"
                               value= "<%=menu.getPrice()%>" />
                  </td>
                 <td>
                        <input type="text" name="description" size=95
                               value= "<%=menu.getDescription()%>" />
                  </td>
                  <%
                    if (session.getAttribute("param").equals("update")) {
                  %>
                  <td>
                        <input type="submit" value="Update"  />  
                  </td>
                  <%} 
                    else{%>
                    <td>
                        <input type="submit" value="Delete"  />  
                    </td>
                    <%}%>
            </form>
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
        <br />
        <a href="menuManage.jsp">Back to Menu Management</a>
    </body>
</html>
