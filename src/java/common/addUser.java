/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import bean.UserBean;
import db.dbOperation;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sliu1_000
 */
public class addUser extends HttpServlet {

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession session = request.getSession(true);
		//User user = (User)session.getAttribute("user");
		String userid=request.getParameter("userid");
                String username=request.getParameter("username");
                String role=request.getParameter("role");
		String password=request.getParameter("password");
		
                try {
                    dbOperation.addUser(userid, username, role, password);
                    
                }catch(Exception e){
			System.out.println(e);
		}
                
                response.sendRedirect("userManage.jsp");
                
	}
    
}