package bean;

public class Customer {
	
	private String cName;
	private String address;
	private String phone;
	private String postalCode;

	
	public Customer(){
	
		this.cName = "";
		this.address = "";
		this.phone = "";
		this.postalCode = "";
		
	}	
	
	public Customer(String phone,String cname){
		
		this.cName = cname;
		this.address = "";
		this.phone = "";
		this.postalCode = "";
		
	}	
	
	public Customer(String cName,String address,
			String telephone,String postalCode ){		
		
		this.cName = cName;
		this.address = address;
		this.phone = telephone;
		this.postalCode = postalCode;		
	}
	
	public String getPhone(){
		return phone;
	}

	public String getCustomerName(){
		return cName;
	}
	
	
	public String getAddress(){
		return address;
	}
	
	
	public String getPostalCode(){
		return postalCode;
	}
	
	
	public void setCustomerName(String cName){
		this.cName = cName;
	}
	
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setPhone(String telephone){
		this.phone = telephone;
	}
	
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	
	
}
