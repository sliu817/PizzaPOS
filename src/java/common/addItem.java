/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import bean.Cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addItem extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Cart myCart=(Cart)session.getAttribute("myCart");
		String mID=request.getParameter("mID");
		
		int count =0;
		try{
			count=Integer.parseInt(request.getParameter("count"));
		}catch(NumberFormatException e){
			count=0;
		}
		count = count <= 0 ? 1 : count;
		
		if(myCart!=null)
			myCart.addItem(mID, count);
		
		response.sendRedirect("checkCart.jsp");
	}

}
