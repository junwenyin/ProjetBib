/*
 * ProtocolHandler.java
 * @author junwen
 * 01/12/2016
 * version 1.0
 * pour le normaliser les Commandes
 */

import java.util.ArrayList;

public class ProtocolHandler {
	public static Command normaliserCommande(String strCommande) {
		CommandType commandType = CommandType.ERREUR;
		ParaType paraType = ParaType.ERREUR;
		String valeur = "";
		if (strCommande.startsWith("TYPE=0000")) {
			commandType = CommandType.END;
		} else if (strCommande.startsWith("TYPE=0001")) {
			commandType = CommandType.HEAD;
		} else if (strCommande.startsWith("TYPE=XXXX")) {
			commandType = CommandType.SOCKETOVER;
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
				valeur = strCommande.substring(i+7, j);
			}
			else {
				paraType = ParaType.ERREUR;
				valeur = "";
			}
		} else if (strCommande.startsWith("TYPE=0003")) {
			commandType = CommandType.Empunter;
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
		Command command = new Command(commandType, paraType, valeur);
		System.out.println("----------------------");
		System.out.println("---commandType: "+ commandType.toString() +"  " +paraType.toString() +" ---value = " + valeur);
		return command;
	}
	
	/*DO TEST
	public static void main(String[] args) {  
		normaliserCommande("TYPE=0000;");
		normaliserCommande("TYPE=0001;");
		normaliserCommande("TYPE=0002;ID=10001;");
		normaliserCommande("TYPE=0002;NAME=xxxx;");
		normaliserCommande("TYPE=0002;AUTEUR=tttttt;");
		normaliserCommande("TYPE=0002;ttt=10001;");
		normaliserCommande("TYPE=0003;ID=10001;");
		normaliserCommande("TYPE=0003;nnn=10001;");
		normaliserCommande("TYPE=4241;");
	} 
	*/

}


