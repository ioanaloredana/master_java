package ro.tirzuman.ioana.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class DataStore {

	static private Map<String, String> memoryMap = new TreeMap<String, String>();

	public DataStore() {
		Properties propLoad = new Properties();
		File temp = new File("dataStore.properties");
		if (temp.exists()) {
			try {
				propLoad.load(new FileReader(temp));
				for (String name : propLoad.stringPropertyNames()) {
					memoryMap.put(name, propLoad.getProperty(name));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void put(String key, String value) {
		memoryMap.put(key, value);
		writeToFile();
	}

	public synchronized void writeToFile() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("dataStore.properties");

			// set the properties value
			prop.putAll(memoryMap);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public synchronized Map<String, String> getData() {
		return new TreeMap<>(memoryMap);
	}

}
