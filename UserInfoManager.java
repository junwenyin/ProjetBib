/*
 * UserInfoManager.java
 * @author Junwen
 * 01/12/2016
 * version 1.0
 * pour verifier les utilisateurs ou s'inscrire les nouvaux utilisateurs
 */

import java.util.ArrayList;

public class UserInfoManager {
	public static UserInfoManager userInfo = new UserInfoManager();
	public UserInfoManager(){
		loadUserListe();
	}
	private ArrayList<String> listToken;
	private void loadUserListe(){
		listToken = new ArrayList<String>();
	}
	public boolean verifierUserByPW(String _name, String _Pass){
		return true;
	}
	
	public boolean verifierUserByToken(String _token){
		return true;
	}

	public boolean inscrireUser(String _namePass){
		return true;
	}
	
	public String createToken(String _name){
		return _name;
	}
}
