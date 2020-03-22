package cn.hsx.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("SJIS");//从request当中获取流信息
		InputStream fileSource = request.getInputStream();
		String tempFileName = "E:/tempFile";//tempFile指向临时文件
		File tempFile = new File(tempFileName);//outputStram文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[1024];
		int n;
		while ((n = fileSource.read(b)) != -1) {
			outputStream.write(b, 0, n);
		} //关闭输出流、输入流
		outputStream.close();
		fileSource.close();//获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");//RandomAccessFile是用来访问那些保存数据记录的文件的，第二个参数“r”表示只读。
		randomFile.readLine();//读取第一行数据
		String str = randomFile.readLine();//读取第二行数据，为什么读取了两次，看临时文件的保存的内容便知，第一行，和最后一行都是无用的内容数据，所以直接从第二行读取
		String str1 = str.substring(0, str.lastIndexOf("\""));//根据最后一个引号来定位
		int endIndex = str.lastIndexOf("\\");
		//int beginIndex = str1.lastIndexOf("\"") + 1;//beginIndex，endIndex，得到文件name 的初始位置。
		String filename = str.substring( endIndex+1).replaceAll("\"", "");
		filename = new String(filename.getBytes("ISO-8859-1"), "utf-8");
		System.out.println("filename:" + filename);
		randomFile.seek(0);// seek方法可以在文件中移动。
		long startPosition = 0;
		int i = 1;//获取文件内容 开始位置
		while ((n = randomFile.readByte()) != -1 && i <= 4) {
			if (n == '\n') {
				startPosition = randomFile.getFilePointer();//.getFilePointer()用于定位
				i++;
			}
		}
		startPosition = randomFile.getFilePointer() - 1;
		//获取文件内容 结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while (endPosition >= 0 && j <= 2) {
			endPosition--;
			randomFile.seek(endPosition);
			if (randomFile.readByte() == '\n') {
				j++;
			}
		}
		endPosition = endPosition - 1;//设置保存上传文件的路径
		String realPath = "E:/tmp";
		//为文件保存的绝对路径
		File fileupload = new File(realPath);
		if (!fileupload.exists()) {
			fileupload.mkdir();
		}
		File saveFile = new File(realPath, filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
		//从临时文件当中读取文件内容（根据起止位置获取）
		randomFile.seek(startPosition);
		while (startPosition < endPosition) {
			randomFile.read(b);
			randomAccessFile.write(b);
			startPosition = randomFile.getFilePointer();
		} //关闭输入输出流、删除临时文件
		randomAccessFile.close();
		randomFile.close();/* tempFile.delete();*/ //我觉得不必关闭，
		request.setAttribute("result", "上传成功！");
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/01.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
