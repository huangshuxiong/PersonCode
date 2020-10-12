package cn.hsx.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Mysocket {
	public static void main(String[] args) throws IOException, DocumentException {
		ServerSocket server = new ServerSocket(getPort());
		System.out.println(server.getInetAddress().getHostName());
		while (true) {
			Socket client = server.accept();

			new Thread(new HandleRequest(client)).start();
		}
	}

	public static Integer getPort() throws DocumentException {
		SAXReader sax = new SAXReader();
		Document read = sax.read(Mysocket.class.getClassLoader().getResourceAsStream("server.xml"));
		Element portNode = (Element) read.selectSingleNode("//connection[@port]");
		String port = portNode.attributeValue("port");
		return Integer.parseInt(port);
	}
}
