package monitor.parser.models;

import java.util.List;

public class PIDHistoryElement {
	private String id;
	private String Name;
	private List<DataElement> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<DataElement> getData() {
		return data;
	}

	public void setData(List<DataElement> data) {
		this.data = data;
	}

}
