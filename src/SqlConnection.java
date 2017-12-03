import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class SqlConnection {

	//Connection conn=null;
	
	public static Connection dbConnector()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Domi","wbd2016");
			
		//JOptionPane.showMessageDialog(null, "Connection succesful");
			return conn;
		}catch(Exception e ){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
