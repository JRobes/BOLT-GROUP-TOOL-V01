package aero.alestis.stresstools.boltgroup;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class BoltGroupResult {
	private String loadCaseID;
	private Vector3D aplicationPoint;
	private Point referencePoint;
	public String getLoadCaseID() {
		return loadCaseID;
	}
	public void setLoadCaseID(String loadCaseID) {
		this.loadCaseID = loadCaseID;
	}
	public Vector3D getAplicationPoint() {
		return aplicationPoint;
	}
	public void setAplicationPoint(Vector3D aaplicationPoint) {
		this.aplicationPoint = aaplicationPoint;
	}
	public Point getReferencePoint() {
		return referencePoint;
	}
	public void setReferencePoint(Point point) {
		this.referencePoint = point;
	}
}
