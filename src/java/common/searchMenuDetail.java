/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;


import bean.*;
import db.dbOperation;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.*;

/**
 *
 * @author sliu1_000
 */
public class searchMenuDetail extends HttpServlet{
	 ArrayList list=null;
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		try{
			list = 
                               dbOperation.getMenuInfo(
					request.getParameter("category"));
		}catch(Exception e){
			System.out.println(e);
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("menuinfo", list);
                session.setAttribute("category", request.getParameter("category"));
		response.setContentType("text/html;charset=GB2312");
		response.sendRedirect("menuFullInfo.jsp");
               
		
	}
	
	/*	
	public static void main(String[] args){
		searchMenu sb = new searchMenu();
		bean.MenuBean book = (bean.MenuBean)sb.list.get(1);
		System.out.println(book.getName());
	}*/
}
