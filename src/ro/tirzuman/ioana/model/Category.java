package ro.tirzuman.ioana.model;

import java.io.Serializable;

public class Category implements Serializable{
	private static final long serialVersionUID = 2015822545026762451L;

	private String name;

	public Category(String categoryName) {
		this.name = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String categoryName) {
		this.name = categoryName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
