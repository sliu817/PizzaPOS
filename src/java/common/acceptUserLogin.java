/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import bean.UserBean;
import db.dbOperation;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class acceptUserLogin extends HttpServlet {
    ArrayList list=null;
   
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession session = request.getSession(true);
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		
		if(userid==null) {
                                userid="";
                            }
		if(password==null) {
                                password="";
                            }
		
		if(userid!=null && password!=null){
                    if(dbOperation.isUserValidate(userid, password).equals("FOH")){
                                String username = dbOperation.getUserNameByID(userid);
				UserBean user=new UserBean(userid,username,password);
				session.setAttribute("user", user);
                                list=dbOperation.getMenus();    
                                session.setAttribute("menuList", list);
                                response.setContentType("text/html;charset=GB2312");
				response.sendRedirect("mainMenu.jsp");
			}
                    else if(dbOperation.isUserValidate(userid, password).equals("Manager"))
                    {
                                String username = dbOperation.getUserNameByID(userid);
                                UserBean user=new UserBean(userid,username,password);
				session.setAttribute("user", user);
				response.sendRedirect("administration.jsp");
                    }
                    else if(dbOperation.isUserValidate(userid, password).equals("Kitchen"))
                    {
                                String username = dbOperation.getUserNameByID(userid);
                                UserBean user=new UserBean(userid,username,password);
				session.setAttribute("user", user);     
				response.sendRedirect("kitchen.jsp");
                    }
                       else if(dbOperation.isUserValidate(userid, password).equals("Driver"))
                    {
                                String username = dbOperation.getUserNameByID(userid);
                                UserBean user=new UserBean(userid,username,password);
				session.setAttribute("user", user);
				response.sendRedirect("orderDeliveryInfo.jsp");
                    }
			else
                        {
				response.sendRedirect("loginFail.jsp");
                        }
		}
	}
    
}
