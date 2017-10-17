package ro.tirzuman.ioana.dao;

import java.security.InvalidParameterException;
import java.util.List;

import ro.tirzuman.ioana.model.Category;
import ro.tirzuman.ioana.util.Util;

public class CategoryRepository {	
	private static List<String> categoryList;
	
	public static List<String> getAll() {
		if (categoryList == null) {
			categoryList = Util.getFileContent("categories.txt");
		}
		return categoryList;
	}

	public static Category getCategory(String category) {
		if(categoryList.contains(category)){
			return new Category(category);
		}
		throw new InvalidParameterException("Category not found");
	}
	
}
