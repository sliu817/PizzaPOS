/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

/**
 *
 * @author MadPotatoes
 */
public class dbOperation {   
    /*
     * Get all Menus
     */
    public static ArrayList getMenus() {
        ArrayList<MenuBean> list= new ArrayList<MenuBean>();  
        Element root;	
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root = doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            NodeList menus =(NodeList)xpath.evaluate("menus/menu",doc,XPathConstants.NODESET);
            for(int i = 0; i<menus.getLength();i++)
            {
                Node n = menus.item(i);
                String mid= (String)xpath.evaluate("@mid", n,XPathConstants.STRING);
                String name= (String)xpath.evaluate("name", n,XPathConstants.STRING);
                String category= (String)xpath.evaluate("category", n,XPathConstants.STRING);
                String p= (String)xpath.evaluate("price", n,XPathConstants.STRING);
                double price = Double.parseDouble(p);
               
                String description= (String)xpath.evaluate("description", n,XPathConstants.STRING);
                MenuBean menu = new MenuBean(mid, category,name, price, description);
                list.add(menu);
            }        
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
           return list;
	}
    
    /*
     * GET ALL orders
     */
  
      public static ArrayList getAllOrders() {
        ArrayList<OrderBean> order_list= new ArrayList<OrderBean>();
        OrderBean order = new OrderBean();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        NodeList theOrder=null;
        Element root_order;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc_order =db.parse(new FileInputStream(new File("D:/xml/order.xml")));
            Document doc_menu =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root_order = doc_order.getDocumentElement();
            Element root_menu = doc_menu.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theOrder =(NodeList)xpath.evaluate("/orders/order",doc_order,XPathConstants.NODESET);
            
            for(int i = 0; i<theOrder.getLength();i++)
            {
                Node n = theOrder.item(i);
                String cid= (String)xpath.evaluate("@customer", n,XPathConstants.STRING);
                String time= (String)xpath.evaluate("@time", n,XPathConstants.STRING);
                int ono = Integer.parseInt((String)xpath.evaluate("@ono", n,XPathConstants.STRING));
                String status= (String)xpath.evaluate("status", n,XPathConstants.STRING);
                            
                NodeList items =(NodeList)xpath.evaluate("items/item",n,XPathConstants.NODESET);
                XPathFactory xpathFactory2=XPathFactory.newInstance();
                XPath xpath2=xpathFactory2.newXPath();
                
                ItemsTable items_table = new ItemsTable();    
                for(int j = 0; j<items.getLength();j++)
                { 
                    Node item = items.item(j);
                    String mid= (String)xpath2.evaluate("@id", item, XPathConstants.STRING);
                    String quantity= (String)xpath2.evaluate("quantity", item, XPathConstants.STRING);
                   
                    XPathFactory xpathFactory3=XPathFactory.newInstance();
                    XPath xpath3=xpathFactory3.newXPath();
                    
                    Node theMenu =(Node)xpath3.evaluate("/menus/menu[@mid='"+mid+ "']",doc_menu,XPathConstants.NODE);
                    String category= (String)xpath3.evaluate("category", theMenu,XPathConstants.STRING);
                    String name= (String)xpath3.evaluate("name", theMenu,XPathConstants.STRING);
                    double price= Double.parseDouble((String)xpath.evaluate("price", theMenu,XPathConstants.STRING));
                    String description= (String)xpath3.evaluate("description", theMenu,XPathConstants.STRING);
          
                    MenuBean menu = new MenuBean(mid, category, name, price, description);
                    items_table.addItem(menu,quantity);  
                 }
            order = new OrderBean(cid, ono,time, status,  items_table);
            order_list.add(order);
          }
           
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       return order_list; 
     }
      
public static ArrayList getOrders(String type) {
        ArrayList<OrderBean> order_list= new ArrayList<OrderBean>();
        OrderBean order = new OrderBean();

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        NodeList theOrder=null;
        Element root_order;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc_order =db.parse(new FileInputStream(new File("D:/xml/order.xml")));
            Document doc_menu =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root_order = doc_order.getDocumentElement();
            Element root_menu = doc_menu.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theOrder =(NodeList)xpath.evaluate("/orders/order[type='"+type+ "']",doc_order,XPathConstants.NODESET);
           
            for(int i = 0; i<theOrder.getLength();i++)
            {
                Node n = theOrder.item(i);
                String time= (String)xpath.evaluate("@time", n,XPathConstants.STRING);
                int ono = Integer.parseInt((String)xpath.evaluate("@ono", n,XPathConstants.STRING));
                String status= (String)xpath.evaluate("status", n,XPathConstants.STRING);
                String cid= (String)xpath.evaluate("@customer", n,XPathConstants.STRING);
          
                NodeList items =(NodeList)xpath.evaluate("items/item",n,XPathConstants.NODESET);
                XPathFactory xpathFactory2=XPathFactory.newInstance();
                XPath xpath2=xpathFactory2.newXPath();
                
                ItemsTable items_table = new ItemsTable();
                for(int j = 0; j<items.getLength();j++)
                { 
                    Node item = items.item(j);
                    String mid= (String)xpath2.evaluate("@id", item, XPathConstants.STRING);
                    String quantity= (String)xpath2.evaluate("quantity", item, XPathConstants.STRING);
                   
                    XPathFactory xpathFactory3=XPathFactory.newInstance();
                    XPath xpath3=xpathFactory3.newXPath();
                    
                    Node theMenu =(Node)xpath3.evaluate("/menus/menu[@mid='"+mid+ "']",doc_menu,XPathConstants.NODE);
                    String category= (String)xpath3.evaluate("category", theMenu,XPathConstants.STRING);
                    String name= (String)xpath3.evaluate("name", theMenu,XPathConstants.STRING);
                    double price= Double.parseDouble((String)xpath.evaluate("price", theMenu,XPathConstants.STRING));
                    String description= (String)xpath3.evaluate("description", theMenu,XPathConstants.STRING);
  
                    MenuBean menu = new MenuBean(mid, category, name, price, description);
                    items_table.addItem(menu,quantity);   
                   }
                   if(cid!=null)
                        order = new OrderBean(cid, ono,time, status, items_table,type);
                    else
                        order = new OrderBean(ono,time, status,items_table,type);
                order_list.add(order);   
            }
           
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       
            return order_list; 
     }
      
            
            
     public static ArrayList getMenuInfo(String category){
        ArrayList<MenuBean> list= new ArrayList<MenuBean>();
		
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        NodeList theMenu=null;
        Element root;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root = doc.getDocumentElement();
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theMenu =(NodeList)xpath.evaluate("/menus/menu[category='"+category+ "']",doc,XPathConstants.NODESET);
            
            for(int i = 0; i<theMenu.getLength();i++)
            {
                Node n = theMenu.item(i);
                String mid= (String)xpath.evaluate("@mid", n,XPathConstants.STRING);
                String name= (String)xpath.evaluate("name", n,XPathConstants.STRING);
                double price= Double.parseDouble((String)xpath.evaluate("price", n,XPathConstants.STRING));
                String description= (String)xpath.evaluate("description", n,XPathConstants.STRING);
           
                MenuBean menu = new MenuBean(mid,category, name, price, description);
                list.add(menu);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }   
            return list; 
     }
     
public static MenuBean getItemInfo(String mid){
        MenuBean theItem= null;
		
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Node item = null;
        Element root;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root = doc.getDocumentElement();
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            item =(Element)xpath.evaluate("/menus/menu[@mid='"+mid+ "']",doc,XPathConstants.NODE);
 
            String category= (String)xpath.evaluate("category", item,XPathConstants.STRING);
            String name= (String)xpath.evaluate("name", item,XPathConstants.STRING);
            double price= Double.parseDouble((String)xpath.evaluate("price", item,XPathConstants.STRING));
            String description= (String)xpath.evaluate("description",item,XPathConstants.STRING);
            theItem = new MenuBean(mid,category, name, price, description);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       
           return theItem;
     }
     
     
  public static ArrayList getOrdersInfo(String phone){
        ArrayList<OrderBean> order_list= new ArrayList<OrderBean>();
        OrderBean order = new OrderBean();

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        NodeList theOrder=null;
        Element root_order;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc_order =db.parse(new FileInputStream(new File("C:/Users/sliu1_000/Documents/NetBeansProjects/PizzaPOS/web/xml/order.xml")));
            Document doc_menu =db.parse(new FileInputStream(new File("C:/Users/sliu1_000/Documents/NetBeansProjects/PizzaPOS/web/xml/menu.xml")));
            
         
            root_order = doc_order.getDocumentElement();
            Element root_menu = doc_menu.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theOrder =(NodeList)xpath.evaluate("/orders/order[@customer='"+phone+"']",doc_order,XPathConstants.NODESET);
            
            for(int i = 0; i<theOrder.getLength();i++)
            {
                Node n = theOrder.item(i);
                String time= (String)xpath.evaluate("@time", n,XPathConstants.STRING);
                int ono = Integer.parseInt((String)xpath.evaluate("@ono", n,XPathConstants.STRING));
                String status= (String)xpath.evaluate("status", n,XPathConstants.STRING);
                            
                NodeList items =(NodeList)xpath.evaluate("items/item",n,XPathConstants.NODESET);
                XPathFactory xpathFactory2=XPathFactory.newInstance();
                XPath xpath2=xpathFactory2.newXPath();
                
                ItemsTable items_table = new ItemsTable(); 
                for(int j = 0; j<items.getLength();j++)
                { 
                    Node item = items.item(j);
                    String mid= (String)xpath2.evaluate("@id", item, XPathConstants.STRING);
                    String quantity= (String)xpath2.evaluate("quantity", item, XPathConstants.STRING);
                   
                    XPathFactory xpathFactory3=XPathFactory.newInstance();
                    XPath xpath3=xpathFactory3.newXPath();
                    
                    Node theMenu =(Node)xpath3.evaluate("/menus/menu[@mid='"+mid+ "']",doc_menu,XPathConstants.NODE);
                    String category= (String)xpath3.evaluate("category", theMenu,XPathConstants.STRING);
                    String name= (String)xpath3.evaluate("name", theMenu,XPathConstants.STRING);
                    double price= Double.parseDouble((String)xpath.evaluate("price", theMenu,XPathConstants.STRING));
            
                    String description= (String)xpath3.evaluate("description", theMenu,XPathConstants.STRING);
                    MenuBean menu = new MenuBean(mid, category, name, price, description);
                    items_table.addItem(menu,quantity); 
               }
            order = new OrderBean(phone, ono,time, status,  items_table);
            order_list.add(order);
                 
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       
            return order_list; 
     }
  
  
  public static ArrayList getOrdersByStatus(String status){
        ArrayList<OrderBean> order_list= new ArrayList<OrderBean>();
        OrderBean order = new OrderBean();

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        NodeList theOrder=null;
        Element root_order;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc_order =db.parse(new FileInputStream(new File("D:/xml/order.xml")));
            Document doc_menu =db.parse(new FileInputStream(new File("D:/xml/menu.xml")));
            root_order = doc_order.getDocumentElement();
            Element root_menu = doc_menu.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theOrder =(NodeList)xpath.evaluate("/orders/order[status='"+status+"']",doc_order,XPathConstants.NODESET);
            
            for(int i = 0; i<theOrder.getLength();i++)
            {
                Node n = theOrder.item(i);
                String time= (String)xpath.evaluate("@time", n,XPathConstants.STRING);
                int ono = Integer.parseInt((String)xpath.evaluate("@ono", n,XPathConstants.STRING));
                String phone = (String)xpath.evaluate("@customer", n,XPathConstants.STRING);
                NodeList items =(NodeList)xpath.evaluate("items/item",n,XPathConstants.NODESET);
                XPathFactory xpathFactory2=XPathFactory.newInstance();
                XPath xpath2=xpathFactory2.newXPath();
                
                ItemsTable items_table = new ItemsTable(); 
                for(int j = 0; j<items.getLength();j++)
                { 
                    Node item = items.item(j);
                    String mid= (String)xpath2.evaluate("@id", item, XPathConstants.STRING);
                    String quantity= (String)xpath2.evaluate("quantity", item, XPathConstants.STRING);
                   
                    XPathFactory xpathFactory3=XPathFactory.newInstance();
                    XPath xpath3=xpathFactory3.newXPath();
                    
                    Node theMenu =(Node)xpath3.evaluate("/menus/menu[@mid='"+mid+ "']",doc_menu,XPathConstants.NODE);
                    String category= (String)xpath3.evaluate("category", theMenu,XPathConstants.STRING);
                    String name= (String)xpath3.evaluate("name", theMenu,XPathConstants.STRING);
                    double price= Double.parseDouble((String)xpath.evaluate("price", theMenu,XPathConstants.STRING));
            
                    String description= (String)xpath3.evaluate("description", theMenu,XPathConstants.STRING);
                    MenuBean menu = new MenuBean(mid, category, name, price, description);
                    items_table.addItem(menu,quantity); 
               }
            order = new OrderBean(phone, ono,time, status, items_table);
            order_list.add(order);
                 
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
       
            return order_list; 
     }
  
  public static Customer getCustomer(String phone)
  {
        Customer customer = null;
        Element root;
		
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document cus_doc =db.parse(new FileInputStream(new File("D:/xml/customer.xml")));
            root = cus_doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            Node theCustomer =(Node)xpath.evaluate("/customers/customer[@phone='"+phone+ "']",cus_doc,XPathConstants.NODE);
            String name= (String)xpath.evaluate("name", theCustomer,XPathConstants.STRING);
            String address= (String)xpath.evaluate("address", theCustomer,XPathConstants.STRING);
            String postal= (String)xpath.evaluate("postalcode", theCustomer,XPathConstants.STRING);
            
            customer = new Customer(name, address,phone, postal);
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
            return customer;
	}
    
    public static String getUserNameByID(String userid)
   {
        String username="";
        Element root;
		
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document user_doc =db.parse(new FileInputStream(new File("D:/xml/user.xml")));
            root =user_doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            Node theCustomer =(Node)xpath.evaluate("/users/user[@userid='"+userid+ "']",user_doc,XPathConstants.NODE);
            username= (String)xpath.evaluate("name", theCustomer,XPathConstants.STRING);
        
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
            return username;
	}
     
  public static String isUserValidate(String userid,String password){
        String userRole = "";	
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
 
        Element root_user;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc_user =db.parse(new FileInputStream(new File("D:/xml/user.xml")));
            root_user = doc_user.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            Node theUser =(Node)xpath.evaluate("/users/user[@userid='"+userid+ "']",doc_user,XPathConstants.NODE);
            String pwd = (String)xpath.evaluate("password", theUser,XPathConstants.STRING);
            if(pwd != null && password.trim().length() !=0 && pwd.equals(password))
            {
                userRole = (String)xpath.evaluate("role", theUser,XPathConstants.STRING);
            }
         } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return userRole; 
    }
  
 /* 
 public static void addUser(String userid, String username, String role, String password)
 {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element theUser=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document user_doc=db.parse(new File("D:/xml/user.xml"));
            root=user_doc.getDocumentElement();

            theUser=user_doc.createElement("user");
            theUser.setAttribute("userid", userid);
            theElem=user_doc.createElement("username");
            theElem.setTextContent(username);
            theUser.appendChild(theElem);
            
            theElem=user_doc.createElement("role");
            theElem.setTextContent(role);
            theUser.appendChild(theElem);
            theElem=user_doc.createElement("password");
            theElem.setTextContent(password);
            theUser.appendChild(theElem);
            root.appendChild(theUser);
            
            saveXml("D:/xml/user.xml", user_doc);
            
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      }*/
 
      
      
public static void addCustomerInfo(String cname,String address,
			String telephone,String postalcode, String time){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element  theCustomer=null, theElem=null, root=null, orderElem =null;
        try {
            factory.setIgnoringElementContentWhitespace(true);    
            DocumentBuilder db=factory.newDocumentBuilder();
            Document customer_doc=db.parse(new File("D:/xml/customer.xml"));
            root=customer_doc.getDocumentElement();
            
            theCustomer=customer_doc.createElement("customer");
            theCustomer.setAttribute("phone", telephone);
            theElem=customer_doc.createElement("name");
            theElem.setTextContent(cname);
            theCustomer.appendChild(theElem);
            
            theElem=customer_doc.createElement("address");
            theElem.setTextContent(address);
            theCustomer.appendChild(theElem);
            theElem=customer_doc.createElement("postalcode");
            theElem.setTextContent(postalcode);
            theCustomer.appendChild(theElem);
             
            theElem = customer_doc.createElement("orders");
            orderElem = customer_doc.createElement("order");
            orderElem.setAttribute("time", time);
            theElem.appendChild(orderElem);
            theCustomer.appendChild(theElem);
             
            root.appendChild(theCustomer);
            saveXml("D:/xml/customer.xml", customer_doc);
            
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
      }
         
    public static void updateCustomerInfo(String cname,String address,
			String telephone,String postalcode, String time) throws XPathExpressionException{
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element  theCustomer=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document customer_doc=db.parse(new File("D:/xml/customer.xml"));
            root=customer_doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            Node customer =(Node)xpath.evaluate("/customers/customer[@phone='"+telephone+ "']",customer_doc,XPathConstants.NODE);
            if(customer != null)
            {
                updateCustomerOrder(cname, address, telephone, postalcode, time);
            }else
                addCustomerInfo(cname, address, telephone, postalcode, time);
  
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      } 
   public static void updateCustomerOrder(String cname,String address,
			String telephone,String postalcode, String time) throws XPathExpressionException{
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element  theCustomer=null, theElem=null, root=null, orderElem =null;
        try {
            factory.setIgnoringElementContentWhitespace(true);     
            DocumentBuilder db=factory.newDocumentBuilder();
            Document customer_doc=db.parse(new File("D:/xml/customer.xml"));
            root=customer_doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            Node customer =(Node)xpath.evaluate("/customers/customer[@phone='"+telephone+ "']",customer_doc,XPathConstants.NODE);
            theElem = (Element)xpath.evaluate("name",customer,XPathConstants.NODE);
            theElem.setTextContent(cname);
            theElem = (Element)xpath.evaluate("address",customer,XPathConstants.NODE);
            theElem.setTextContent(address);
            theElem = (Element)xpath.evaluate("postalcode",customer,XPathConstants.NODE);
            theElem.setTextContent(postalcode);
            
            theElem = (Element)xpath.evaluate("orders",customer,XPathConstants.NODE);
            orderElem = customer_doc.createElement("order");
            orderElem.setAttribute("time", time);
            theElem.appendChild(orderElem);
  
            saveXml("D:/xml/customer.xml", customer_doc);
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      }
        
         
    public static void deleteCustomerInfo(String phone) throws XPathExpressionException
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element theCustomer=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true); 
            DocumentBuilder db=factory.newDocumentBuilder();
            Document cus_doc=db.parse(new File("D:/xml/customer.xml"));
            root=cus_doc.getDocumentElement();
           
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theCustomer =(Element)xpath.evaluate("/customers/customer[@phone='"+phone+ "']",cus_doc,XPathConstants.NODE);  
            theCustomer.getParentNode().removeChild(theCustomer);   
            saveXml("D:/xml/customer.xml", cus_doc);
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
      
      }
        
    public static boolean makeOrder(int ono, String time,String telephone,Hashtable buyedItems, String type){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element  theOrder=null, theItems=null, root=null, item=null, theElem;
        try {
            factory.setIgnoringElementContentWhitespace(true); 
            DocumentBuilder db=factory.newDocumentBuilder();
            Document order_doc=db.parse(new File("D:/xml/order.xml"));
            root=order_doc.getDocumentElement();
            
            theOrder=order_doc.createElement("order");
            theOrder.setAttribute("ono", Integer.toString(ono));
            theOrder.setAttribute("time", time);
            theOrder.setAttribute("customer", telephone);
            theItems=order_doc.createElement("items");
            Enumeration enume = buyedItems.keys();
            while (enume.hasMoreElements()) {
                 String itemKey = (String) enume.nextElement();
                 item = order_doc.createElement("item");
                 item.setAttribute("id", itemKey);
                 theItems.appendChild(item);
                 
                 int count = ((Integer) buyedItems.get(itemKey)).intValue();
                 theElem = order_doc.createElement("quantity");
                 theElem.setTextContent(String.valueOf(count));
                 item.appendChild(theElem);        
                 
             }
            theItems.appendChild(item);
            theElem = order_doc.createElement("status");
            theElem.setTextContent("accepted");
                  
            theOrder.appendChild(theItems);
            theOrder.appendChild(theElem);
                   
            theElem = order_doc.createElement("type");
            theElem.setTextContent(type);
            theOrder.appendChild(theElem);
                 
            root.appendChild(theOrder);
            saveXml("D:/xml/order.xml", order_doc);
            return true;
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
              return false; 
      }
         
         
    public static boolean makeDineinOrder(int ono, String time,Hashtable buyedItems, String type){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element  theOrder=null, theItems=null, root=null, item=null, theElem;
        try {
            factory.setIgnoringElementContentWhitespace(true);  
            DocumentBuilder db=factory.newDocumentBuilder();
            Document order_doc=db.parse(new File("D:/xml/order.xml"));
            root=order_doc.getDocumentElement();

            theOrder=order_doc.createElement("order");
            theOrder.setAttribute("ono", Integer.toString(ono));
            theOrder.setAttribute("time", time);
            theItems=order_doc.createElement("items");
            Enumeration enume = buyedItems.keys();
            while (enume.hasMoreElements()) {
                 String itemKey = (String) enume.nextElement();
             
                 item = order_doc.createElement("item");
                 item.setAttribute("id", itemKey);
                 theItems.appendChild(item);
                 
                 int count = ((Integer) buyedItems.get(itemKey)).intValue();
                 theElem = order_doc.createElement("quantity");
                 theElem.setTextContent(String.valueOf(count));
                 item.appendChild(theElem);        
                 
             }
             theItems.appendChild(item);
             theElem = order_doc.createElement("status");
             theElem.setTextContent("accepted");
             theOrder.appendChild(theItems);
             theOrder.appendChild(theElem);
             theElem = order_doc.createElement("type");
             theElem.setTextContent(type);
             theOrder.appendChild(theElem);
             
            root.appendChild( theOrder);
            System.out.println("--- new order starts. ----");
            saveXml("D:/xml/order.xml", order_doc);
            return true;
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
    }
          
    public static void updateOrderStatus (String ono, String status) throws XPathExpressionException
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element theOrder=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true); 
            DocumentBuilder db=factory.newDocumentBuilder();
            Document order_doc=db.parse(new File("D:/xml/order.xml"));
            root=order_doc.getDocumentElement();
            
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theOrder =(Element)xpath.evaluate("/orders/order[@ono='"+ono+ "']",order_doc,XPathConstants.NODE);
            
            theOrder.getElementsByTagName("status").item(0).setTextContent(status);
            saveXml("D:/xml/order.xml", order_doc);   
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void addUser(String userid, String username, String role, String password)
      {
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
         Element theUser=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document user_doc=db.parse(new File("D:/xml/user.xml"));
            root=user_doc.getDocumentElement();
            
      
            theUser=user_doc.createElement("user");
            theUser.setAttribute("userid", userid);
            theElem=user_doc.createElement("name");
            theElem.setTextContent(username);
            theUser.appendChild(theElem);
            
            theElem=user_doc.createElement("role");
            theElem.setTextContent(role);
            theUser.appendChild(theElem);
            theElem=user_doc.createElement("password");
            theElem.setTextContent(password);
            theUser.appendChild(theElem);
            root.appendChild(theUser);
            System.out.println("--- new user starts. ----");
           
            saveXml("D:/xml/user.xml", user_doc);
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      }
    
    public static void deleteUserById(String userid) throws XPathExpressionException
      {
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
         Element theUser=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document user_doc=db.parse(new File("D:/xml/user.xml"));
            root=user_doc.getDocumentElement();
           
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theUser =(Element)xpath.evaluate("/users/user[@userid='"+userid+ "']",user_doc,XPathConstants.NODE);
            
            theUser.getParentNode().removeChild(theUser);
  
            saveXml("D:/xml/user.xml", user_doc);
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
      
      }
     public static ArrayList getUsers() {
        ArrayList<UserBean> list= new ArrayList<UserBean>();
        
        Element root;
		
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc =db.parse(new FileInputStream(new File("D:/xml/user.xml")));
            root = doc.getDocumentElement();
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            NodeList users =(NodeList)xpath.evaluate("users/user",doc,XPathConstants.NODESET);
            for(int i = 0; i<users.getLength();i++)
            {
                Node n = users.item(i);
                String userid = (String)xpath.evaluate("@userid", n,XPathConstants.STRING);
                String name = (String)xpath.evaluate("name", n,XPathConstants.STRING);
                String role = (String)xpath.evaluate("role", n,XPathConstants.STRING);
                String password = (String)xpath.evaluate("password", n,XPathConstants.STRING);               
 
                UserBean user = new UserBean(userid, name, role, password);
                list.add(user);
            }       
        
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
           return list;
	}
      public static void addMenu(String menuID, String category, String name, String price, String description)
      {
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
         Element theMenu =null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document user_doc=db.parse(new File("D:/xml/menu.xml"));
            root=user_doc.getDocumentElement();
            
            theMenu=user_doc.createElement("menu");
            theMenu.setAttribute("mid", menuID);
            
            theElem=user_doc.createElement("category");
            theElem.setTextContent(category);
            theMenu.appendChild(theElem);
            
            theElem=user_doc.createElement("name");
            theElem.setTextContent(name);
            theMenu.appendChild(theElem);
            
            theElem=user_doc.createElement("price");
            theElem.setTextContent(price);
            theMenu.appendChild(theElem);
            
            theElem=user_doc.createElement("description");
            theElem.setTextContent(description);
            theMenu.appendChild(theElem);
            root.appendChild(theMenu);
            System.out.println("--- new user starts. ----");
           
        
        
            saveXml("D:/xml/menu.xml", user_doc);
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
      }  
      
       public static void deleteMenuById(String menuid) throws XPathExpressionException
      {
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
         Element theMenu=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
            Document menu_doc=db.parse(new File("D:/xml/menu.xml"));
            root=menu_doc.getDocumentElement();
           
            XPathFactory xpathFactory=XPathFactory.newInstance();
            XPath xpath=xpathFactory.newXPath();
            theMenu =(Element)xpath.evaluate("/menus/menu[@mid='"+menuid+ "']",menu_doc,XPathConstants.NODE);
            
            theMenu.getParentNode().removeChild(theMenu);

            saveXml("D:/xml/menu.xml", menu_doc);
            
           }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
      
      }
	   
    public static void saveXml(String fileName, Document doc) {
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source=new DOMSource();
            source.setNode(doc);
            StreamResult result=new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName)); 
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }        
	
	
}
