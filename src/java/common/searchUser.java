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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.*;

public class searchUser extends HttpServlet{
    ArrayList list=null;

    public void init(ServletConfig config)throws ServletException{
            super.init(config);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws
    ServletException,IOException{
                  
            try{
                    list=dbOperation.getUsers( );
            }catch(Exception e){
                    System.out.println(e);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("userList", list);
            response.setContentType("text/html;charset=GB2312");
            response.sendRedirect("searchUserResult.jsp");


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            try{
                    list=dbOperation.getMenus( );
            }catch(Exception e){
                    System.out.println(e);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("userList", list);
            response.setContentType("text/html;charset=GB2312");
            response.sendRedirect("searchUserResult.jsp");
    
    }   
}

