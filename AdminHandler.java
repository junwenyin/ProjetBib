import java.io.*;

public class AdminHandler implements Runnable {
	InputStreamReader ir;
	BufferedReader in;

	public void run() {
		while (true) {
			System.out.println("1:sync la liste aux autre serveurs");
			System.out.println("2:fermer le serveur");
			ir = new InputStreamReader(System.in);
			in = new BufferedReader(ir);
			String str_command;
			try {
				str_command = in.readLine();
				switch (str_command) {
				case "1":
					SyncSocket theAppli = new SyncSocket();
					theAppli.myGo();
					break;
				case "2":
					System.exit(0);
					break;
				default :
					break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
