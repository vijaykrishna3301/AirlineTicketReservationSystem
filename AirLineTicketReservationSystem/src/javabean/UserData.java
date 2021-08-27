package javabean;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
public class UserData implements Serializable{
	private int userId;
    private  String name;
    private String email;
    private String mobNo;
    private String password;
    private String role;
    public int getUserId() {
		return userId;
	}
    public void setUserId(int userId) {
		this.userId = userId;
	}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;

    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;

    }
    public String getMobno(){
        return mobNo;
    }
    public void setMobNo(String mobNo){
        this.mobNo=mobNo;

    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;

    }
    public String getRole() {
		return role;
	}
    public void setRole(String role) {
		this.role = role;
	}

}