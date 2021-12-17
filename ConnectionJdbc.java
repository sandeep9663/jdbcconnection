package jdbc;
import java.sql.*;
import java.util.Objects;

public class ConnectionJdbc {
	 public static void main(String[] args) 
	 {
		 ConnectionJdbc mdb = new ConnectionJdbc();
	        try (Connection con = getConnection()) {
	            Student s1 = new Student(60, "XYZ", "Dubai");
	           
	            mdb.createStudent(s1, con);
	           mdb.selectStudent(s1,con);
	            mdb.updateStudent(s1,con);
	            mdb.deleteStudent(s1,con);
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	    }

	  

		public static Connection getConnection() {
	        System.out.println("Trying to connect with DB!!");
	        Connection con = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "P@til9663");
	            System.out.println("Connection done successfully!!!");
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("Failed to establish connection with DB!!");
	            e.printStackTrace();
	        }
	        return con;
	    }

	    private void createStudent(Student s1, Connection con) throws SQLException {
	        System.out.println("Lets add Student record to the DB...");
	        if (Objects.nonNull(s1) && Objects.nonNull(con)) {
	            PreparedStatement pstmt = con.prepareStatement("INSERT INTO student (Id, Name,Address) VALUES (?,?,?)");
	            Student temp = s1;
	            pstmt.setInt(1, (temp.getId()));
	            pstmt.setString(2, s1.getName());
	            pstmt.setString(3, s1.getAddress());
	            //pstmt.setString(4, s1.getDept());
	            pstmt.executeUpdate();
	            System.out.println("Student Record addedd to DB!!!");
	        }  
	    }
	            
	    private  boolean selectStudent(Student s, Connection con) throws SQLException {
	    		
	        	
	    	 String s2 = "SELECT * FROM student";
	    	 
	    	 Statement statement = con.createStatement();
	    	 ResultSet result = statement.executeQuery(s2);
	    	  
	    	 int count = 0;
	    	  
	    	 while (result.next()){
	    	     int Id = result.getInt(1);
	    	     String Name = result.getString(2);
	    	     String Address = result.getString(3);
	    	    
	    	  
	    	    String output = "student: %s - %s - %s";
	    	     System.out.println(String.format(output, ++count, Id, Name,Address));
	        	
	        	
	    		
	    	}
			return true;
	            }
	    
	    private  void updateStudent(Student s, Connection con) throws SQLException {
	    
	    String sql = "UPDATE student SET name='SHAM' WHERE name='RAM'";
	    
	   PreparedStatement statement = con.prepareStatement(sql);
	   // Statement st=con.createStatement();
	   statement.executeUpdate();
	   
	    	 System.out.println("An existing user was updated successfully!");
	    }
	    
	    private boolean deleteStudent(Student s, Connection con) throws SQLException
	    {
	    	String sql = "DELETE FROM student WHERE name=?";
	    	 
	    	PreparedStatement statement = con.prepareStatement(sql);
	    	statement.setString(1, "XYZ");
	    	 
	    	int rowsDeleted = statement.executeUpdate();
	    	if (rowsDeleted > 0) {
	    	    System.out.println("A user was deleted successfully!");
	    	}
	    	return true;
	    }
	    }


