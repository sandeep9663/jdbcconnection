package com.deep.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class bcConnection {
	public static void main(String [] args) throws SQLException
	{
		try
		{
			bcConnection jdbc = new bcConnection();
		        try (Connection con1 = getConnection())
		        {
		          
		          List list= jdbc.printCity(con1);
		         System.out.println(list);
			
	
		        } 
		  } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	    }
		        
		        
		    	public static Connection getConnection() {
			        System.out.println("connect with DB!!");
			        Connection con1 = null;
			        try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "P@til9663");
			            System.out.println("Connection done successfully!!!");
			       	 } 
					catch (ClassNotFoundException | SQLException e) 	
				{
			            System.out.println("connection Failed");
			            e.printStackTrace();
			        }
			        return con1;
			    }
 
		   	    
		    	   public static List printCity(Connection con1) throws SQLException
		   	    {
		    		  
		   	    	 Statement st=con1.createStatement();
		   	 	    ResultSet rs= st.executeQuery("SELECT * FROM student WHERE Address='mumbai'");
		   	 	    List list=new ArrayList();
		   	 	    while(rs.next())
		   	 	    {
		   	 	    	Student s1=new Student();
		   	 	    			s1.setId(rs.getInt(1));
		   	 	    			s1.setName(rs.getString(2));
		   	 	    			s1.setAddress(rs.getString(3));
		   	 	      list.add(s1);
		   	 	    }
		   	 	   
		   	 	    return list;
		   	 	    
		   	  } 
		}

	