package ro.tirzuman.ioana.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Util {
	private static final String FILES_RELATIVE_PATH = "E:\\dev\\eclipse-workspace\\java\\java-l1\\";

	public static List<String> getFileContent(String filename) {
		List<String> lines = new ArrayList<>();
		File file = new File(FILES_RELATIVE_PATH + filename);
		try {
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	public static void addLineToFile(String filename, String line) {

		try {
			File file = new File(FILES_RELATIVE_PATH + filename);
			FileWriter writer = new FileWriter(file, true);
			writer.append(line + "\n");
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void log(String message) {
		System.out.println(message);
	}

	public static boolean getBoolean(String parameter) {
		boolean result = false;
		if (parameter != null && parameter.toLowerCase().matches("true|yes|1|on")) {
			result = true;
		}

		return result;
	}
}
