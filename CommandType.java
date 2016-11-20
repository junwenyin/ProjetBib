
public enum CommandType {
	HEAD("head"),END("end"),CHERCHER("chercher"),Empunter("emprunter"),ERREUR("erreur"),SOCKETOVER("socketover");
	private String scode;
	private CommandType(String _scode){
		scode = _scode;
	}
	
	@Override
	public String toString(){
		return scode;
	}
	
}
