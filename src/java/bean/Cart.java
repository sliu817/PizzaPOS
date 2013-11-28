package bean;

import java.util.Hashtable;
import java.io.Serializable;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 6152499994129454808L;
	
	private Hashtable myItems = new Hashtable();
	
	public Cart(){}
	

	public void addItem(String mID,int count){
		if(myItems.contains(mID)){
			int tempCount = ((Integer)myItems.get(mID)).intValue();
			tempCount = tempCount + count;
			myItems.put(mID, new Integer(tempCount));
		}
		else{
			myItems.put(mID, new Integer(count));
		}		
	}
	
	public boolean minusItems(String mID,int count){
		if(myItems.containsKey(mID)){
			int tempCount = ((Integer)myItems.get(mID)).intValue();
			tempCount = tempCount-count;
			if(tempCount <= 0)
				deleteItem(mID);
			else
				myItems.put(mID, new Integer(tempCount));
			return true;
		}
		else{
			return false;
		}
	}
	
	public Hashtable listMyItems(){
		return myItems;
	}
	
	public boolean deleteItem(String mID){
		if(myItems.remove(mID)==null)
			return false;
		else
			return true;
	}

	public void clear(){
		myItems.clear();
	}
}
