/*
 * create by junwen 19 Nov 2016
 * pour load books data de XML fichier
 * pour sauver books data de XML fichier
 */
import java.util.*;
import javax.xml.parsers.*;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import java.io.*;

public class XMLHandler {
	public static HashMap<String, Book> loadXML(String fname) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fname);
			NodeList nl1 = doc.getElementsByTagName("book");
			int size1 = nl1.getLength();
			
			for (int i = 0; i < size1; i++) {
				Node n = nl1.item(i);
				NodeList nl2 = n.getChildNodes();
				int size2 = nl2.getLength();
				String id = "";
				String name = "";
				String auteur = "";
				String status = "";
				String dir = "";
				for (int j = 0; j < size2; j++) {
					Node n2 = nl2.item(j);
					if (n2.hasChildNodes()) {
						switch (n2.getNodeName()) {
						case "id":
							id = n2.getFirstChild().getNodeValue();
							break;
						case "name":
							name = n2.getFirstChild().getNodeValue();
							break;
						case "auteur":
							auteur = n2.getFirstChild().getNodeValue();
							break;
						case "status":
							status = n2.getFirstChild().getNodeValue();
							break;
						case "dir":
							dir = n2.getFirstChild().getNodeValue();
							break;
						default:
							break;
						}
					}
				}
				Book book = new Book(id, name, auteur, status, dir);
				books.put(id, book);
			}
			System.out.println("load books nombre：" + books.size());
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		}
		return books;
	}

	public static void saveXML(HashMap<String, Book> books) {
		// TODO
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("books");
			document.appendChild(root);

			Iterator it = books.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				Book book = books.get(key);

				Element booknode = document.createElement("book");

				Element name = document.createElement("name");
				name.appendChild(document.createTextNode(book.name));
				booknode.appendChild(name);

				Element id = document.createElement("id");
				id.appendChild(document.createTextNode(book.id));
				booknode.appendChild(id);

				Element auteur = document.createElement("auteur");
				auteur.appendChild(document.createTextNode(book.auteur));
				booknode.appendChild(auteur);

				Element dir = document.createElement("dir");
				dir.appendChild(document.createTextNode(book.dir));
				booknode.appendChild(dir);

				Element status = document.createElement("status");
				status.appendChild(document.createTextNode(book.status));
				booknode.appendChild(status);

				root.appendChild(booknode);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream("book.xml"));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("creat XML success! books nombre：" + books.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
