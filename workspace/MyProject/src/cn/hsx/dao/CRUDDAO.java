package cn.hsx.dao;

import java.sql.SQLException;
import java.util.List;

import cn.hsx.util.DBUtil;

public class CRUDDAO<T> {
	private DBUtil dbutil;

	public CRUDDAO() {
		dbutil = new DBUtil();
	}

	public List<T> getAll(Class<T> cls, String sql, Object... args) throws Exception {
		List<T> datas = dbutil.selectAll(cls, sql, args);
		return datas;
	}

	public Object getForValue(String sql, Object... args) throws SQLException {
		return dbutil.getForValue(sql, args);
	}

	public void insert(String sql, Object... args) throws SQLException {
		this.update(sql, args);
	}

	public void update(String sql, Object... args) throws SQLException {
		dbutil.update(sql, args);
	}

	public int getCount(String sql, Object... args) throws SQLException {

		return dbutil.getCount(sql, args);
	}

	public  T select(Class<T> cls, String sql, Object... args) throws Exception {
		return dbutil.select(cls, sql, args);
	}
}
