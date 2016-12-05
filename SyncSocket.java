/*
 * SyncSocket.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour sync la liste de livre entre les serveurs
 */

import java.io.*;
import java.net.*;

public class SyncSocket {
	public void a(String s) {
		System.out.println(s);
	}

	Socket theConversation;
	BufferedReader theIn;
	PrintStream theOut;

	public void myGo() throws Exception {
		for (Integer port : ServerInfo.getInstance().getServerList()) {
			theConversation = new Socket("localhost", port);
			theIn = new BufferedReader(new InputStreamReader(theConversation.getInputStream()));
			theOut = new PrintStream(new BufferedOutputStream(theConversation.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("TYPE=8001;TOKEN=");
			sb.append(ServerInfo.getInstance().getToken());
			sb.append(";SERVER=");
			sb.append(ServerInfo.getInstance().getPort());
			sb.append(";");
			String l =sb.toString();
			theOut.println(l);
			envoyerListe();
			theOut.println("");
			theOut.flush();

			String ligne = theIn.readLine();
			if(ligne.startsWith("TYPE=8002;")){
				a("...SYNC SUCCESS AVEC :" + port);
			}else if(ligne.startsWith("TYPE=8003;")){
				a("...SYNC FAULT AVEC :" + port);
			}else{
				a("...RIEN RECU DE : " + port);
			}
			
			while (true) {
				if (ligne.equals("\r\n"))
					break;
				if (ligne.equals("\n"))
					break;
				if (ligne.equals("\r"))
					break;
				if (ligne.equals(""))
					break;
				a("...RECU de : " +port.toString()+" CONTENU: " + ligne);
				ligne = theIn.readLine();
			}
			

			theIn.close();
			theOut.flush();
			theOut.close();
			theConversation.close();
		}
	}

	private void envoyerListe() throws Exception {
		// TODO liste
		BibHandler bib = Bib.getInstance();
		for(String bookinfo : bib.getLocalListe() ){
			theOut.println(bookinfo);
		}
	}

	// ----------------------------------------------------------------------
	// fin de classe
}
