package aero.alestis.stresstools.loadcases;

import java.util.ArrayList;
import java.util.List;

public class LoadCase {
	private String LoadCaseID;
	private List<Load> loadList = new ArrayList<Load>();

	public void add(Load load) {
		this.loadList.add(load);
	}
	public void remove(Load load) {
		this.loadList.remove(load);
	}
	public void clear() {
		this.loadList.clear();
	}
	
	
	public String getLoadCaseID() {
		return LoadCaseID;
	}

	public void setLoadCaseID(String loadCaseID) {
		LoadCaseID = loadCaseID;
	}
}
