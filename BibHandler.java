/**
 * BibHandler.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * interface entre junwen et lu
 */

import java.util.ArrayList;

public interface BibHandler {
	/*chercher un livre avec ID, retourner le liste des livres  
	//exp de retour :
	ID=456,Name=xx,Auteur=xx,Dir=XX,SERVER=BJ;
	ID=456,Name=xx,Auteur=xx,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByID(String id);
	
	/*chercher un livre avec nom de livre, retourner le liste des livres  
	//exp de retour :
	ID=xx,Name=maths,Auteur=xx,Dir=XX,SERVER=BJ;
	ID=xx,Name=mymaths,Auteur=xx,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByName(String name);
	
	/*chercher un livre avec nom de auteur, retourner le liste des livres  
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	 * */
	public ArrayList<String> searchByAuteur(String name);
	
	/*retourne le liste des tous les livres
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;
	 * */
	public ArrayList<String> getAllListe();
	
	/*retourne le liste des tous les livres dans ce Serveur
	//exp de retour :
	ID=xx,Name=xx,Auteur=pilu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=BJ;
	ID=xx,Name=xx,Auteur=pilulu,Dir=XX,SERVER=GZ;
	 * */
	public ArrayList<String> getLocalListe();
	
	/*retourne le Dir des la livre avec id
	//exp de retour :
	./BJ/Maths/id.txt
	 * */
	public String getDir(String str_id);
	
	
	/*save le liste d'autre servers
	//exp de retour :
	./BJ/Maths/id.txt
	 * */
	public void saveAutreListe(ArrayList<String> arrayList,String port);
}
