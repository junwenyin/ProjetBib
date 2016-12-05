/*
 * ParaType.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour defination de la type de parametere 
 */

public enum ParaType {
	AUTEUR("auteur"),ID("id"),NAME("name"),SPECIAL("special"),SERVER("servername"),ERREUR("erreur");
	
	private String scode;
	private ParaType(String _scode){
		scode = _scode;
	}
	
	@Override
	public String toString(){
		return scode;
	}
}
