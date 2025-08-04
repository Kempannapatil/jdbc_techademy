package jdbc_start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//1 we need import package
//2 load and register the package:com.mysql.jdbc.driver
//3 establish the connection
//4 create the statement
//5 Execute  query
//6 procees the reasult
//7 close the connection


public class Jdbc_demo {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String pwd = "Darsh@#123"; 
        String query="select * from student";

        Class.forName("com.mysql.cj.jdbc.Driver"); 
        System.out.println("driver loaded");

       Connection con = DriverManager.getConnection(url, username, pwd);
       System.out.println("Connection successful");
       
        Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);
    
        
       ResultSet rs= st.executeQuery(query);
        System.out.println("query executed");
       //  accessing using column number and column name
      //  System.out.println(res.getInt(1)+ " "+ res.getString(2)+" "+res.getString(3));
     //  rs.next(); //is important to execute new line
     //  System.out.println(rs.getInt("id")+ " "+ rs.getString("name")+" "+rs.getString("age"));
        while(rs.next()) {
    	int id=rs.getInt(1);
    	
		String name=rs.getString(2);
		int age=rs.getInt(3);
		
		System.out.println("id- "+id+" Name is-"+name+" age is-"+age);
	}
	  rs.first();

	  System.out.println(rs.getInt(1)+ " "+ rs.getString(2)+" "+rs.getString(3));
	  
	  rs.absolute(3);
	  System.out.println(rs.getInt(1)+ " "+ rs.getString(2)+" "+rs.getString(3));
}
}
