package expense.tracker.dao.datatable;

public class Search {

	private String value;
	private boolean regex;
	public Search(String value, boolean regex) {
		super();
		this.value = value;
		this.regex = regex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	
}
