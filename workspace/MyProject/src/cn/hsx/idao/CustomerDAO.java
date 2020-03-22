package cn.hsx.idao;

import java.sql.SQLException;
import java.util.List;

import cn.hsx.bean.Customer;

public interface CustomerDAO {

	public void update(String sql, Object... args) throws SQLException;

	public void insert(String sql, Object... args) throws SQLException;

	public List<Customer> getForList(Class<Customer> cls, String sql, Object... args) throws Exception;

	public Object getForValue(String sql, Object... args) throws SQLException;

	public int getCount(String sql, Object... args) throws SQLException;

	public Customer select(Class<Customer> cls, String sql, Object... args) throws Exception;
}
