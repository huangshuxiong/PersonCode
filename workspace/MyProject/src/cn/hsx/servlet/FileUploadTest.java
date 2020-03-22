package cn.hsx.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadTest
 */
@WebServlet("*.action")
@MultipartConfig
public class FileUploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadTest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getServletPath().replaceAll("/", "");
		name = name.substring(0, name.indexOf("."));
		try {
			Method method = this.getClass().getDeclaredMethod(name, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public String getFileName(String path) {
		String newStr = path.replaceAll("\"", "");
		return newStr.substring(newStr.lastIndexOf("\\") + 1);
	}

	protected void doFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Part> parts = request.getParts();
		if (parts.size() > 1) {

			for (Part part : parts) {
				String headerInfo = part.getHeader("Content-Disposition");
				String fileName = getFileName(headerInfo);
				part.write("E:/tmp/" + fileName);
				part.delete();
			}
		} else {
			Part part = request.getPart("file");
			String headerInfo = part.getHeader("Content-Disposition");
			String fileName = getFileName(headerInfo);
			part.write("E:/tmp/" + fileName);
			part.delete();
		}

	}

	protected void doShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "E:/";
		File file = new File(path);
		List<File> fileList = new ArrayList<>();
		for (File f : file.listFiles()) {
			fileList.add(f);
		}
		request.setAttribute("fileList", fileList);
		request.getRequestDispatcher("fileupload.jsp").forward(request, response);
	}

	protected void doDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("path");
		File file = new File(path);
		if (file.exists()) {
			response.setContentType("text/xml");
			response.setHeader("Content-Disposition", "attachment;" + file.getName());
			FileInputStream fs = new FileInputStream(file);
			ServletOutputStream sos = response.getOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = fs.read(buff)) != -1) {
				sos.write(buff, 0, len);
			}
			sos.close();
			fs.close();
		}
	}
}
