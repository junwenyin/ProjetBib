import java.util.*;

public class Book {
	public String name;
	public String auteur;
	public String id;
	public String dir;
	public String server;
	//public ArrayList<String> keywords; 
	public Book(String sid,String sname,String sauteur, String sdir,String sserver ){
		name=sname;
		id = sid;
		auteur = sauteur;
		server = sserver;
		dir= sdir;
	}
	public Book(){
	}
	public String toString(){
		return name;
	}
}
