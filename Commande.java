/*
 * Commande.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour defination de la Commande 
 */

public class Commande {
	public CommandType comType;
	public ParaType paraType;
	public String paraValeur;
	public Commande(CommandType _comType,ParaType _paraType, String _paraValeur){
		comType = _comType;
		paraType = _paraType;
		paraValeur = _paraValeur;
	}
}
