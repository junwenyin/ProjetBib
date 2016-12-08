/**
 * @author Lu Pi
 *
 */
public class Book {
	
	private String id;
	
	private String name = "";
	
	private	String auteur = "";
	
	private String special = "";
	
	private	String dir;
	
	private String server;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	@Override
	public String toString() {
		return String.format("ID=%s,Name=%s,Auteur=%s,Dir=%s,Special=%s,SERVER=%s;", id, name, auteur, dir,special,server);
	}
	
}
