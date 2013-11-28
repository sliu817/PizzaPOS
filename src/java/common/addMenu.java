/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.*;
import bean.MenuBean;
import db.dbOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sliu1_000
 */
public class addMenu extends HttpServlet {

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
              
            String category=request.getParameter("category");
            String name=request.getParameter("name");
            String price=request.getParameter("price");
            String description=request.getParameter("description");
            int menuAmount = 0;
            ArrayList<MenuBean> list= new ArrayList<MenuBean>();       
            Element root;	
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            try {
                factory.setIgnoringElementContentWhitespace(true);

                DocumentBuilder db=factory.newDocumentBuilder();
                Document doc =db.parse(new FileInputStream(new File("C:/zhenhua1/xml/menu.xml")));
                root = doc.getDocumentElement();
                XPathFactory xpathFactory=XPathFactory.newInstance();
                XPath xpath=xpathFactory.newXPath();
                NodeList menus =(NodeList)xpath.evaluate("menus/menu",doc,XPathConstants.NODESET);
                menuAmount = menus.getLength();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (XPathExpressionException e) {
                e.printStackTrace();
            }
                //PrintWriter out = response.getWriter();
                //out.println("Hello World");
                //out.println((menuAmount + 1) + " " + category + " " + name + " " +price + " " + description );
            try {
                dbOperation.addMenu(Integer.toString(menuAmount + 1), category, name, price, description);

            }catch(Exception e){
                    System.out.println(e);
            }

            response.sendRedirect("menuManage.jsp");
                
	}
    
}
