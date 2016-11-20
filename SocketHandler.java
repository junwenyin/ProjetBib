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
import java.util.ArrayList;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

public class SocketHandler implements Runnable {
	// debut de classe
	// ----------------------------------------------------------------------

	/**
	 * Commodit&eacute; d'&eacute;criture.
	 */
	static public void log(String s) {
		System.out.println("Thread name:" + Thread.currentThread().getName() + "---" + s);
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
		boolean isOver = false;
		while (!isOver) {
			log("...premiere ligne de la question...");
			String ligne = theIn.readLine();
			log(ligne);

			Command headcommand = ProtocolHandler.normaliserCommande(ligne);
			if (headcommand.comType.equals(CommandType.HEAD)) {
				log("receive un tete de request....");
			} else {
				log("do rien....");
				return;
			}
			log("...suite de la request...");

			Command newCommand;

			while (true) {
				if (ligne.equals("\r\n"))
					break;
				if (ligne.equals("\n"))
					break;
				if (ligne.equals("\r"))
					break;
				if (ligne.equals(""))
					break;
				newCommand = ProtocolHandler.normaliserCommande(ligne);
				if (newCommand.comType.equals(CommandType.SOCKETOVER)) {
					isOver = true;
					break;
				} else if (newCommand.comType.equals(CommandType.END)) {
					break;
				} 
				else if (newCommand.comType.equals(CommandType.CHERCHER)
						|| newCommand.comType.equals(CommandType.Empunter)) {
					log("--------do chercher ou emprunter");
					exeute(newCommand);
					reponse(newCommand);
				}
				ligne = theIn.readLine();
				log("serveur recoit: " + ligne);
			}
		}
	}

	ArrayList<Book> searchRes = new ArrayList<Book>();
	boolean isSuccessEmprunter = false;

	private void exeute(Command command) {
		if (command.comType.equals(CommandType.Empunter)) {
			isSuccessEmprunter = BookStore.getInstance().empunterBookByID(command.paraValeur);
		} else if (command.comType.equals(CommandType.CHERCHER)) {
			if (command.paraType.equals(ParaType.ID)) {
				searchRes = BookStore.getInstance().searchByID(command.paraValeur);
			} else if (command.paraType.equals(ParaType.NAME)) {
				searchRes = BookStore.getInstance().searchByName(command.paraValeur);
			} else if (command.paraType.equals(ParaType.AUTEUR)) {
				searchRes = BookStore.getInstance().searchByAuteur(command.paraValeur);
			}
		}
	}

	private void reponse(Command command) {
		log("...reponse..head.");
		if (command.comType.equals(CommandType.Empunter)) {
			// HEAD
			theOut.println("TYPE=8001;SN=BJ;RN=1");
			// ADD CONTENU
			theOut.println("TYPE=8003;NO=1;SN=BJ;RES=TRUE");
		} else if (command.comType.equals(CommandType.CHERCHER)) {
			// HEAD
			theOut.println("Type=8001;SN=BJ;RN=" + searchRes.size());
			// ADD CONTENU
			if (searchRes.size() > 0) {
				for (int i = 0; i < searchRes.size(); i++) {
					Book book = searchRes.get(i);
					StringBuilder line = new StringBuilder();
					line.append("TYPE=8002;NO=" + i + ";");
					line.append("SN="+ ServerInfo.name+ ";");
					line.append("LIVREID=" + book.id + ";");
					line.append("NAME=" + book.name + ";");
					line.append("AUTEUR=" + book.auteur + ";");
					line.append("STATUS=" + book.status + ";");
					line.append("DIR=" + book.dir + ";");
					theOut.println(line.toString());
				}
			}
		}
		// END
		theOut.println("TYPE=8000;SN=BJ;");
		theOut.println();
		/*
		 * theOut.println("Type=8001;SN=BJ;RN=5"); log(
		 * "...reponse contenu de la page demandee..."); // ADD CONTENU
		 * theOut.println(
		 * "Type=8002;NO=1;SN=BJ;LivreID=55;Name=xx;Auteur=ttt;Status=Libre;Dir=XXX;"
		 * ); theOut.println(
		 * "Type=8002;NO=2;SN=BJ;LivreID=55;Name=xx;Auteur=ttt;Statu=Libre;Dir=XXX;"
		 * ); theOut.println("Type=8003;NO=1;SN=BJ;RES=TRUE");
		 * theOut.println("Type=8000;");
		 */
		theOut.flush();
	}

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
