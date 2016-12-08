import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class Bib implements BibHandler {

	private HashMap<String, Book> myBooks;
	private HashMap<String, HashMap<String, Book>> libraryBooks;
	private HashMap<String, Book> allBooks; // all books for searching all books
											// referent;

	private static Bib bib = new Bib(ServerInfo.getInstance().getName());

	private String libraryName;

	private Bib(String libraryName) {
		this.libraryName = libraryName;
		initStore();
		libraryBooks = new HashMap<String, HashMap<String, Book>>();
		libraryBooks.put(libraryName, myBooks);
		concatBooks();
	}

	public static Bib getInstance() {
		return bib;
	}

	private void concatBooks() {
		allBooks = new HashMap<>();
		for (HashMap<String, Book> books : libraryBooks.values()) {
			allBooks.putAll(books);
		}
	}

	private void initStore() {
		try {
			String filename = libraryName + ".txt";
			FileReader fReader = new FileReader(filename);
			BufferedReader bReader = new BufferedReader(fReader);
			ArrayList<String> books = new ArrayList<>();
			String line = new String();
			while ((line = bReader.readLine()) != null) {
				books.add(line);
			}
			myBooks = convertBooks(books);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> searchByID(String id) {
		Collection<Book> books = allBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		String lowercaseKeyword = id.toLowerCase();
		for (Book book : books) {
			String lowercaseBookId = book.getId().toLowerCase();
			if(lowercaseBookId.contains(lowercaseKeyword)) {
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByName(String name) {
		Collection<Book> books = allBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		String lowercaseKeyword = name.toLowerCase();
		for (Book book : books) {
			String lowercaseBookName = book.getName().toLowerCase();
			if (lowercaseBookName.contains(lowercaseKeyword)) {
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByAuteur(String auteur) {
		Collection<Book> books = allBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		String lowerCaseKeyword = auteur.toLowerCase();
		for (Book book : books) {
			String lowerCaseAuteur = book.getAuteur().toLowerCase();
			if (lowerCaseAuteur.contains(lowerCaseKeyword)) {
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> getAllListe() {
		Collection<Book> books = allBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		for (Book book : books) {
			bookList.add(book.toString());// toString
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
		Book book = allBooks.get(id);
		return book.getDir();
	}

	@Override
	public void saveAutreListe(ArrayList<String> list, String name) {
		if (libraryBooks.containsKey(name)) {
			libraryBooks.remove(name);
		}
		HashMap<String, Book> books = convertBooks(list);
		libraryBooks.put(name, books);
		concatBooks();
	}

	private HashMap<String, Book> convertBooks(ArrayList<String> list) {
		HashMap<String, Book> books = new HashMap<>();
		for (String bookInfo : list) {
			bookInfo = bookInfo.substring(0, bookInfo.length() - 1);
			String[] fields = bookInfo.split(",");
			Book book = new Book();
			for (String info : fields) {
				String[] pair = info.split("=");
				{
					if (pair.length == 2) {
						String key = pair[0];
						String value = pair[1];
						if (key.equals("ID")) {
							book.setId(value);
						} else if (key.equals("Name")) {
							book.setName(value);
						} else if (key.equals("Auteur")) {
							book.setAuteur(value);
						} else if (key.equals("Dir")) {
							book.setDir(value);
						} else if (key.equals("SERVER")) {
							book.setServer(value);
						} else if (key.equals("Special")) {
							book.setSpecial(value);
						}
					}
				}
			}
			books.put(book.getId(), book);
		}
		return books;
	}

}
