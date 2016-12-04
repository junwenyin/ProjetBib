/*
 * ProtocolParser.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour parser les commandes de clients et serveurs 
 */

import java.util.ArrayList;

public class ProtocolParser {
	public static Commande parser(String strCommande) {
		CommandType commandType = CommandType.ERREUR;
		ParaType paraType = ParaType.ERREUR;
		String valeur = "";
		if (strCommande.startsWith("TYPE=0000")) {
			commandType = CommandType.LOGER;
		} else if (strCommande.startsWith("TYPE=0001")) {
			commandType = CommandType.LISTE;
		} else if (strCommande.startsWith("TYPE=8001")) {
			commandType = CommandType.SYNCH;
			if (strCommande.contains("SERVER=")) {
				paraType = ParaType.SERVER;
				int i = strCommande.indexOf("SERVER=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+7, j);
			}
			else {
				paraType = ParaType.ERREUR;
				valeur = "";
			}
		} else if (strCommande.startsWith("TYPE=0002")) {
			commandType = CommandType.CHERCHER;
			if (strCommande.contains("ID=")) {
				paraType = ParaType.ID;
				int i = strCommande.indexOf("ID=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+3, j);
			}else if (strCommande.contains("NAME=")) {
				paraType = ParaType.NAME;
				int i = strCommande.indexOf("NAME=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+5, j);
			}else if (strCommande.contains("AUTEUR=")) {
				paraType = ParaType.AUTEUR;
				int i = strCommande.indexOf("AUTEUR=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+7, j);
			}else if (strCommande.contains("SP=")) {
				paraType = ParaType.SPECIAL;
				int i = strCommande.indexOf("SP=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+3, j);
			}
			else {
				paraType = ParaType.ERREUR;
				valeur = "";
			}
		} else if (strCommande.startsWith("TYPE=0003")) {
			commandType = CommandType.DOWNLOAD;
			if (strCommande.contains("ID=")) {
				paraType = ParaType.ID;
				int i = strCommande.indexOf("ID=");
				int j = strCommande.indexOf(";", i);
				valeur = strCommande.substring(i+3, j);
			}else {
				paraType = ParaType.ERREUR;
				valeur = "";
			}
		} else {
			commandType = CommandType.ERREUR;
		}
		Commande command = new Commande(commandType, paraType, valeur);
		System.out.println("----------------------");
		System.out.println("CommandType: "+ commandType.toString() +"  " +paraType.toString() +" value = " + valeur);
		return command;
	}
	
	///*DO TEST
	public static void main(String[] args) {  
		parser("TYPE=0000;");
		parser("TYPE=0001;");
		parser("TYPE=0002;ID=10001;");
		parser("TYPE=0002;NAME=xxxx;");
		parser("TYPE=0002;AUTEUR=tttttt;");
		parser("TYPE=0002;ttt=10001;");
		parser("TYPE=0003;ID=10001;");
		parser("TYPE=0003;nnn=10001;");
		parser("TYPE=4241;");
		parser("TYPE=8001;TOKEN=GZ;SERVER=GZ;");
	} 
	//*/

}


