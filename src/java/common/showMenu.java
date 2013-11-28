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
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.*;
import java.io.*;

public class showMenu extends HttpServlet{
    ArrayList list=null;

    public void init(ServletConfig config)throws ServletException{
            super.init(config);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws
    ServletException,IOException{
           
            try{
                    list=dbOperation.getMenus( );
            }catch(Exception e){
                    System.out.println(e);

            }
            String param =request.getParameter("p");
            PrintWriter out = response.getWriter();
            //out.print(p);
            HttpSession session = request.getSession(true);
            session.setAttribute("menuList", list);
            session.setAttribute("param", param);
            response.setContentType("text/html;charset=GB2312");
                    
            response.sendRedirect("showMenuResult.jsp");
            
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           
            try{
                    list=dbOperation.getMenus( );
            }catch(Exception e){
                    System.out.println(e);
            }
    
    //out.println("hello " + list.size());

            HttpSession session = request.getSession(true);
            session.setAttribute("menuList", list);
            response.setContentType("text/html;charset=GB2312");
            response.sendRedirect("showMenuResult.jsp");

    }   
}

