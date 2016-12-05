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
			ArrayList<Book> books = mapper.readValue(file, new TypeReference<ArrayList<Book>>(){});
			myBooks = new HashMap<>();
			for (Book book : books) {
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
		Book book = myBooks.get(id);
		ArrayList<String> bookList = new ArrayList<String>();
		if(book != null){
			bookList.add(book.toString());
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByName(String name) {
		Collection<Book> books = myBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		String lowercaseKeyword = name.toLowerCase();
		for (Book book : books) {
			String lowercaseBookName = book.getName().toLowerCase();
			if(lowercaseBookName.contains(lowercaseKeyword)) {
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> searchByAuteur(String auteur) {
		Collection<Book> books = myBooks.values();
		ArrayList<String> bookList = new ArrayList<String>();
		String lowerCaseKeyword = auteur.toLowerCase();
		for(Book book : books){
			String lowerCaseAuteur = book.getAuteur().toLowerCase(); 
			if(lowerCaseAuteur.contains(lowerCaseKeyword)){
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> getAllListe() {
		ArrayList<String> bookList = new ArrayList<String>();
		for(String key : allBooks.keySet()){
			for(Book book : allBooks.get(key)){
				bookList.add(book.toString());
			}
		}
		return bookList;
	}

	@Override
	public ArrayList<String> getLocalListe() {
		ArrayList<String> bookList = new ArrayList<String>();
		Collection<Book> books = myBooks.values();
		for (Book book : books){
			bookList.add(book.toString());
		}
		return bookList;
	}

	@Override
	public String getDir(String id) {
		for(String key : allBooks.keySet()){
			for(Book book : allBooks.get(key)){
				if(book.getId()==id)
					return book.getDir();
			}
		}
		return "";
	}

	@Override
	public void saveAutreListe(ArrayList<String> list, String port) {
		ArrayList<Book> books = new ArrayList<Book>();
		for(String str_book : list){
			Book book = new Book();
			//TODO set name.id.dir.auteur
			books.add(book);
		}
		allBooks.put(port, books);
		// TODO Auto-generated method stub
	}
	
}
