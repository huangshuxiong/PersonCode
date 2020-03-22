package cn.hsx.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {

	private static String user;
	private static String password;
	private static String url;
	private static String driverClass;
	private static Connection con;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	static {
		InputStream is = DBUtil.class.getResourceAsStream("info.properties");
		Properties pp = new Properties();
		try {
			pp.load(is);
			user = pp.getProperty("user");
			password = pp.getProperty("password");
			driverClass = pp.getProperty("driverClass");
			url = pp.getProperty("url");

			Class.forName(driverClass);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// public static void main(String[] args) throws Exception {
	// DBUtil db = new DBUtil();
	// db.update("update customer set name=? where id=?", "admin", 6);
	// String sql = "select * from customer where id > ? ";
	// List<Customer> c = db.selectAll(Customer.class, sql, 5);
	// for (Customer customer : c) {
	// System.out.println(customer);
	// }
	// sql = "select name from customer where id = ?";
	// System.out.println(db.getForValue(sql, 5));
	// }

	/**
	 * 単一値取得
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public Object getForValue(String sql, Object... args) throws SQLException {
		Object result = "";
		con = this.getConnection();
		pst = con.prepareStatement(sql);
		this.fillStatement(pst, args);
		rs = pst.executeQuery();
		if (rs.next()) {
			result = rs.getObject(1);
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * 検索操作
	 * @param cls
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public <T> T select(Class<T> cls, String sql, Object... args) throws Exception {
		con = this.getConnection();
		T entity = null;
		entity = cls.newInstance();
		pst = con.prepareStatement(sql);

		this.fillStatement(pst, args);
		rs = pst.executeQuery();
		if (rs.next()) {
			ResultSetMetaData rsd = rs.getMetaData();
			int count = rsd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String label = rsd.getColumnLabel(i);
				String value = rs.getObject(label).toString();
				BeanUtil.setProperty(entity, label, value);
			}
		}
		this.realease(con, pst, rs);
		return entity;
	}

	/**
	 * 検索による複数値の取得
	 * @param cls
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> selectAll(Class<T> cls, String sql, Object... args) throws Exception {
		con = this.getConnection();
		List<T> entities = new ArrayList<>();
		T entity = null;
		pst = con.prepareStatement(sql);

		this.fillStatement(pst, args);
		rs = pst.executeQuery();
		while (rs.next()) {
			entity = cls.newInstance();
			ResultSetMetaData rsd = rs.getMetaData();
			int count = rsd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String label = rsd.getColumnLabel(i);
				String value = rs.getObject(label).toString();
				BeanUtil.setProperty(entity, label, value);
			}
			entities.add(entity);
		}
		this.realease(con, pst, rs);
		return entities;
	}

	/**
	 * アップデート処理
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	public void update(String sql, Object... args) throws SQLException {
		try {
			con = this.getConnection();
			pst = con.prepareStatement(sql);
			this.fillStatement(pst, args);
			if (pst.executeUpdate() > 0) {
				con.commit();
				this.realease(con, pst, null);
			}
		} catch (Exception e) {
			con.rollback();
			this.realease(con, pst, null);
			e.printStackTrace();
		}
	}

	/**
	 * バインド変数を埋める
	 * @param pst
	 * @param args
	 * @throws SQLException
	 */
	public void fillStatement(PreparedStatement pst, Object... args) throws SQLException {
		int parameterIndex = 1;
		if (args != null) {
			for (Object object : args) {
				pst.setObject(parameterIndex, object);
				parameterIndex++;
			}
		}
	}

	/**
	 * データ数取得
	 * @param sql
	 * @param args
	 * @return
	 */
	public int getCount(String sql, Object... args) {
		int count = 0;
		try {
			con = this.getConnection();
			pst = con.prepareStatement(sql);
			this.fillStatement(pst, args);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * データベース資源解放
	 * @param con
	 * @param st
	 * @param rs
	 */
	public void realease(Connection con, Statement st, ResultSet rs) {
		try {
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
