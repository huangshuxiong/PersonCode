package cn.hsx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import cn.hsx.bean.Customer;
import cn.hsx.dao.CRUDDAO;
import cn.hsx.idao.CustomerDAO;

public class CustomerDAOImpl extends CRUDDAO<Customer> implements CustomerDAO {

	@Override
	public void update(String sql, Object... args) throws SQLException {
		super.update(sql, args);
	}

	@Override
	public void insert(String sql, Object... args) throws SQLException {
		super.insert(sql, args);
	}

	@Override
	public List<Customer> getForList(Class<Customer> cls, String sql, Object... args) throws Exception {
		return getAll(cls, sql, args);
	}

	@Override
	public Object getForValue(String sql, Object... args) throws SQLException {
		return getForValue(sql, args);
	}

	@Override
	public int getCount(String sql, Object... args) throws SQLException {
		return super.getCount(sql, args);
	}

	@Override
	public Customer select(Class<Customer> cls, String sql, Object... args) throws Exception {
		return super.select(cls, sql, args);
	}

}
