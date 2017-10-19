package aero.alestis.stresstools.boltgroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import aero.alestis.stresstools.general.Fastener;

public class BoltGroupResult {
	private String loadCaseID;
	private Vector3D aplicationPoint;
	private Point referencePoint;
	private List<Fastener> referenceFasteners = new ArrayList<Fastener>();
	
	public BoltGroupResult(String loadCaseID) {
		this.loadCaseID = loadCaseID;
	}
	public String getLoadCaseID() {
		return loadCaseID;
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
