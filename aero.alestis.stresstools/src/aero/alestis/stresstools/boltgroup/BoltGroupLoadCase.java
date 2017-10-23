package aero.alestis.stresstools.boltgroup;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import aero.alestis.stresstools.loadcases.LoadCase;
import aero.alestis.stresstools.loadcases.PuntualForce;

public class BoltGroupLoadCase extends LoadCase {
	private String bgLoadCaseID;
	private Vector3D loadCasePoint;
	private PuntualForce loadCaseForce;
	private BoltGroupResult bgResult;
	public BoltGroupLoadCase(String BGLoadCaseID, Vector3D loadCasePoint, PuntualForce loadCaseForce) {
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
	public Vector3D getLoadCasePoint() {
		return loadCasePoint;
	}
	public void setLoadCasePoint(Vector3D loadCasePoint) {
		this.loadCasePoint = loadCasePoint;
	}
	public PuntualForce getLoadCaseForce() {
		return loadCaseForce;
	}
	public void setLoadCaseForce(PuntualForce loadCaseLoad) {
		this.loadCaseForce = loadCaseLoad;
	}
	public BoltGroupResult getBgResult() {
		if(bgResult == null) return new BoltGroupResult(bgLoadCaseID);
		return bgResult;
	}
	public void setBgResult(BoltGroupResult bgResult) {
		this.bgResult = bgResult;
	}
	
	
}
