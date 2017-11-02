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
	private static double alpha;
	private static double yTA, zTA, MySA, MzSA;
	static private List<Fastener> translatedFasteners = new ArrayList<Fastener>();


	
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
	public void setReferencePoint(Point<?> point) {
		BoltGroupResult.referencePoint = (Vector3D)point;

	}
	public List<Fastener> getReferenceFasteners() {
		return referenceFasteners;
	}
	public void setReferenceFasteners(List<Fastener> referenceFasteners) {
		System.out.println("Assigned reference fastener list...");
		BoltGroupResult.referenceFasteners = referenceFasteners;
	}
	public PuntualForce getForceAtReferencePoint() {
		return forceAtReferencePoint;
	}
	public void setForceAtReferencePoint(PuntualForce forceAtReferencePoint) {
		BoltGroupResult.forceAtReferencePoint = forceAtReferencePoint;
	}
	public Vector3D getShearCentroidPoint() {
		return shearCentroidPoint;
	}
	public void setShearCentroidPoint(Vector3D shearCentroidPoint) {
		BoltGroupResult.shearCentroidPoint = shearCentroidPoint;
	}
	public double getMomentAbutPointS() {
		return momentAbutPointS;
	}
	public void setMomentAbutPointS(double momentAbutPointS) {
		BoltGroupResult.momentAbutPointS = momentAbutPointS;
	}
	public Vector3D getTensionCentroidPoint() {
		return tensionCentroidPoint;
	}
	public void setTensionCentroidPoint(Vector3D tensionCentroidPoint) {
		BoltGroupResult.tensionCentroidPoint = tensionCentroidPoint;
	}
	public  double getMomentAboutPointT_Y() {
		return momentAboutPointT_Y;
	}
	public  void setMomentAboutPointT_Y(double momentAboutPointT_Y) {
		BoltGroupResult.momentAboutPointT_Y = momentAboutPointT_Y;
	}
	public  double getMomentAboutPointT_Z() {
		return momentAboutPointT_Z;
	}
	public  void setMomentAboutPointT_Z(double momentAboutPointT_Z) {
		BoltGroupResult.momentAboutPointT_Z = momentAboutPointT_Z;
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		BoltGroupResult.alpha = alpha;
	}
	public  double getyTA() {
		return yTA;
	}
	public  void setyTA(double yTA) {
		BoltGroupResult.yTA = yTA;
	}
	public  double getzTA() {
		return zTA;
	}
	public  void setzTA(double zTA) {
		BoltGroupResult.zTA = zTA;
	}
	public  double getMySA() {
		return MySA;
	}
	public  void setMySA(double mySA) {
		MySA = mySA;
	}
	public  double getMzSA() {
		return MzSA;
	}
	public  void setMzSA(double mzSA) {
		MzSA = mzSA;
	}
	public  List<Fastener> getTranslatedFasteners() {
		return translatedFasteners;
	}
	public  void setTranslatedFasteners(List<Fastener> translatedFasteners) {
		BoltGroupResult.translatedFasteners = translatedFasteners;
	}

}
