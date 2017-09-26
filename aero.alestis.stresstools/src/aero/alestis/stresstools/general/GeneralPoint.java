package aero.alestis.stresstools.general;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class GeneralPoint {
	private String pointID;
	private Vector3D punto;
	
	/*
	public GeneralPoint(String pointID, Vector3D punto) {
		this.pointID = pointID;
		this.punto = punto;
	}
	*/
	
	public GeneralPoint() {
	
	}
	
	public String getPointID() {
		return pointID;
	}
	public void setPointID(String pointID) {
		this.pointID = pointID;
	}
	public Vector3D getPunto() {
		return punto;
	}
	public void setPunto(Vector3D punto) {
		this.punto = punto;
	}
	

}
