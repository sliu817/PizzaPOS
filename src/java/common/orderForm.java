package common;

import bean.Cart;
import bean.Customer;
import db.dbOperation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class orderForm extends HttpServlet {
        Customer customer = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            
		HttpSession session = request.getSession(true);
                
                String phone = request.getParameter("phone");
		Cart myCart = (Cart) session.getAttribute("myCart");
		if (myCart != null && !myCart.listMyItems().isEmpty())
                {
                    if(phone!=null)
                    {
                       customer = dbOperation.getCustomer(phone);
                       session.setAttribute("customer", customer);        
                    }
                    response.sendRedirect("orderForm.jsp");
                }	
		//else
			//response.sendRedirect("searchMenuResult.jsp");
	}
}
