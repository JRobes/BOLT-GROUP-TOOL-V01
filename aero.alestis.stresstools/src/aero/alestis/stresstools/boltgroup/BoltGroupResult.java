package aero.alestis.stresstools.boltgroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import aero.alestis.stresstools.general.Fastener;

public class BoltGroupResult {
	private String loadCaseID;
	private Vector3D aplicationPoint;
	private static Vector3D referencePoint;
	private List<Fastener> referenceFasteners = new ArrayList<Fastener>();
	private Vector3D shearCentroidPoint;
	
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
	public Vector3D getReferencePoint() {
		System.out.println("vector en get reference ***********************:   "+referencePoint.toString());

		return referencePoint;
	}
	public void setReferencePoint(Point point) {
		this.referencePoint = (Vector3D)point;
		System.out.println("punto ***********************:   "+point.toString());
		System.out.println("vector ***********************:   "+referencePoint.toString());

	}
	public List<Fastener> getReferenceFasteners() {
		return referenceFasteners;
	}
	public void setReferenceFasteners(List<Fastener> referenceFasteners) {
		System.out.println("Assigned reference fastener list...");
		this.referenceFasteners = referenceFasteners;
	}

}
