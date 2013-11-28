package common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
/*
	public void init() throws ServletException {
		// Put your code here
	}*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		session.removeAttribute("user");
		session.removeAttribute("myCart");
		response.sendRedirect("index.jsp");
	}
}
