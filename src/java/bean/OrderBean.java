/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author sliu1_000
 */
public class OrderBean
{
	private String customerID=null;
        private int orderNo;
	private String addTime=null;
	private String status="-1";
        private String type="";
	private ItemsTable order=null;
        
        public OrderBean()
        {
            order = new ItemsTable();
        }
	
	public OrderBean(String cID, int orderNo,String addTime,String status, ItemsTable items){
		this.customerID=cID;
                this.orderNo = orderNo;
		this.addTime=addTime;
		this.status=status;
                this.order = items;
	}
        public OrderBean(String cID, int orderNo,String addTime,String status, ItemsTable items, String type){
		this.customerID=cID;
                this.orderNo = orderNo;
		this.addTime=addTime;
		this.status=status;
                this.order = items;
                this.type = type;
	}
        
        public OrderBean(int orderNo,String addTime,String status, ItemsTable items, String type){
                this.orderNo = orderNo;
		this.addTime=addTime;
		this.status=status;
                this.order = items;
                this.type = type;
	}
        
        public String getOrderType()
        {
            return type;
        }
        
        public String getCustomerID()
        {
            return customerID;
        }
         public String getAddTime()
        {
            return addTime;
        }
         
          public String getStatus()
        {
            return status;
        }
          
           public ItemsTable getItemsTable()
        {
            return order;
        }
           public int getOrderNo()
           {
               return orderNo;
           }
        
       
}