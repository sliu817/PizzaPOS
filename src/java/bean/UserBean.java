/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author sliu1_000
 */
public class UserBean {
        private final String FOH = "FOH";
	private final String Manager = "Manager";
        private final String Kitchen = "Kitchen";
        private final String Driver = "Driver";
	
	private String userID;
	private String userName;
	private String userRole;
        private String password;
        
        public UserBean(){
		this.userName = "";
		this.userRole = "";
		this.password = "";
                
	}	
          public UserBean(String userid,String password){
		this.userID = userid;
		
		this.password = password;
                
	}
          public UserBean(String userid,String username,String password){
	
                this.userName = username;
		this.userID = userid;
		this.password = password;
                
	}
          
        
	    public UserBean(String userid,String username,String userrole,String password){
                this.userID = userid;
		this.userName = username;
		this.userRole = userrole;
		this.password = password;
                
	}
            public String getUserID(){
		return userID;
	}
            public String getUserName(){
		return userName;
	}
            
            public String getUserRole(){
		return userRole;
	}
            public String getPassword(){
		return password;
	}
            public void setUserID(String userID){
		this.userID = userID;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
        
        public void setUserRole(String userRole){
		if(userRole == FOH)
			this.userRole = "FOH";
                else if(userRole == Manager)
			this.userRole = "Manager";
                else if(userRole == Driver)
                        this.userRole = "Driver";
                else if(userRole == Kitchen)
                        this.userRole = "Kitchen";
	}
	
	public void setPassword(String password){
		this.password = password;
	}
        
        
        
    
}
