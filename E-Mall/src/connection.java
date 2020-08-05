import java.sql.*;
public class connection {

 private String dburl = "jdbc:mysql://localhost:3306/emall";
 private String dbuname = "root";
 private String dbpassword = "";
 private String dbdriver = "com.mysql.jdbc.Driver";
 
 public void loadDriver()
 {
	 try {
		Class.forName(dbdriver);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 public Connection getConnection()
 {
	 Connection con=null;
	 try {
		con = DriverManager.getConnection(dburl, dbuname, dbpassword);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return con;
 }
}
