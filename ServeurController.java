//----------------------------------------------------------------------
//----------------------------------------------------------------------
//
//		-------------------------------------
//		 Tentative de serveur de pages html.
//		-------------------------------------
//
//----------------------------------------------------------------------
//----------------------------------------------------------------------

//	Java --- langage
//
import java.io.*;
import java.util.concurrent.*;

//	Java --- transmissions
//	
import java.net.*;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

public class ServeurController {
	// debut de classe
	// ----------------------------------------------------------------------

	/**
	 * Commodit&eacute; d'&eacute;criture.
	 */
	static public void a(String s) {
		System.out.println(s);
	}

	/**
	 * Singleton applicatif.
	 */
	static public ServeurController theAppli;

	/**
	 * Point d'entr&eacute;e.
	 */
	static public void main(String args[]) throws Exception {
		a("---------------------");
		a(" Serveur TCP + HTTP: ");
		a("---------------------");
		a("\nB.M.G. version automne 2016 ");

		for (int w = 0; w < args.length; w++) {
			a("\t" + args[w]);
			if (args[w].startsWith("port=")) {
				ServerInfo.port = Integer.parseInt(args[w].substring(5));
			}
			if (args[w].startsWith("name=")) {
				ServerInfo.name = args[w].substring(5);
			}
		}

		a("port utilise: " + ServerInfo.port + "!");

		theAppli = new ServeurController();
		theAppli.myGo();
	}

	/**
	 * Port / Service.
	 */
	//public static int port = 5000;

	/**
	 * ServerSocket.
	 */
	public static ServerSocket theConnection;

	/**
	 * Fichiers de la conversation.
	 */
	public Socket theConversation;
	public BufferedReader theIn;
	public PrintStream theOut;
	public String pageName;
	public BufferedReader page;

	/**
	 * Routine principale.
	 */
	public void myGo() throws Exception {
		//TO LISTDIR
		theConnection = new ServerSocket(ServerInfo.port);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		a(ServerInfo.name+":Socket de connexion en place...");
		while (true) {
			a("\n\n");
			theConversation = theConnection.accept();
			a("nouvel appel recu...");
			a("nouvel THread start...");
			
			Runnable newThread = new SocketHandler(theConversation);
			fixedThreadPool.execute(newThread);
		}
	}

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
