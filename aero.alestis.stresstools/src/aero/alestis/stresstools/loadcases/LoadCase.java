package aero.alestis.stresstools.loadcases;

import java.util.ArrayList;
import java.util.List;

public class LoadCase {
	private String LoadCaseID;
	private List<Load> loadCaseList = new ArrayList<Load>();

	public void add(Load load) {
		this.loadCaseList.add(load);
	}
	public void remove(Load load) {
		this.loadCaseList.remove(load);
	}
	public void clear() {
		this.loadCaseList.clear();
	}
	
	
	public String getLoadCaseID() {
		return LoadCaseID;
	}

	public void setLoadCaseID(String loadCaseID) {
		LoadCaseID = loadCaseID;
	}
}
