package aero.alestis.stresstools.boltgroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import aero.alestis.stresstools.general.Fastener;
import aero.alestis.stresstools.loadcases.PuntualForce;

public class BoltGroupResult {
	private String loadCaseID;
	private Vector3D aplicationPoint;
	private static Vector3D referencePoint;
	static private List<Fastener> referenceFasteners = new ArrayList<Fastener>();
	private static Vector3D shearCentroidPoint;
	private static PuntualForce forceAtReferencePoint;
	private static double momentAbutPointS;
	private static Vector3D tensionCentroidPoint;
	private static double momentAboutPointT_Y;
	private static double momentAboutPointT_Z;

	
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
		return referencePoint;
	}
	public void setReferencePoint(Point point) {
		this.referencePoint = (Vector3D)point;


	}
	public List<Fastener> getReferenceFasteners() {
		return referenceFasteners;
	}
	public void setReferenceFasteners(List<Fastener> referenceFasteners) {
		System.out.println("Assigned reference fastener list...");
		this.referenceFasteners = referenceFasteners;
	}
	public PuntualForce getForceAtReferencePoint() {
		return forceAtReferencePoint;
	}
	public void setForceAtReferencePoint(PuntualForce forceAtReferencePoint) {
		this.forceAtReferencePoint = forceAtReferencePoint;
	}
	public Vector3D getShearCentroidPoint() {
		return shearCentroidPoint;
	}
	public void setShearCentroidPoint(Vector3D shearCentroidPoint) {
		this.shearCentroidPoint = shearCentroidPoint;
	}
	public double getMomentAbutPointS() {
		return momentAbutPointS;
	}
	public static void setMomentAbutPointS(double momentAbutPointS) {
		BoltGroupResult.momentAbutPointS = momentAbutPointS;
	}
	public static Vector3D getTensionCentroidPoint() {
		return tensionCentroidPoint;
	}
	public static void setTensionCentroidPoint(Vector3D tensionCentroidPoint) {
		BoltGroupResult.tensionCentroidPoint = tensionCentroidPoint;
	}
	public static double getMomentAboutPointT_Y() {
		return momentAboutPointT_Y;
	}
	public static void setMomentAboutPointT_Y(double momentAboutPointT_Y) {
		BoltGroupResult.momentAboutPointT_Y = momentAboutPointT_Y;
	}
	public static double getMomentAboutPointT_Z() {
		return momentAboutPointT_Z;
	}
	public static void setMomentAboutPointT_Z(double momentAboutPointT_Z) {
		BoltGroupResult.momentAboutPointT_Z = momentAboutPointT_Z;
	}

}
