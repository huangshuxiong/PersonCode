package cn.hsx.constfield;

public class CustomerConst {
	public static final String SELECT_ALL_SQL = " select id,name,address,phone,cre_ymd from customer where id like ? and name like ? and address like ? and phone like ? and cre_ymd like ? ";
	public static final String DELETE_SQL = " delete from customer where id = ?";
	public static final String INSERT_SQL = " insert into customer values (?,?,?,?,sysdate)";
	public static final String COUNT_SQL = " select count(id) from customer where id = ? ";
	public static final String SELECT_SQL = " select id,name,address,phone from customer where id = ? ";
	public static final String UPDATE_SQL = " update customer set name = ?,address = ?,phone = ? where id = ? ";
}
