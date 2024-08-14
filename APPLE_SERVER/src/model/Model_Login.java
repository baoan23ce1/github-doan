package model;

import org.json.JSONObject;

public class Model_Login {
	   public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Model_Login(String userName, String password) {
	        this.userName = userName;
	        this.password = password;
	    }

	    public Model_Login() {
	    }

	    private String userName;
	    private String password;

	    public JSONObject toJsonObject() {
	    	try {
				JSONObject json = new JSONObject();
				json.put("type", "login");
				json.put("userName", userName);
				json.put("password", password);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	    }
	    
		public Model_Login(Object json) {
	        JSONObject obj = (JSONObject) json;
	        try {
	        	userName = obj.getString("userName");
	        	password = obj.getString("password");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
