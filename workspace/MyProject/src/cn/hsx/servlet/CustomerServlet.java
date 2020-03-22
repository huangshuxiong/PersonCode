package cn.hsx.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hsx.bean.Customer;
import cn.hsx.constfield.CustomerConst;
import cn.hsx.dao.impl.CustomerDAOImpl;
import cn.hsx.util.CommonUtil;
import cn.hsx.util.GetParameterToObject;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet({ "*.do" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAOImpl cdi = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		cdi = new CustomerDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath().substring(1);
		String methodName = servletPath.substring(0, servletPath.length() - 3);
		response.getWriter().append(methodName);
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//test str
		Customer cus = (Customer) GetParameterToObject.getParameter(request, new Customer());
		//test end

		String id = CommonUtil.paramChange(request.getParameter("id"));
		String name = CommonUtil.paramChange(request.getParameter("name"));
		String address = CommonUtil.paramChange(request.getParameter("address"));
		String phone = CommonUtil.paramChange(request.getParameter("phone"));
		String createymd = CommonUtil.paramChange(request.getParameter("createymd"));

		String sql = CustomerConst.SELECT_ALL_SQL;
		List<Customer> customers = cdi.getAll(Customer.class, sql, id, name, address, phone,
				createymd);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String oldId = request.getParameter("oldId");
		String oldName = request.getParameter("oldName");
		String oldAddress = request.getParameter("oldAddress");
		String oldPhone = request.getParameter("oldPhone");

		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setAddress(address);
		customer.setPhone(phone);

		if (CommonUtil.isNull(id, name, address, phone)) {
			request.setAttribute("error", CommonUtil.getMessage("empty"));
			request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
			return;

		}

		if (cdi.getCount(CustomerConst.COUNT_SQL, id) > 0 && !id.equals(oldId)) {
			request.setAttribute("error", CommonUtil.getMessage("repeat"));
			customer.setId(oldId);
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
			return;
		}

		if (!id.equals(oldId)) {

			cdi.insert(CustomerConst.INSERT_SQL, id, name, address, phone);
			response.sendRedirect("query.do");
			return;
		}

		cdi.update(CustomerConst.UPDATE_SQL, name, address, phone, oldId);
		response.sendRedirect("query.do");
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		cdi.update(CustomerConst.DELETE_SQL, id);
		response.sendRedirect("query.do");
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		Customer customer = new Customer();
		customer.setId(id);
		customer = cdi.select(Customer.class, CustomerConst.SELECT_SQL, customer.getId());
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
	}
}
