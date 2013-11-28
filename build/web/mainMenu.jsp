<%-- 
    Document   : mainMenu
    Created on : Oct 19, 2013, 5:28:39 PM
    Author     : MadPotatoes
--%>
<%@page import="db.dbOperation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator"%>
<%@ page import="bean.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Main Menu</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <table>
          
          <%
            if (list != null) {
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
                        <input type="hidden" name="description" value= <%=menu.getDescription()%> />
                        <%//String hue = "rgb(" + ((int)Math.floor(Math.random() * 256)) + "," + ((int)Math.floor(Math.random() * 256)) + "," + ((int)Math.floor(Math.random() * 256)) + ")";
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
     
          
          </div>
</body>
</html>
