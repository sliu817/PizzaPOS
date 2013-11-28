/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import bean.Cart;
import bean.UserBean;
import db.dbOperation;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class checkOut extends HttpServlet {
    ArrayList list=null;
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession session = request.getSession(true);
		String checkType=request.getParameter("checkType");
		Cart myCart = (Cart) session.getAttribute("myCart");

                 if(checkType.equals("dine_in")){
                    session.setAttribute("myCart", myCart);
                     response.sendRedirect("dineInCheckOut.jsp");
                 
                 }else if(checkType.equals("delivery"))
                 {
                    session.setAttribute("myCart", myCart);
                    response.sendRedirect("deliveryCheckOut.jsp");
                 
                 }
                         
                     
		
		
	}
    
}
