package ro.tirzuman.ioana.customtags;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ro.tirzuman.ioana.dao.RecordRepository;
import ro.tirzuman.ioana.model.Record;

public class RecordTagHandler extends SimpleTagSupport implements DynamicAttributes {

	private String key;
	private String category;
	private Map<String, Object> dynamicAttributes;

	public void doTag() throws JspException {
		try {
			JspWriter out = getJspContext().getOut();
			if (key != null) {
				Record record = RecordRepository.getInstance().getRecord(key, category);
				if (record != null) {
					out.println(record.getName());
				}
			} else {
				JspFragment f = getJspBody();
				if (f != null) {
					// get the body and process it
					StringWriter sw = new StringWriter();
					f.invoke(sw);
					String result = sw.toString();
					for (String recordKey : result.split(",")) {
						Record record = RecordRepository.getInstance().getRecord(recordKey);
						if (record != null) {
							out.println(record.getName());
						}
					}
				}
			}
		} catch (java.io.IOException ex) {
			throw new JspException("Error in the handler tag", ex);
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		if (category != null && category.trim().isEmpty()) {
			category = null;
		}
		this.category = category;
	}

	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
		dynamicAttributes.put(name, value);
	}

}
