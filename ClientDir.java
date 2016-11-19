//----------------------------------------------------------------------
//----------------------------------------------------------------------
//
//		--------------------------------------
//		 Affichage des directories lointains.
//		--------------------------------------
//
//----------------------------------------------------------------------
//----------------------------------------------------------------------

//	Java --	Langage:
//
import java.io.*;
import java.net.*;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

/**
 * A chaque connexion, ce serveur lance le programme d'affichage de directories.
 * Le fichier resultant est renvoy&eacute; au client.
 */
public class ClientDir {
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
	public static ClientDir theAppli;

	/**
	 * Socket.
	 */
	Socket theConversation;
	BufferedReader theIn;
	PrintStream theOut;

	/**
	 * Point d'entr&eacute;e.
	 */
	public static void main(String[] args) throws Exception {
		theAppli = new ClientDir();
		theAppli.myGo();
	}

	/**
	 * Proc&eacute;dure principale.
	 */
	public void myGo() throws Exception {
		theConversation = new Socket("localhost", 5000);
		theIn = new BufferedReader(new InputStreamReader(theConversation.getInputStream()));
		theOut = new PrintStream(new BufferedOutputStream(theConversation.getOutputStream()));
		String l = "Type=CherHead ClientName=junwen";
		theOut.println(l);
		theOut.println("Type=Chercher ClientName=junwen ParaType=Auteur ParaValeur=jone");
		theOut.println("Type=END");
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
			if (ligne.startsWith("Type=END"))
				break;
			a("Client recoit: " + ligne);
			ligne = theIn.readLine();
		}

		theIn.close();
		theOut.flush();
		theOut.close();
		theConversation.close();
	}

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
