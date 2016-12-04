import java.io.*;
import java.util.ArrayList;

public class ServerInfo {
	private static ServerInfo server = new ServerInfo();
	private int port;
	//private String token;
	private String name;
	private ArrayList<Integer> serverList;

	private ServerInfo() {
		initServerInfo("config.txt");
	}
	
	public static ServerInfo getInstance() {
		return server;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getName() {
		return name;
	}
	
	public String getToken() {
		return name;
	}
	
	public ArrayList<Integer> getServerList() {
		return serverList;
	}

	private void initServerInfo(String path) {
		try {
			serverList = new ArrayList<Integer>();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String data =  br.readLine();
			if (data.startsWith("port=")) {
				int index = data.indexOf(";");
				port = Integer.parseInt(data.substring(5,index));
			}
			if (data.contains("name=")) {
				int indexname = data.indexOf("name=");
				int index = data.indexOf(";",indexname);
				name = data.substring(indexname+5,index);
			}
			/*
			if (data.contains("token=")) {
				int indexname = data.indexOf("token=");
				int index = data.indexOf(";",indexname);
				token = data.substring(indexname+6,index);
			}*/
			while ((data = br.readLine()) != null) {
				Integer in= Integer.parseInt(data);
				serverList.add(in);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//test
	public static void main(String[] args) { 
		System.out.println(ServerInfo.server.name);
		System.out.println(ServerInfo.server.port);
		for(Integer in:ServerInfo.server.serverList){
			System.out.println(in);
		}
			
	} 

}
