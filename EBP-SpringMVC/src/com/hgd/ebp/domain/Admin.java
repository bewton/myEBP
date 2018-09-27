package com.hgd.ebp.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Admin implements Serializable {
    private int uid;
    private String username;
    private String password;
    
    public Admin() {}
    
    public Admin(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    
    public int getUid() {
        return uid;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

	@Override
	public String toString() {
		return "Admin [uid=" + uid + ", username=" + username + ", password="
				+ password + "]";
	}
}
