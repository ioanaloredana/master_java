package ro.tirzuman.ioana.dao;

import java.util.List;

import ro.tirzuman.ioana.util.Util;

public class CategoryRepository {	
	private static List<String> categoryList;
	
	public static List<String> getAll() {
		if (categoryList == null) {
			categoryList = Util.getFileContent("categories.txt");
		}
		return categoryList;
	}
	
}
