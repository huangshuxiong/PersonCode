package cn.hsx.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest implements Runnable {
	private Socket client;

	public HandleRequest(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter out = null;
		BufferedReader ff = null;
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String requestInfo = br.readLine();
			String uri = requestInfo.split(" ")[1].substring(1);
			System.out.println(uri);
			StringBuilder sb = new StringBuilder();
			sb.append("HTTP/1.1 200 OK \n");
			sb.append("Content-Type:text/html;charset=utf-8\n\n");
			if (uri.equals("") || uri.endsWith("index.html") || uri.endsWith("index.htm")) {
				InputStream resourceAsStream = Mysocket.class.getClassLoader().getResourceAsStream("index.html");
				ff = new BufferedReader(new InputStreamReader(resourceAsStream));
				String info = "";
				while ((info = ff.readLine()) != null) {
					sb.append(info);
				}
			}else{
				throw new IOException("www");
			}

			out = new PrintWriter(client.getOutputStream());
			out.println(sb);
			out.flush();
		} catch (IOException e) {
			System.out.println("error..");
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
				if (ff != null) {
					ff.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
