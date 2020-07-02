package mainClass;

import db.DBConnection;
import view.*;

public class Main {
	public static void main(String[] args) {
		DBConnection.initConnection();
		
		new loginView();
//		new FilterByPeriodView();
//		new signUpView();
//		new FilterByTextView();
	}
}
