<%@page import="bean.UserBean"%>
<%@page import="bean.Customer"%>
<%@page import="db.dbOperation"%>
<%@page import="java.util.Enumeration"%>
<%@page import="bean.Cart"%>
<%@page import="java.util.Hashtable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.MenuBean"%>
<!DOCTYPE html>

    <html>
        <head>
            <title>Order Delivery Information</title>
            <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
            <link href="css/styles.css" rel="stylesheet" type="text/css" />
        </head>
        <body>
            <div id="container">
            <h2>Pizza POS System</h2>  
             <div id="time"><h4 style="text-align:left;" id="time"></h4></div>
            <div align="right">
            <% UserBean user = null;%>
            <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print("Operator: "+user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp");
                    }
            %>
            
            </div>
            
            <table align=center>
             <%
                Customer customer = null;
                String name = "";
                String address = "";
                String telephone = "";
                String postalcode = "";
                if(session.getAttribute("customer")!=null)
                {
                    customer = (Customer) session.getAttribute("customer");
                    name = customer.getCustomerName();
                    address = customer.getAddress();
                    telephone = customer.getPhone();
                    postalcode = customer.getPostalCode();
                 }
              %>

                <tr>
                    <td>
                        <center>
                        <font size="4" color="blue"><strong>Order Delivery Information</strong></font>
                        <br>
                        <form name="order" action="makeOrder" method="post" onSubmit="checkOrder()" >
                            <table>
                                <tr>
                                    <td>
                                        Customer name:&nbsp;
                                        <input type=text name="cname" size=25 value="<%=name %>" />
                                    </td>
                                 </tr>
                                 <tr>
                                     <td>
                                         Phone Number:&nbsp;
                                        <input type=text name="telephone" size=25  value="<%=telephone%>" />
                                      </td>
                                 </tr>
                                 <tr>
                                     <td>
                                        Address:&nbsp;
                                        <input type=text name="address" size=25 value="<%=address%>" />
                                     </td>
                                 </tr>
                                 <tr>
                                     <td>
                                     Post Code:&nbsp;
                                     <input type=text name="postalcode" size=25  value="<%=postalcode%>" />
                                     </td>
                                </tr>
                               
                            </table>
                            <input type="hidden" name="type"  value="delivery"/>
                            <br>
                            <input type="submit" value="Submit" style="background-color:blue" />  
                            <p> &nbsp;
                            <a href=checkCart.jsp>Check Order</a>&nbsp; &nbsp;
                            <a href=mainMenu.jsp>Main Menu</a>&nbsp; &nbsp;
                             <a href=index.jsp>Home</a>  
                            </p>
                        </form>
                       
                          
                            <br>
                            <br>
                    </center>
  
                </td>
            </tr>
            
        </table>
                                     
 
  </div>
                                    
<script type="text/javascript">
    function checkOrder(){
        if(order.username.value==""){				
            alert("Please input customer name");
                        return false;
        }
        else if(order.telephone.value==""){				
            alert("Please input phone number.");
                        return false;
        }
        else if(order.address.value==""){
            alert("Please input the address.");
                        return false;
        }
        else if(order.postalcode.value==""){
                        alert("Please input your post code.");
                        return false;
         }				
  }
			
            </script>
    </body>
</html>
