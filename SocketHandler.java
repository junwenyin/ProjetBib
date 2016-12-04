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
	public String str_token;
	public BufferedReader theIn;
	public PrintStream theOut;
	BibHandler bib = new Bib();

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

		Commande command = ProtocolParser.parser(ligne);
		// qui est le client
		String str_token = "";
		String str_Name = "";
		String str_Pass = "";
		if (ligne.contains("TOKEN=")) {
			int indexname = ligne.indexOf("TOKEN=");
			int index = ligne.indexOf(";", indexname);
			str_token = ligne.substring(indexname + 6, index);
		} else if (ligne.contains("NAME=") && ligne.contains("PASS=")) {
			int indexname = ligne.indexOf("NAME=");
			int index = ligne.indexOf(";", indexname);
			str_Name = ligne.substring(indexname + 5, index);
			int indexPass = ligne.indexOf("PASS=");
			index = ligne.indexOf(";", indexPass);
			str_Pass = ligne.substring(indexPass + 5, index);
		}

		boolean isValide = false;
		if (command.comType == CommandType.LOGER) {
			isValide = UserInfoManager.userInfo.verifierUserByPW(str_Name, str_Pass);
		} else {
			isValide = UserInfoManager.userInfo.verifierUserByToken(str_token);
		}

		if (isValide) {
			ArrayList<String> searchRes;
			switch (command.comType) {
			case SYNCH:
				ArrayList<String> bookDataList = new ArrayList<String>();
				while (true) {
					ligne = theIn.readLine();
					log("...serveur recoit..." + ligne);
					if (ligne.equals("\r\n"))
						break;
					if (ligne.equals("\n"))
						break;
					if (ligne.equals("\r"))
						break;
					if (ligne.equals(""))
						break;
					bookDataList.add(ligne);
				}
				// TODO
				bib.saveAuterListe(bookDataList);
				theOut.println("TYPE=8002;");
				theOut.println();
				break;
			case LOGER:
				str_token = UserInfoManager.userInfo.createToken(str_Name);
				theOut.println("TYPE=1000;SETTOKEN=" + str_token + ";");
				theOut.println();
				break;
			case CHERCHER:
				searchRes = new ArrayList<String>();
				// TODO
				if (command.paraType.equals(ParaType.ID)) {
					searchRes = bib.searchByID(command.paraValeur);
				} else if (command.paraType.equals(ParaType.NAME)) {
					searchRes = bib.searchByName(command.paraValeur);
				} else if (command.paraType.equals(ParaType.AUTEUR)) {
					searchRes = bib.searchByAuteur(command.paraValeur);
				}
				theOut.println("TYPE=1002;RES=" + searchRes.size());
				for (String bookinfo : searchRes) {
					theOut.println(bookinfo);
				}
				theOut.println();
				break;
			case DOWNLOAD:
				// TODO
				theOut.println("TYPE=1003;");
				String filePath = bib.getDir(command.paraValeur);
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
					String data;
					while ((data = br.readLine()) != null) {
						theOut.println(data);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				theOut.println();
				break;
			case LISTE:
				for (String bookinfo : bib.getAllListe()) {
					theOut.println(bookinfo);
				}
				theOut.println();
				break;
			default:
				theOut.println();
				break;
			}
			theOut.flush();
		}
	}

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
