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

public class updateMenu extends HttpServlet{
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            PrintWriter out = response.getWriter();	
            String param =request.getParameter("param");
           
            out.print(param);            
            String menuid=request.getParameter("menuid");
            String category=request.getParameter("category");
            String name=request.getParameter("name");
            String price=request.getParameter("price");
            String description=request.getParameter("description");

            try {
                dbOperation.deleteMenuById(menuid);    
                if(param.equals("update")){
                    dbOperation.addMenu(menuid, category, name, price, description);
                }
               out.println("Menu has been updated.");
            }catch(Exception e){
                    System.out.println(e);
            }

            response.setContentType("text/html;charset=GB2312");
            response.sendRedirect("menuManage.jsp");
                
	}
}
