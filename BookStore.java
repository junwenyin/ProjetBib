import java.util.*;

public class BookStore {
	private static BookStore bookStore = new BookStore();
	private HashMap<String,Book> books;
	private BookStore(){
		initStore();
	}
	public static BookStore getInstance(){
		return bookStore;
	}
	private synchronized void initStore(){
		books= XMLHandler.loadXML("book.xml");
	}
	public synchronized void saveStore(){
		XMLHandler.saveXML(books);
	}
	public ArrayList<Book> searchByID(String id){
		ArrayList<Book> searchRes = new ArrayList<Book>();
		if(books.containsKey(id)){
			searchRes.add(books.get(id));
		}
		System.out.println("search by book id : result :" + searchRes.size());
		return searchRes;
	}
	public ArrayList<Book> searchByName(String name){
		ArrayList<Book> searchRes = new ArrayList<Book>();
		Iterator it = books.keySet().iterator();  
        while(it.hasNext()) {  
            String key = (String)it.next(); 
            Book book = books.get(key);
            if(book.name.contains(name)){
            	searchRes.add(book);
            }
        }  
        System.out.println("search by book name : result :" + searchRes.size());
		return searchRes;
	}
	public synchronized ArrayList<Book> searchByAuteur(String name){
		ArrayList<Book> searchRes = new ArrayList<Book>();
		Iterator it = books.keySet().iterator();  
        while(it.hasNext()) {  
            String key = (String)it.next(); 
            Book book = books.get(key);
            if(book.auteur.contains(name)){
            	searchRes.add(book);
            }
        }
        System.out.println("search by auteur : result :" + searchRes.size());
		return searchRes;
	}
	public boolean empunterBookByID(String id){
		boolean res = false;
		if(books.containsKey(id)){
			if(books.get(id).status.equals("Libre")){
				books.get(id).status="Emprunte";
				System.out.println("emprunter success bookid : "+ id);
				res = true;
				}else{
					System.out.println("emprunter false bookid : "+ id);
				}
					
		}
		saveStore();
		return res;
	}
	
	public static void main(String[] args) {  
		ArrayList<Book> searchRes= BookStore.getInstance().searchByID("10001");
		BookStore.getInstance().empunterBookByID("10001");
    } 


}
