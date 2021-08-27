package login;

import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

import com.sun.javafx.font.Disposer;

import Administrator.AdminDashBoard;
import database.DatabaseHelper;
import javabean.AirportDataTableModel;

import java.awt.Color;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.*;


public class Login {
	public Login() {
		JFrame f=new JFrame("Airline Ticket Reservataion");
        f.setVisible(true);
        JLabel jl1=new JLabel("User Login");
        jl1.setBounds(260,50,300,80);
        JLabel jl2=new JLabel("Emai Id    ");
        jl2.setBounds(180,90,300,100);
        JLabel jl3=new JLabel("Password  ");
        jl3.setBounds(180,130,300,100);
        JTextField jt=new JTextField();
        jt.setBounds(250,125,150,25);
        JPasswordField jt1=new JPasswordField();
        jt1.setBounds(250,165,150,25);
        JButton jb=new JButton("Log in");
        jb.setBounds(260,210,100,30);
        JLabel jl4=new JLabel("-------------------------- or -------------------------");
        jl4.setBounds(180, 220, 300, 100);
        JLabel jl5=new JLabel("New User ? ");
        jl5.setBounds(190,250,300,100);
        JButton jb1=new JButton("Register");
        jb1.setBounds(265,285,100,30);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("F:\\vijay\\BootDocs\\login2.jpg")));
        f.setResizable(false);
        f.setBounds(10, 10, 900, 650);
        f.add(jl1);
        f.add(jl2);
        f.add(jl3);
        f.add(jl3);
        f.add(jt);
        f.add(jt1);
        f.add(jb);
        f.add(jl4);
        f.add(jl5);
        f.add(jb1);
        f.setVisible(true);
      
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	f.dispose();
                new Register();
                
            }
            
        });
		
	
    jb.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	DatabaseHelper helper = new DatabaseHelper();
            String email="";
            String role="";
            String password="";
            Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("getLoginDetails");
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    email = dis.readUTF();
			    dataOutputStream.writeUTF(jt.getText());
			    password = dis.readUTF();
			    role = dis.readUTF();
			    
			} catch (Exception ex) {
				
				ex.printStackTrace();
			} 
            
            if(!email.contains(jt.getText())){
                JOptionPane.showMessageDialog(jb,"Email Id is Invalid");
                    return;
            }
            else if(!password.equals(jt1.getText())){
                JOptionPane.showMessageDialog(jb,"Password is Invalid");
                    return;

            }
            else{
                JOptionPane.showMessageDialog(jb,"Login successfully");
                if(role.equals("customer")) {
                	new AdminDashBoard();
                }
                else if(role.equals("admin")) {
                	
                }
                f.dispose();
                
            }

        }});
	}
	public static void main(String[] aa) {
		
        new Login();

    
    }
}
