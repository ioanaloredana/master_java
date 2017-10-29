package ro.tirzuman.ioana.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.tirzuman.ioana.util.Util;

public class AuthLogic {

	private static String DEFAULT_USER = "admin";
	private static String DEFAULT_PASS = "admin";

	private static Map<String, String> credentials = new HashMap<String, String>();

	static {
		List<String> credentialsList = Util.getFileContent("credentials.txt");
		if (credentialsList != null && !credentialsList.isEmpty()) {
			for (String credential : credentialsList) {
				String[] cred = credential.split(",");
				if (cred != null && cred.length == 2) {
					String user = cred[0];
					String pass = cred[1];
					if (user != null && pass != null) {
						user = user.trim();
						pass = pass.trim();
						credentials.put(user, pass);
					}
				}
			}
		}
		if (credentials.isEmpty()) {
			credentials.put(DEFAULT_USER, DEFAULT_PASS);
		}
	}

	public static boolean isValidCredential(String username, String password) {
		String pass = credentials.get(username);
		if (pass != null && pass.equals(password)) {
			return true;
		}
		return false;
	}

}
