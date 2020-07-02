package mainClass;

import db.DBConnection;
import view.AddView;
import view.loginView;
import view.signUpView;

public class Main {
	public static void main(String[] args) {
		DBConnection.initConnection();
		
//		new loginView();
		new AddView();
	}
}
