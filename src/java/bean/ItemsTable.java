package bean;

import java.util.*;

public class ItemsTable {
	
	Hashtable<MenuBean, String> table=null;
	
	public ItemsTable(){
		table=new Hashtable();
	}
	
	public void setTable(Hashtable ht){
		table=(Hashtable)ht.clone();
	}
	
	public Hashtable getTable(){
		return table;
	}
        public int getLength(){
		return table.size();
	}
        
	public void addItem(MenuBean item, String count){
		table.put(item,count);
	}
}
