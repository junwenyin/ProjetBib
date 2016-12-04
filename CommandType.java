
public enum CommandType {
	CHERCHER("chercher"),DOWNLOAD("download"),ERREUR("erreur"),SYNCH("synch"),LISTE("liste"),LOGER("loger"),INSCRIRE("inscrire");
	private String scode;
	private CommandType(String _scode){
		scode = _scode;
	}
	
	@Override
	public String toString(){
		return scode;
	}
	
}
