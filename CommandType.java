/*
 * CommandType.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour defination de la type de la Commande 
 */

public enum CommandType {
	CHERCHER("chercher"),DOWNLOAD("download"),ERREUR("erreur"),SYNCH("synch"),LISTE("liste"),LOGER("loger"),INSCRIRE("inscrire");
	private String scode;
	private CommandType(String _scode){
		scode = _scode;
	}
	
	@Override
	public String toString(){
		return scode;
	}
	
}
