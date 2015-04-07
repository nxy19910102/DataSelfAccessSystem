package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static final String dbClassName="oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl="jdbc:oracle:thin:@127.0.0.1:1521:nxy";
	private static final String dbUsername="nxy";
	private static final String dbPassword="Pxy0520zyh0505";
	/*private static final String dbClassName="com.mysql.jdbc.Driver";
	private static final String dbUrl="jdbc:mysql://127.0.0.1:3306/nxy_db?useUnicode=true&characterEncoding=UTF-8";
	private static final String dbUsername="nxy";
	private static final String dbPassword="Pxy0520zyh0505";*/
	private static Connection conn=null;
	static
	{
		try
		{
			Class.forName(dbClassName);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException
	{
		if (conn==null)
		{
			conn=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		}
		return conn;
	}

}
