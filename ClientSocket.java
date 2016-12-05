/*
 * Commande.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour faire conversation  avec server
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

/**
* A chaque connexion, ce serveur lance le programme d'affichage de directories.
* Le fichier resultant est renvoy&eacute; au client.
*/
public class ClientSocket {
	// debut de classe
	// ----------------------------------------------------------------------

	/**
	 * Raccourci d'&eacute;criture.
	 */
	public static void a(String s) {
		System.out.println(s);
	}

	/**
	 * Singleton applicatif.
	 */
	private static ClientSocket theAppli = new ClientSocket();
	public static ClientSocket getInstance(){
		return theAppli;
	} 
	/**
	 * Socket.
	 */
	Socket theConversation;
	BufferedReader theIn;
	PrintStream theOut;

	/**
	 * Point d'entr&eacute;e.
	 */
	

	/**
	 * Proc&eacute;dure principale.
	 */
	public ArrayList<String> myGo(String str_Commande,int port) throws Exception {
		ArrayList<String> res= new  ArrayList<String>();
		theConversation = new Socket("localhost", port);
		theIn = new BufferedReader(new InputStreamReader(theConversation.getInputStream()));
		theOut = new PrintStream(new BufferedOutputStream(theConversation.getOutputStream()));
		theOut.println(str_Commande);
		theOut.println();
		theOut.flush();
		
		String ligne = theIn.readLine();
		while (true) {
			if (ligne.equals("\r\n"))
				break;
			if (ligne.equals("\n"))
				break;
			if (ligne.equals("\r"))
				break;
			if (ligne.equals(""))
				break;
			a("Client recoit: " + ligne);
			res.add(ligne);
			ligne = theIn.readLine();
		}
		theIn.close();
		theOut.flush();
		theOut.close();
		theConversation.close();
		return res;
	}

	// ----------------------------------------------------------------------
	// fin de classe
	
	public static void main(String[] args) throws Exception {
		ArrayList<String> res;
		InputStreamReader ir;
		BufferedReader in;
		/*
		a("test se loger ---------------------------------------");
		res = theAppli.myGo("TYPE=0000;NAME=Junwen;PASS=xxxx;",5000);
		
		a("test get liste ---------------------------------------");
		res = theAppli.myGo("TYPE=0001;TOKEN=Junwen;",5000);
		
		a("test chercher avec id ---------------------------------------");
		res = theAppli.myGo("TYPE=0002;ID=10001;TOKEN=Junwen;",5000);
		
		a("test chercher avec auteur ---------------------------------------");
		res = theAppli.myGo("TYPE=0002;AUTEUR=xxx;TOKEN=Junwen;",5000);
		
		a("test chercher avec name ---------------------------------------");
		res = theAppli.myGo("TYPE=0002;NAME=math;TOKEN=Junwen;",5000);
		
		a("test télécharger une livre avec id ---------------------------------------");
		res = theAppli.myGo("TYPE=0003;ID=10001;TOKEN=Junwen;",5001);
		
		a("test 7 wrong---------------------------------------");
		res = theAppli.myGo("TYPE=4254;ID=10001;TOKEN=Junwen;",5000);
		*/
		while(true){
			System.out.println("1:Chercher une livre avec id");
			System.out.println("2:Chercher une livre avec name");
			System.out.println("3:Chercher une livre avec auteur");
			System.out.println("4:télécharger une livre avec id");
			System.out.println("5:demander la liste");
			
			System.out.println("0:fermer le serveur");
			ir = new InputStreamReader(System.in);
			in = new BufferedReader(ir);
			String str_command;
			try {
				str_command = in.readLine();
				switch (str_command) {
				case "1":
					System.out.println("entrer le id de livre");
					str_command = in.readLine();
					theAppli.myGo("TYPE=0002;ID="+str_command+";TOKEN=Junwen;",5000);
					break;
				case "2":
					System.out.println("entrer le name de livre");
					str_command = in.readLine();
					theAppli.myGo("TYPE=0002;NAME="+str_command+";TOKEN=Junwen;",5000);
					break;
				case "3":
					System.out.println("entrer le auteur de livre");
					str_command = in.readLine();
					theAppli.myGo("TYPE=0002;AUTEUR="+str_command+";TOKEN=Junwen;",5000);
					break;
				case "4":
					System.out.println("entrer le id de livre");
					str_command = in.readLine();
					theAppli.myGo("TYPE=0003;ID="+str_command+";TOKEN=Junwen;",5000);
					break;
				case "5":
					theAppli.myGo("TYPE=0001;TOKEN=Junwen;",5000);
					break;
				case "0":
					System.exit(0);
					break;
				default :
					break;
				}
				System.out.println("----------------------------------\n");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
}

//----------------------------------------------------------------------
//----------------------------------------------------------------------
//----------------------------------------------------------------------
//----------------------------------------------------------------------
