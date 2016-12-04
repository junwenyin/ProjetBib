/*
 * ServerController.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * entrée de le projet
 * Thread Pools
 * run AdminHandler
 * run SocketHandler 
 */


import java.io.*;
import java.util.concurrent.*;

//	Java --- transmissions
//	
import java.net.*;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

public class ServerController {
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
	static public ServerController theAppli;

	/**
	 * Point d'entr&eacute;e.
	 */
	static public void main(String args[]) throws Exception {
		a("---------------------");
		a(" Serveur TCP: ");
		a("---------------------");
		a("\n@junwen lu qian version 1.0 décembre 2016 ");

		/*
		for (int w = 0; w < args.length; w++) {
			a("\t" + args[w]);
			if (args[w].startsWith("port=")) {
				port = Integer.parseInt(args[w].substring(5));
			}
			if (args[w].startsWith("name=")) {
				name = args[w].substring(5);
			}
		}
		*/
		theAppli = new ServerController();
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
	/**
	 * Routine principale.
	 */
	public void myGo() throws Exception {
		//TO LISTDIR
		theConnection = new ServerSocket(ServerInfo.getInstance().getPort());
		a(ServerInfo.getInstance().getName()+":Socket Server running avec port: " + ServerInfo.getInstance().getPort());
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		Runnable adminThread = new AdminHandler();
		fixedThreadPool.execute(adminThread);
		
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
