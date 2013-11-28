<%@page import="bean.UserBean"%>
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
        <title>Cart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
          <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
			function showHint(str){
			var xmlhttp;
			var txt,x,i;
			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp=new XMLHttpRequest();
			}
			else{// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function(){
				if (xmlhttp.readyState==4 && xmlhttp.status==200){
					xmlDoc=xmlhttp.responseXML;
					txt=new Array();
					x=xmlDoc.getElementsByTagName("customer");
					for (i=0;i<x.length;i++){
						var att = x[i].getAttribute("phone");
						if (att.search(str) == 0)
							txt.push(att);
					}
                                        var menuDOM = document.getElementById("txtHint");
                                        menuDOM.length = 0;
                                        /*for (i=menuDOM.length-1; i>=0; i--){
                                            menuDOM.remove(i);
                                        }*/
                                        var nextPhone;
                                        for (i = 0; i < txt.length; i++){
                                            nextPhone = txt[i];
                                            var opt = document.createElement('option');
                                            opt.value = nextPhone;
                                            opt.innerHTML = nextPhone;
                                            menuDOM.appendChild(opt);
                                        }
				}
			}
			xmlhttp.open("GET","xml/customer.xml",true);
			xmlhttp.send();
		}
        
                function time(){
                    var d = new Date();
                    var h = d.getHours();
                    var m = d.getMonth() + 1;
                    if (h >= 0 && h < 12){
                        document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "AM " + m + "/" + d.getDate() + "/" + d.getFullYear();
                    }
                    else{
                        h -= 12;
                        document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "PM " + m + "/" + d.getDate() + "/" + d.getFullYear();
                    }
                    setTimeout("time()", 500);
               }
               
               function formSubmit(){
                   var s = document.getElementById("txtHint");
                   var c = s.selectedIndex;
                   document.getElementById("phone").value= s.options[c].value;
                   document.getElementById("form1").submit();
               }
	</script>
    </head>

    <body onload="time()">  
        <div id="container">
        <h2>Pizza POS System</h2>  
  
            <div><h4 style="text-align:left;" id="time"></h4></div>
            <div id="welcome">
            <% ArrayList list = dbOperation.getMenus();%>
            <% UserBean user = null;%>
            <% user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print("Operator: " +user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp <a href=index.jsp>Home</a>");
                    }
                %>
                
                 &nbsp&nbsp <a href="searchMenu">Menu</a>
              </div>
    
        <div class="menu">
        <form action="searchMenuDetail" method="post">
            <input type="hidden" name="category" value="pizza" />
            <input type="submit" value="Pizza" style="background-color:#6699FF" class="btn_1" />    
         </form>
         <form action="searchMenuDetail" method="post">
             <input type="hidden" name="category" value="salad" />
            <input type="submit" value="Salad" style="background-color:#FFFF33" class="btn_2" />    
         </form>
         <form action="searchMenuDetail" method="post">
             <input type="hidden" name="category" value="sides" />
            <input type="submit" value="Sides" style="background-color:#66FF66"  class="btn_3" />  
         </form>
         <form action="searchMenuDetail" method="post">
             <input type="hidden" name="category" value="drink" />
            <input type="submit" value="Drinks" style="background-color:#FF6666"  class="btn_4" />  
         </form>
        </div>
              
        <div id="searchOrder">
         <form id="form1" action="searchOrder" method="post">
            Search order:<input type="text" id="phone" name="phone" onkeyup="showHint(this.value)">
            Hints: <select id="txtHint" onclick="formSubmit()"></select>
            <input type="submit" value="seach"/>
        </form>
        </div>
        
        <div id="order">
        <form action="searchAllOrder" method="post" >
            <input type="submit" value="Orders"  style="background-color:pink;width:50px;height:30px" />      
        </form>
        </div>
        
        <div id="mainMenu">
        <%-- <h1><font size="6" color="blue"><strong>Our All Menus!</strong></font></h1>--%>
        <table align="left">  
        <%
            if(list!=null){      
                int i =0;
                int row=0;
                int col=4;
                for(row=0; row<=(list.size()/col);row++)
                {
           %>
            <tr align=center>
           <%   
                    for(i=col*row; i<row*col+col&&i<list.size(); i++)
                    {
                        MenuBean menu = (MenuBean)list.get(i); 
                        
           %>
         
                 <td>
                    <form action="checkCart" method="post" >
                        <input type="hidden" name="mID" value=<%=menu.getMid()%>  />
                        <input type="hidden" name="menuName" value= <%=menu.getName()%> />
                        <input type="hidden" name="price" value=<%=menu.getPrice()%> />
                        <input type="hidden" name="description"  value= <%=menu.getDescription()%> />
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
                <%
                    }
                 %>
           </tr>
                 <%
                }
                    session.removeAttribute("menuList");
               
             %>
            </table>
            </div>
            <%
               }
                 else {
                    out.println("<center><font color=brown>Sorry, No results found!</font></center>");
                }
            
        %>   
       <div  id="checkFrame">
        <font size="4" color="blue"><strong>Checkout</strong></font>&nbsp;&nbsp;&nbsp;&nbsp;

        <br>
        <%
            Hashtable buyedItems = null;
            double totalPrice = 0;//Total original price
            int count = 0;
            try {
        %>
        <p>
            <table  cellSpacing=0 cellPadding=1 width="30%" border=1 >
                <tr bgColor=#d7ebff>
                    <td colSpan=2 width=80>
                        <strong>Items:</strong>
                    </td>
                    <td width=20>
                        <strong>Price</strong>
                    </td>
                  
                    <td width=40>
                         <strong>Qty</strong>
                    </td>
                </tr>
                <%
                    Cart myCart = (Cart) session.getAttribute("myCart");
                    if (myCart == null) {
                        myCart = new Cart();
                    }
                    buyedItems = myCart.listMyItems();
                    Enumeration enume = buyedItems.keys();
                    while (enume.hasMoreElements()) {
                        String itemKey = (String) enume.nextElement();
                        //byte[] b = itemKey.getBytes("ISO-8859-1");
                        //itemKey = new String(b);
                        MenuBean item = dbOperation.getItemInfo(itemKey);
                        String itemName = item.getName();
                        double price = item.getPrice();
                        count = ((Integer) buyedItems.get(itemKey)).intValue();
                        totalPrice += price * count;
                       
                %>
                <tr>
                    <td colSpan=2>
                         <%=itemName%>  &nbsp;
                        <form id="listItem" action="removeItem" method="post">
                           <input type="hidden" name="mID" value="<%=itemKey%>"/>
                           <input type="submit" value="delete" style="background-color:#d7ebff"/> 
                        </form>
                    </td>
                    <td align=center>
                        $<%=price%> 
                    </td>
               
                    <td align=center>
                        <form id="updateItemCount" action="addItem" method="post">
                            <input type="text" size=2  maxlength=3
                                   name="count" value="<%=count%>">
                            <input type="hidden" name="mID" value="<%=itemKey%>" >
                            <input type="submit" value="update" style="background-color:#d7ebff"/>
                        </form>
                    </td>
                </tr>

                <%
                    }
                %>
                
                  <tr>
                    <td colSpan=2 width=50>
                        <div align=right>
                            <strong>Total:</strong></div> 
                    </td>
                    <td width=30>
                        <div align=center>
                            <font color=black>$<%=totalPrice %></font>
                             <input type="hidden" id="totalPrice" value="<%=totalPrice %>" >
                        </div>    
                    </td>
                    <td width=30>
                        <div align=center>
                            <strong></strong></div>
                    </td>
                </tr>
                 
                 <tr>
                    <td colSpan=2 width=50>
                       <strong>Amount</strong>
                    </td>
                    <td>
                        <input type="text" name="pay" id="payAmount" onkeyup="checkOut()" /><br/>
                    </td>
                
                    <td width=30>
                        <div align=center>                    
                            <form action="makeDineinOrder" method="post" >
                             <select name="type">
					<option value="dinein"  selected>
						Dine in
					</option>
					<option value="takeout">
						Take out
					</option>
				</select>
                            <strong><input type="submit"  value="check out" style="background-color:#d7ebff" /></strong>
                            </form>
                        </div>
                    </td>
                 </tr>
                 <tr>
                     <td colSpan=2 width=50>
                         <strong>Change</strong>
                      </td>
                     <td>
                        <div id="change"></div>
                    </td> 
                     <td width=30>
                        <div align=center>
                            <strong></strong></div>
                    </td>
                  </tr>
                                   
            </table>
 <%
                } catch (Exception e) {
                    out.print(e);
                }
            %>
         
           
            <br>
            <br>
             <form action="orderForm" method="post">
                 <font size="4" color="blue">Look up Phone<input type="text" name="phone" /></font>
                 <input type="submit" value="Delivery" style="background-color:pink" />
                  <%--Hints: <select id="txtHint" onclick="formSubmit()"></select>  --%>
              </form>       
       </div>
       
      
         
<script>
   
    function checkOut()
    {
        var totalPay = document.getElementById("payAmount").value;
        var totalPrice = document.getElementById("totalPrice").value;
        var change = totalPay - totalPrice;
        document.getElementById("change").innerHTML="$"+change;
    }
            
            
    function time(){
	var d = new Date();
	var h = d.getHours();
	var m = d.getMonth() + 1;
	if (h >= 0 && h < 12){
		document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "AM " + m + "/" + d.getDate() + "/" + d.getFullYear();
	}
	else{
		h -= 12;
		document.getElementById("time").innerHTML = h + ":" + d.getMinutes() + "PM " + m + "/" + d.getDate() + "/" + d.getFullYear();
	}
	setTimeout("time()", 500);
    }

</script>
</div>
</body>
</html>
