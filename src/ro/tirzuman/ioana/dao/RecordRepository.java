package ro.tirzuman.ioana.dao;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import ro.tirzuman.ioana.model.Record;
import ro.tirzuman.ioana.util.Util;

public class RecordRepository {

	private static RecordRepository instance;
	private static String FILE = "records.txt";
	private static List<Record> memoryList = new ArrayList<Record>();

	private RecordRepository() {
		loadDataFromDisk();
	}

	public static RecordRepository getInstance() {
		if (instance == null) {
			instance = new RecordRepository();
		}
		return instance;
	}

	private void loadDataFromDisk() {
		List<String> fileLines = Util.getFileContent(FILE);
		for (String line : fileLines) {
			String[] data = line.split(",");
			if (data == null || data.length != 3) {
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

	public Record getRecord(String key) {
		return getRecord(key, null);
	}

	public Record getRecord(String key, String category) {
		for (Record record : memoryList) {
			if (record.getKey().equals(key)) {
				if (category != null) {
					if (record.getCategory().getName().equals(category)) {
						return record;
					}
				} else {
					return record;
				}
			}
		}
		return null;
	}
}
