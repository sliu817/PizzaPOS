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
public class updateStatus extends HttpServlet {

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession session = request.getSession(true);
		
		String newStatus=request.getParameter("status");
                String orderNo=request.getParameter("ono");
		
                try {
                    dbOperation.updateOrderStatus(orderNo, newStatus);
                    response.sendRedirect("kitchen.jsp");
                }catch(Exception e){
			System.out.println(e);
		}
                
                
                
	}
    
}