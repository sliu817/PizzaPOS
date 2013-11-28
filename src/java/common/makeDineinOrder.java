package common;

import db.dbOperation;
import bean.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class makeDineinOrder extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession session = request.getSession(true);
		Cart myCart = (Cart) session.getAttribute("myCart");
                Hashtable buyedItems = null;
		if(myCart!=null)
			buyedItems  = myCart.listMyItems();
                
                Date date = Calendar.getInstance().getTime();       
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");   
                String timestamp = formatter.format(date);   
                int order_no = (int)Math.floor(Math.random()*1000000);
                String type=request.getParameter("type");
               
		if(buyedItems !=null)
		if (!buyedItems .isEmpty() && dbOperation.makeDineinOrder(order_no,timestamp, buyedItems,type)) {
			//myCart.clear();
			//session.removeAttribute("myCart");
                        session.setAttribute("order_no", order_no);
                        session.setAttribute("type", type);
                        session.setAttribute("time", timestamp);
                        session.setAttribute("myCart", myCart);
			response.sendRedirect("makeOrderSuccessfully.jsp");
		} else {
			response.sendRedirect("makeOrderFailed.jsp");
		}
	}

}
