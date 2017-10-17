package ro.tirzuman.ioana.model;

import java.io.Serializable;

import ro.tirzuman.ioana.dao.CategoryRepository;

public class Record implements Serializable{

	private static final long serialVersionUID = -4314640355951716540L;

	private String key;
	private String name;
	private Category category;
	
	public Record(String category, String key, String name) {
		this.category = CategoryRepository.getCategory(category);
		this.key = key;
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory(){
		return category;
	}
	
	public void setCategory(Category category){
		this.category = category;
	}
	
	public String getAsLine() {
		return category.getName() + "," + key + "," + name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
