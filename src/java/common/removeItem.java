package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;

public class removeItem extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Cart myCart=(Cart)session.getAttribute("myCart");
		String mID=request.getParameter("mID");
				
		if(myCart!=null && mID!=null)
			myCart.deleteItem(mID);
		
		response.sendRedirect("checkCart.jsp");
	}

}
