package cn.hsx.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static String user;
	private static String password;
	private static String url;
	private static String driverClass;
	private static Connection con;

	static {

		try {
			InputStream is = DBUtil.class.getResourceAsStream("info.properties");
			Properties p = new Properties();
			p.load(is);
			user = p.getProperty("user");
			password = p.getProperty("password");
			url = p.getProperty("url");
			driverClass = p.getProperty("driverClass");
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, user, password);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
	}

	public void release(ResultSet rs, Statement st, Connection con) throws Exception {
		try {
			if (con != null) {
				con.close();
			}
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
