import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bib implements BibHandler {

	private HashMap<String, Book> myBooks;
	private HashMap<String, Collection<Book>> allBooks;
	private static Bib bib = new Bib(ServerInfo.getInstance().getName());

	private String libraryName;

	private Bib(String libraryName) {
		this.libraryName = libraryName;
		initStore();
	}

	public static Bib getInstance() {
		return bib;
	}

	private void initStore() {
		allBooks = new HashMap<String, Collection<Book>>();
		String filename = libraryName + ".json";
		File file = new File(filename);
		ObjectMapper mapper = new ObjectMapper();
		try {
			ArrayList<Book> books = mapper.readValue(file, new TypeReference<ArrayList<Book>>() {
			});
			myBooks = new HashMap<>();
			for (Book book : books) {
				book.setServer(String.valueOf(ServerInfo.getInstance().getPort()));
				myBooks.put(book.getId(), book);
			}
			String str_Port = String.valueOf(ServerInfo.getInstance().getPort());
			allBooks.put(str_Port, myBooks.values());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> searchByID(String id) {
		ArrayList<String> bookList = new ArrayList<String>();
		for (String key : allBooks.keySet()) {
			for (Book book : allBooks.get(key)) {
				if (book.getId().toLowerCase().contains(id.toLowerCase())) {
					bookList.add(book.toString());
				}
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByName(String name) {
		ArrayList<String> bookList = new ArrayList<String>();
		for (String key : allBooks.keySet()) {
			for (Book book : allBooks.get(key)) {
				if (book.getName().toLowerCase().contains(name.toLowerCase())) {
					bookList.add(book.toString());
				}
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByAuteur(String auteur) {
		ArrayList<String> bookList = new ArrayList<String>();
		for (String key : allBooks.keySet()) {
			for (Book book : allBooks.get(key)) {
				if (book.getAuteur().toLowerCase().contains(auteur.toLowerCase())) {
					bookList.add(book.toString());
				}
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> getAllListe() {
		ArrayList<String> bookList = new ArrayList<String>();
		for (String key : allBooks.keySet()) {
			for (Book book : allBooks.get(key)) {
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> getLocalListe() {
		ArrayList<String> bookList = new ArrayList<String>();
		Collection<Book> books = myBooks.values();
		for (Book book : books) {
			bookList.add(book.toString());
		}
		return bookList;
	}

	@Override
	public String getDir(String id) {
		/*
		 * for (String key : allBooks.keySet()) { for (Book book :
		 * allBooks.get(key)) { if
		 * (book.getId().toLowerCase().equals(id.toLowerCase())){ return
		 * book.getDir(); } } }
		 */
		if (myBooks.containsKey(id)) {
			Book book = myBooks.get(id);
			return book.getDir();
		}
		return null;
	}

	@Override
	public void saveAutreListe(ArrayList<String> list, String port) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (String str_book : list) {
			System.out.println(str_book);
			Book book = new Book();
			// String.format("ID=%s;Name=%s;Auteur=%s;Dir=%s;SERVER=%s;", id,
			// name, auteur, id + ".txt",ServerInfo.getInstance().getPort());
			// TODO set name.id.dir.auteur
			int i = str_book.indexOf("ID=");
			int j = str_book.indexOf(";", i);
			String id = str_book.substring(i + 3, j);

			i = str_book.indexOf("Name=");
			j = str_book.indexOf(";", i);
			String name = str_book.substring(i + 5, j);

			i = str_book.indexOf("Auteur=");
			j = str_book.indexOf(";", i);
			String auteur = str_book.substring(i + 7, j);

			i = str_book.indexOf("Dir=");
			j = str_book.indexOf(";", i);
			String dir = str_book.substring(i + 4, j);

			i = str_book.indexOf("SERVER=");
			j = str_book.indexOf(";", i);
			String server = str_book.substring(i + 7, j);

			if (id != null && name != null) {
				System.out.println("add book");
				book.setAuteur(auteur);
				book.setDir(dir);
				book.setId(id);
				book.setName(name);
				book.setServer(server);
				books.add(book);
			}
		}
		allBooks.put(port, books);
	}

}
