package main.java.login;

import java.util.HashMap;

public class Credentials {
	 
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	
	protected Credentials() {
		loginInfo.put("admin", "123");
		loginInfo.put("employee", "123");
	}
	
	protected HashMap<String, String> getLoginInfo() {
		return loginInfo;
	}
}
