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

//	Java --- transmissions
//	
import java.net.*;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

public class SocketHandler implements Runnable {
	// debut de classe
	// ----------------------------------------------------------------------

	/**
	 * Commodit&eacute; d'&eacute;criture.
	 */
	static public void log(String s) {
		System.out.println("Thread name:" + Thread.currentThread().getName()+"---"+s);
	}

	public SocketHandler(Socket theCon) {
		theConversation = theCon;
	}

	/**
	 * Fichiers de la conversation.
	 */
	public Socket theConversation;
	public BufferedReader theIn;
	public PrintStream theOut;

	/**
	 * Routine principale.
	 */
	public void run() {
		try {
			theIn = new BufferedReader(new InputStreamReader(theConversation.getInputStream()));
			theOut = new PrintStream(new BufferedOutputStream(theConversation.getOutputStream()));
			doConversation();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				theOut.flush();
				theOut.close();
				theIn.close();
				theConversation.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Le serveur fait la conversation avec un client. (sans erreur)
	 */
	public void doConversation() {
		try {
			doConv();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Le serveur fait la conversation avec un client. (avec erreurs
	 * eventuelles)
	 */
	public void doConv() throws Exception {
		log("...doConv()...");

		log("...premiere ligne de la question...");
		String ligne = theIn.readLine();
		log(ligne);

		// indesirable + systematique:
		if (ligne.startsWith("Type=CherHead")){
			log("do chercher....");
		}
		else if(ligne.startsWith("Type=EmpHead")){
			log("do Emprunter....");
		}else{
			log("do rien....");
			return;
		}
		log("...suite de la request...");
		
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
			if(ligne.startsWith("Type=Emprunter")){
				log("start do Emprunter");
				//do Emprunter
			}
			if(ligne.startsWith("Type=Chercher")){
				log("start do Chercher");
				//do Chercher
			}
			ligne = theIn.readLine();
			log("serveur recoit: " + ligne);
		}

		log("...reponse..head.");
		theOut.println("Type=ResultHead ServeurName=BJ ResultsNombre=5");
		log("...reponse contenu de la page demandee...");
		//ADD CONTENU
		theOut.println("Type=Result ServeurName=BJ LivreID=55 Name=xx Auteur=ttt Statu=Libre Dir=XXX");
		theOut.println("Type=Result ServeurName=BJ LivreID=51 Name=xx Auteur=sss Statu=Libre Dir=XXX");
		theOut.println("Type=Result ServeurName=BJ LivreID=52 Name=xx Auteur=yyy Statu=Libre Dir=XXX");
		theOut.println("Type=Result ServeurName=BJ LivreID=53 Name=xx Auteur=www Statu=Libre Dir=XXX");
		theOut.println("Type=Result ServeurName=BJ LivreID=54 Name=xx Auteur=zzz Statu=Libre Dir=XXX");
		theOut.println("Type=END");
		theOut.flush();
	}
	

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
