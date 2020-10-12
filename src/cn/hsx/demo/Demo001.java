package cn.hsx.demo;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

public class Demo001 {
	private static Connection con;
	private static final String DRIVERCLASS = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:shuxiong124";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	private ResultSet rs;
	private PreparedStatement pst;
	static {
		try {
			Class.forName(DRIVERCLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test010() {
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 2 == 0) {
						System.out.println(i);
					}
				}
			}
		};
		r1.run();
		Runnable r2 = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 2 != 0) {
						System.out.println(i);
					}
				}
			}
		};
		r2.run();
	}

	@Test
	public void test009() throws Exception {
		CallableStatement cs = null;
		try {
			String sql = "{?= call FUNCTION001(?,?)}";
			cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.NUMERIC);
			cs.registerOutParameter(3, Types.NUMERIC);
			cs.setDouble(2, 50.2);
			cs.execute();
			System.out.println(cs.getDouble(1));
			System.out.println(cs.getDouble(3));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cs.close();
			con.close();
		}
	}

	@Test
	public void test008() throws Exception {
		String sql = "insert into customer values (?,?,?,?)";
		try {
			long start = System.currentTimeMillis();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			for (int i = 1; i <= 1000000; i++) {
				pst.setString(1, "usr" + i);
				pst.setString(2, "usrn" + i);
				pst.setString(3, "ps" + i);
				pst.setDate(4, new Date(new java.util.Date().getTime()));
				pst.addBatch();

				if (i % 30000 == 0) {

					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();

			con.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		} finally {
			pst.close();
			con.close();
		}
	}

	@Test
	public void test001() throws Exception {
		String sql = "insert into CUSTOMER values(?,?,?,?)";
		Object params[] = { "001", "001", "001", new Date(new java.util.Date().getTime()) };
		update(sql, params);
		QueryRunner q = new QueryRunner();
	}

	@Test
	public void test0002() throws Exception {
		String sql = " select * from customer where user_id = 'jtest' or 1 = 1";
		Statement st = con.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("user_id"));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getDate(4));
		}
		st.close();
		con.close();
	}

	@Test
	public void test004() throws Exception {
		DatabaseMetaData dm = con.getMetaData();
		System.out.println(dm.getDatabaseProductName());
		System.out.println(dm.getDatabaseProductVersion());
		System.out.println(dm.getURL());
		System.out.println(dm.getUserName());
		System.out.println(dm.getDriverName());
	}

	@Test
	public void test0003() throws Exception {
		String sql = "insert into customer values(?,?,?,?)";
		Object[] params = { "admin", "administrator", "adminps", new Date(new java.util.Date().getTime()) };
		update(sql, params);
	}

	@Test
	public void test0004() throws Exception, Exception {
		String sql = " select user_id userId,user_password userPassword,user_name userName from customer where user_id = ?";
		Customer ct = selectUtil(Customer.class, sql, "007");
		System.out.println(ct);
	}

	public void update(String sql, Object... params) throws Exception {
		pst = con.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i + 1, params[i]);
		}
		pst.executeUpdate();
	}

	public <T> T selectUtil(Class<T> cls, String sql, Object... params) throws Exception, IllegalAccessException {

		T entity = null;
		entity = cls.newInstance();
		pst = con.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i + 1, params[i]);
		}
		rs = pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData rsd = rs.getMetaData();
			for (int i = 1; i <= rsd.getColumnCount(); i++) {
				String label = rsd.getColumnLabel(i);
				Object value = rs.getObject(i);
				setProperty(entity, label, value);
			}
		}
		return entity;
	}

	public <T> void setProperty(T t, String columnName, Object value)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getName().toUpperCase().equals(columnName)) {
				field.set(t, value);
			}
		}
	}

}
