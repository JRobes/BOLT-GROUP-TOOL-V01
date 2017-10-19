package aero.alestis.stresstools.boltgroup;

import aero.alestis.stresstools.general.GeneralPoint;
import aero.alestis.stresstools.loadcases.LoadCase;
import aero.alestis.stresstools.loadcases.PuntualForce;

public class BoltGroupLoadCase extends LoadCase {
	private String bgLoadCaseID;
	private GeneralPoint loadCasePoint;
	private PuntualForce loadCaseForce;
	private BoltGroupResult bgResult;
	public BoltGroupLoadCase(String BGLoadCaseID, GeneralPoint loadCasePoint, PuntualForce loadCaseForce) {
		this.setBgLoadCaseID(BGLoadCaseID);
		this.setLoadCasePoint(loadCasePoint);
		this.setLoadCaseForce(loadCaseForce);
	}
	public String getBgLoadCaseID() {
		return bgLoadCaseID;
	}
	public void setBgLoadCaseID(String bgLoadCaseID) {
		this.bgLoadCaseID = bgLoadCaseID;
	}
	public GeneralPoint getLoadCasePoint() {
		return loadCasePoint;
	}
	public void setLoadCasePoint(GeneralPoint loadCasePoint) {
		this.loadCasePoint = loadCasePoint;
	}
	public PuntualForce getLoadCaseForce() {
		return loadCaseForce;
	}
	public void setLoadCaseForce(PuntualForce loadCaseLoad) {
		this.loadCaseForce = loadCaseLoad;
	}
	
	
}
