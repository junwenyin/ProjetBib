import java.util.ArrayList;

public class Bib implements BibHandler{
	/*chercher un livre avec ID, retourner le liste des livres  
	//exp de retour :
	ID=456,Name=xx,Auteur=xx,Dir=XX,SERVER=BJ;
	ID=456,Name=xx,Auteur=xx,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByID(String id){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;");
		return arrayList;
	}
	
	/*chercher un livre avec nom de livre, retourner le liste des livres  
	//exp de retour :
	ID=xx,Name=maths,Auteur=xx,Dir=XX,SERVER=BJ;
	ID=xx,Name=mymaths,Auteur=xx,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByName(String name){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;");
		return arrayList;
	}
	
	/*chercher un livre avec nom de auteur, retourner le liste des livres  
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByAuteur(String name){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;");
		return arrayList;
	}
	
	/*retourne le liste des tous les livres
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;
	 * */
	public ArrayList<String> getAllListe(){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;");
		return arrayList;
	}
	/*retourne le liste des tous les livres dans ce Serveur
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;
	 * */
	public ArrayList<String> getLocalListe(){
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;");
		arrayList.add("ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;");
		return arrayList;
	}
	/*retourne le Dir des la livre avec id
	//exp de retour :
	./BJ/Maths/id.txt
	 * */
	public String getDir(String str_id){
		String dir = "config.txt";
		return dir;
	}
	
	/*save le liste d'autre servers
	//exp de retour :
	./BJ/Maths/id.txt
	 * */
	public void saveAuterListe(ArrayList<String> arrayList){
		
	}
}
