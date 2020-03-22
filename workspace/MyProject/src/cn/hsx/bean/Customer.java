package cn.hsx.bean;

public class Customer {

	private String id;
	private String name;
	private String address;
	private String phone;
	private String cre_YMD;

	public Customer() {
	}
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 * @param cre_YMD
	 */
	public Customer(String id, String name, String address, String phone, String cre_YMD) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.cre_YMD = cre_YMD;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            セットする phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return cre_YMD
	 */
	public String getCre_YMD() {
		return cre_YMD;
	}

	/**
	 * @param cre_YMD
	 *            セットする cre_YMD
	 */
	public void setCre_YMD(String cre_YMD) {
		this.cre_YMD = cre_YMD;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", cre_YMD="
				+ cre_YMD + "]";
	}

}
