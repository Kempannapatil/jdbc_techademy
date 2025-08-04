package jdbc_start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/student";
		String username="root";
		String pass="Darsh@#123";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				System.out.println("mysql driver not loaded");
				e.printStackTrace();
				return;
			}
		try {
			Connection con=DriverManager.getConnection(url, username, pass);
			Statement st=con.createStatement();
			String user="CREATE TABLE IF NOT EXISTS class(id INT, name VARCHAR(100))";
			st.executeUpdate(user);
			System.out.println("table class created ");
			String value="insert into class(id,name) values(?,?)";
			
			try(PreparedStatement ps=con.prepareStatement(value)) {
				ps.setInt(1, 1);
				ps.setString(2, "Class A");
				 // ps.executeUpdate();
				ps.setInt(1, 2);
				ps.setString(2, "Class B");
				//  ps.executeUpdate();
				ps.addBatch();
				int[] arr=ps.executeBatch();
//				All the rows inserted into the database at a single batch
				System.out.printf("inserted rows %d",(int)java.util.stream.IntStream.of(arr).filter(c-> c>0).count());

			}
			String query="select * from class";
			try(ResultSet rs=st.executeQuery(query)){
				while (rs.next()) {
				  int id=rs.getInt(1);
					String name=rs.getString(2);
					
					System.out.println(" id  "+id+" class is :"+name);

		}
		

	}

}
catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Data operation failed");
			e.printStackTrace();
		}

	}
}
