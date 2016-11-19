import java.util.*;

public class Book {
	public String name;
	public String auteur;
	public String id;
	public String status;
	public String dir;
	//public ArrayList<String> keywords; 
	public Book(String sid,String sname,String sauteur, String sstatus,String sdir){
		name=sname;
		id = sid;
		auteur = sauteur;
		status = sstatus;
		dir= sdir;
	}
	public Book(){
	}
	public String toString(){
		return name;
	}
}
