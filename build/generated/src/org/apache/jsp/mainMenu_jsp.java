package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import db.dbOperation;
import java.util.ArrayList;
import java.util.Iterator;
import bean.*;

public final class mainMenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Main Menu</title> \n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\t\tfunction showHint(str){\n");
      out.write("\t\t\tvar xmlhttp;\n");
      out.write("\t\t\tvar txt,x,i;\n");
      out.write("\t\t\tif (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("\t\t\t\txmlhttp=new XMLHttpRequest();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\telse{// code for IE6, IE5\n");
      out.write("\t\t\t\txmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\txmlhttp.onreadystatechange=function(){\n");
      out.write("\t\t\t\tif (xmlhttp.readyState==4 && xmlhttp.status==200){\n");
      out.write("\t\t\t\t\txmlDoc=xmlhttp.responseXML;\n");
      out.write("\t\t\t\t\ttxt=new Array();\n");
      out.write("\t\t\t\t\tx=xmlDoc.getElementsByTagName(\"customer\");\n");
      out.write("\t\t\t\t\tfor (i=0;i<x.length;i++){\n");
      out.write("\t\t\t\t\t\tvar att = x[i].getAttribute(\"phone\");\n");
      out.write("\t\t\t\t\t\tif (att.search(str) == 0)\n");
      out.write("\t\t\t\t\t\t\ttxt.push(att);\n");
      out.write("\t\t\t\t\t}\n");
      out.write("                                        var menuDOM = document.getElementById(\"txtHint\");\n");
      out.write("                                        menuDOM.length = 0;\n");
      out.write("                                        /*for (i=menuDOM.length-1; i>=0; i--){\n");
      out.write("                                            menuDOM.remove(i);\n");
      out.write("                                        }*/\n");
      out.write("                                        var nextPhone;\n");
      out.write("                                        for (i = 0; i < txt.length; i++){\n");
      out.write("                                            nextPhone = txt[i];\n");
      out.write("                                            var opt = document.createElement('option');\n");
      out.write("                                            opt.value = nextPhone;\n");
      out.write("                                            opt.innerHTML = nextPhone;\n");
      out.write("                                            menuDOM.appendChild(opt);\n");
      out.write("                                        }\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\txmlhttp.open(\"GET\",\"xml/customer.xml\",true);\n");
      out.write("\t\t\txmlhttp.send();\n");
      out.write("\t\t}\n");
      out.write("        \n");
      out.write("                function time(){\n");
      out.write("                    var d = new Date();\n");
      out.write("                    var h = d.getHours();\n");
      out.write("                    var m = d.getMonth() + 1;\n");
      out.write("                    if (h >= 0 && h < 12){\n");
      out.write("                        document.getElementById(\"time\").innerHTML = h + \":\" + d.getMinutes() + \"AM \" + m + \"/\" + d.getDate() + \"/\" + d.getFullYear();\n");
      out.write("                    }\n");
      out.write("                    else{\n");
      out.write("                        h -= 12;\n");
      out.write("                        document.getElementById(\"time\").innerHTML = h + \":\" + d.getMinutes() + \"PM \" + m + \"/\" + d.getDate() + \"/\" + d.getFullYear();\n");
      out.write("                    }\n");
      out.write("                    setTimeout(\"time()\", 500);\n");
      out.write("               }\n");
      out.write("\t</script>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body onload=\"time()\">\n");
      out.write("        <div id=\"container\">\n");
      out.write("        <h2>Pizza POS System</h2>  \n");
      out.write("  \n");
      out.write("            <div id=\"time\"><h4 style=\"text-align:left;\" id=\"time\"></h4></div>\n");
      out.write("            <div id=\"welcome\">\n");
      out.write("            ");
 ArrayList list = dbOperation.getMenus();
      out.write("\n");
      out.write("            ");
 UserBean user = null;
      out.write("\n");
      out.write("            ");
 user = (UserBean) session.getAttribute("user");
                    if (user != null) {
                        out.print("Operator: " +user.getUserName() + "!&nbsp&nbsp<a href=Logout>Logout</a>&nbsp&nbsp <a href=index.jsp>Home</a>");
                    }
                
      out.write("\n");
      out.write("              </div>\n");
      out.write("    \n");
      out.write("        <div class=\"menu\">\n");
      out.write("        <form action=\"searchMenuDetail\" method=\"post\">\n");
      out.write("            <input type=\"hidden\" name=\"category\" value=\"pizza\" />\n");
      out.write("            <input type=\"submit\" value=\"Pizza\" style=\"background-color:#6699FF\" class=\"btn_1\" />    \n");
      out.write("         </form>\n");
      out.write("         <form action=\"searchMenuDetail\" method=\"post\">\n");
      out.write("             <input type=\"hidden\" name=\"category\" value=\"salad\" />\n");
      out.write("            <input type=\"submit\" value=\"Salad\" style=\"background-color:#FFFF33\" class=\"btn_2\" />    \n");
      out.write("         </form>\n");
      out.write("         <form action=\"searchMenuDetail\" method=\"post\">\n");
      out.write("             <input type=\"hidden\" name=\"category\" value=\"sides\" />\n");
      out.write("            <input type=\"submit\" value=\"Sides\" style=\"background-color:#66FF66\"  class=\"btn_3\" />  \n");
      out.write("         </form>\n");
      out.write("         <form action=\"searchMenuDetail\" method=\"post\">\n");
      out.write("             <input type=\"hidden\" name=\"category\" value=\"drink\" />\n");
      out.write("            <input type=\"submit\" value=\"Drinks\" style=\"background-color:#FF6666\"  class=\"btn_4\" />  \n");
      out.write("         </form>\n");
      out.write("        </div>\n");
      out.write("              \n");
      out.write("        <div id=\"searchOrder\">\n");
      out.write("        <form action=\"searchOrder\" method=\"post\" id=\"form1\" >\n");
      out.write("            Search order:<input type=\"text\" id=\"phone\" name=\"phone\"  onkeyup=\"showHint(this.value)\" >\n");
      out.write("            Hints: <select id=\"txtHint\" onclick=\"formSubmit()\"></select>\n");
      out.write("            <input type=\"submit\" value=\"seach\"/>\n");
      out.write("        </form>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div id=\"order\">\n");
      out.write("        <form action=\"searchAllOrder\" method=\"post\" >\n");
      out.write("            <input type=\"submit\" value=\"Orders\"  style=\"background-color:pink;width:50px;height:30px\" />      \n");
      out.write("        </form>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div id=\"mainMenu\">\n");
      out.write("        ");
      out.write("\n");
      out.write("        <table>\n");
      out.write("          \n");
      out.write("          ");

            if (list != null) {
                int i =0;
                int row=0;
                int col=4;
                for(row=0; row<=(list.size()/col);row++)
                {
           
      out.write("\n");
      out.write("            <tr align=center>\n");
      out.write("           ");

                    
                    for(i=col*row; i<row*col+col&&i<list.size(); i++)
                    {
                        MenuBean menu = (MenuBean)list.get(i);                
           
      out.write("\n");
      out.write("         \n");
      out.write("                 <td>\n");
      out.write("                    <form action=\"checkCart\" method=\"post\" >\n");
      out.write("                        <input type=\"hidden\" name=\"mID\" value=");
      out.print(menu.getMid());
      out.write("  />\n");
      out.write("                        <input type=\"hidden\" name=\"menuName\" value= ");
      out.print(menu.getName());
      out.write(" />\n");
      out.write("                        <input type=\"hidden\" name=\"price\" value=");
      out.print(menu.getPrice());
      out.write(" />\n");
      out.write("                        <input type=\"hidden\" name=\"description\" value= ");
      out.print(menu.getDescription());
      out.write(" />\n");
      out.write("                        ");
//String hue = "rgb(" + ((int)Math.floor(Math.random() * 256)) + "," + ((int)Math.floor(Math.random() * 256)) + "," + ((int)Math.floor(Math.random() * 256)) + ")";
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
                        
      out.write("\n");
      out.write("                     \n");
      out.write("                        <input type=\"submit\" value=\"");
      out.print(menu.getName());
      out.write("\" style=\"background-color:");
      out.print(hue);
      out.write(";width:150px;height:60px\" />    \n");
      out.write("                    </form>        \n");
      out.write("                </td>   \n");
      out.write("         ");

                    }
         
      out.write("\n");
      out.write("           </tr>\n");
      out.write("          ");

                }
                    session.removeAttribute("menuList");
               
          
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("            \n");
      out.write("            ");

               }
                 else {
                    out.println("<center><font color=brown>Sorry, No results found!</font></center>");
                }  
            
      out.write("\n");
      out.write("     \n");
      out.write("          \n");
      out.write("          </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
