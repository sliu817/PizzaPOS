<%-- 
    Document   : addCustomer
    Created on : Nov 17, 2013, 2:58:41 PM
    Author     : zhenhua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Menu</title>
        <script language="javascript">
            function on_submit(){
                var formid = document.getElementById("form1");             
 alert("Hello");
                if(!formid.category.value){
                    alert("Category cannot be empty.");
                   
                    return false;
                }
                
                if(!formid.name.value){
                    alert("Menu name cannot be empty.");
                    return false;
                }
                
                if(!formid.price.value){
                    
                    alert("Price cannot be empty.");
                    return false;
                }
                else{
                    alert(formid.price.value);
                }
                
                // regular expression
                var rgexp = new RegExp("^\d*([.]\d{2})?$");
                if (!price.match(rgexp)){
                    alert("Price should be decimal number.");
                    return false;                    
                }
                                                   
                if(!formid.description.value){
                    alert("Description cannot be empty.");
                    return false;
                }	               
            }	
        </script>              
    </head>
       <font size="3" color="blue"><strong>Add menu</strong></font>

        <form id="form1" name="form1" method="Post" action="addMenu" onsubmit="return on_submit()">
            <table>
                <tr><td>Category</td><td><input type="text" name="category"></td></tr>
                <tr><td>Name</td><td><input type="text" name="name"></td></tr>
                <tr><td>Price</td><td><input type="text" name="price"></td></tr>
                <tr><td>Description</td><td><input type="text" name="description"></td></tr>        
                <tr><td colspan="2"><button type="submit" >Add Menu</button></td></tr>
            </table>
        </form>
        
</html>
