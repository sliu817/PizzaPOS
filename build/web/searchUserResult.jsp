<%-- 
    Document   : searchUserResult
    Created on : Nov 17, 2013, 12:37:18 PM
    Author     : zhenhua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript">
            function on_submit(str){
                var formid = document.getElementById(str);
 
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
    </head>
    <body>
        <h1></h1>
        <%! ArrayList list = null;%>
         <table>
                <tr align=center>                
                    <th>Userid</th><th>&nbsp;</th><th>Username</th><th>Role</th><th>Password</th><th>&nbsp;</th>
                </tr>
          <%
            if (session.getAttribute("userList") != null) {
                list = (ArrayList) session.getAttribute("userList");
                Iterator it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    UserBean user;
                            user = (UserBean) it.next();
                    i++;
                    
        %>
          <tr align=center >
              <form id="<%=user.getUserID()%>" name="<%=user.getUserID()%>" action="updateUser" method="post" onsubmit="return on_submit(this.getAttribute('id'))">
                               
                  <td>                    
                        <%=user.getUserID()%>  
                 </td>
                 <td>
                        <input type="hidden" name="userid"
                               value= <%=user.getUserID()%> />
                 </td>
                 <td>
                        <input type="text" name="username"
                               value= <%=user.getUserName()%> />
                 </td>                 
                 <td>
                        <input type="text" name="role" value=<%=user.getUserRole()%> />
                 </td>
                 <td>
                        <input type="password" name="password"
                               value= <%=user.getPassword()%> />
                  </td>
                  <td>
                        <input type="submit" value="Update"  />                                   
                  </td>
            </form>
         </tr>
         <%
                    session.removeAttribute("userList");
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
        <a href="userManage.jsp">Back to User Management</a>
    </body>
</html>
