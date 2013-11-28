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

public class makeOrder extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("cname");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String postalcode = request.getParameter("postalcode");
                String type = request.getParameter("type");

		Cart myCart=null;
		
		Hashtable buyedItems = null;
		HttpSession session=request.getSession(true);
		myCart=(Cart)session.getAttribute("myCart");
		if(myCart!=null)
			buyedItems  = myCart.listMyItems();
                
                Date date = Calendar.getInstance().getTime();       
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");   
                String timestamp = formatter.format(date);   
              
                int order_no = (int)Math.floor(Math.random()*1000000);
                
                try {
			dbOperation.updateCustomerInfo(username,address,
					telephone, postalcode, timestamp);
		} catch (Exception e) {
			System.out.println(e);
		}

		if(buyedItems !=null)
		if (!buyedItems .isEmpty() && dbOperation.makeOrder(order_no,timestamp,telephone, buyedItems, type )) {
			//myCart.clear();
			//session.removeAttribute("myCart");
                         session.setAttribute("type", type);
                        session.setAttribute("myCart", myCart);
                        session.setAttribute("time", timestamp);
                        session.setAttribute("order_no", order_no);
			response.sendRedirect("makeOrderSuccessfully.jsp");
		} else {
			response.sendRedirect("makeOrderFailed.jsp");
		}
	}

}
