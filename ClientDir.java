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
		int n = 100;
		while (n>0) {
			Thread.sleep(3000);
			theOut.println("TYPE=0001;");
			// theOut.println("TYPE=0002;AUTEUR=XXX;");
			theOut.println("TYPE=0002;NAME=math;");
			// theOut.println("TYPE=0002;ID=10001;");
			// theOut.println("TYPE=0003;ID=10001;");
			theOut.println("TYPE=0000;");
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
				ligne = theIn.readLine();
			}
			n--;

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
