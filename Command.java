
public class Command {
	public CommandType comType;
	public ParaType paraType;
	public String paraValeur;
	public Command(CommandType _comType,ParaType _paraType, String _paraValeur){
		comType = _comType;
		paraType = _paraType;
		paraValeur = _paraValeur;
	}
}
