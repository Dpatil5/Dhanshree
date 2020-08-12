import java.sql.*;

import javax.swing.JOptionPane;
public class Connectivity {
	Connectivity()
	{
		
	}
 public  ResultSet DisplayResult(String query,String type)
	{
	 ResultSet rs=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			String q="select solutions from Solution natural join Error where errorType='" + type +"'and errorName like '"+query+"'";
			System.out.println(q);
			PreparedStatement stmt=conn.prepareStatement(q);
			
			 rs=stmt.executeQuery();
			 
			//conn.close();
			
			
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
				
		System.out.println(rs);
		return rs;
		
		
	}
	

}
