/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

/**
 *
 * @author zhenhua
 */

import db.dbOperation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.*;

public class updateUser extends HttpServlet{
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            PrintWriter out = response.getWriter();	
            
            String userid=request.getParameter("userid");
            String username=request.getParameter("username");
            String role=request.getParameter("role");
            String password=request.getParameter("password");

                try {
                   dbOperation.deleteUserById(userid);    
                   dbOperation.addUser(userid, username, role, password); 
                   out.println("User has been updated.");
                }catch(Exception e){
			System.out.println(e);
		}
                
                response.sendRedirect("userManage.jsp");
                
	}
}
