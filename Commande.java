
public class Commande {
	public CommandType comType;
	public ParaType paraType;
	public String paraValeur;
	public Commande(CommandType _comType,ParaType _paraType, String _paraValeur){
		comType = _comType;
		paraType = _paraType;
		paraValeur = _paraValeur;
	}
}
