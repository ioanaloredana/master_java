package ro.tirzuman.ioana.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class DataStore {

	//static private Map<String, String> memoryMap = new TreeMap<String, String>();
	static private ArrayList<Element> memoryList = new ArrayList<Element>();
	
	public DataStore() {
		Properties propLoad = new Properties();
		File temp = new File("dataStore.properties");
		if (temp.exists()) {
			try {
				propLoad.load(new FileReader(temp));
				for (String name : propLoad.stringPropertyNames()) {
					//memoryMap.put(name, propLoad.getProperty(name));
					memoryList.add(new Element(name, propLoad.getProperty(name)));
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

	public void put(String key, String value) {
		//memoryMap.put(key, value);
		memoryList.add(new Element(key,value));
		writeToFile();
	}

	public void writeToFile() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("dataStore.properties");

			// set the properties value
			//prop.putAll(memoryMap);
						
			for (Element e : memoryList) {
				prop.put(e.getKey(), e.getValue());
			}
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

	public ArrayList<Element> getData() {
		//return new TreeMap<>(memoryMap);
		return new ArrayList<Element>(memoryList);
	}

}
