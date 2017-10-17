package ro.tirzuman.ioana.dao;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import ro.tirzuman.ioana.model.Record;
import ro.tirzuman.ioana.util.Util;

public class RecordRepository {

	private static String FILE = "records.txt";
	private static List<Record> memoryList = new ArrayList<Record>();
	
	public RecordRepository() {
		loadDataFromDisk();
	}
	
	private void loadDataFromDisk() {
		List<String> fileLines = Util.getFileContent(FILE);
		for (String line : fileLines) {
			String[] data = line.split(",");
			if(data == null || data.length != 3) {
				Util.log("Record read from file is invalid: " + line);
				continue;
			}
			String category = data[0];
			String key = data[1];
			String name = data[2];
			memoryList.add(new Record(category, key, name));
		}
	}

	public void put(String category, String key, String name) {
		Record record = new Record(category, key, name);
		if (memoryList.contains(record)) {
			throw new InvalidParameterException("Record already exists.");
		}
		memoryList.add(record);
		Util.addLineToFile(FILE, record.getAsLine());
	}

	public List<Record> getData() {
		return new ArrayList<Record>(memoryList);
	}

}
