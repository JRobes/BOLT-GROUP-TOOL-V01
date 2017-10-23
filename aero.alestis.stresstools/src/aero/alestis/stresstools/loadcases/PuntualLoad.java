package aero.alestis.stresstools.loadcases;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;


public class PuntualLoad extends Load {
	
	private String LoadCaseID;
	private Vector3D loadApplicationPoint;
	private PuntualForce puntualForce;
	
	@Override
	public int getLoadType() {
		return LoadType.PUNTUAL_LOAD;
	}
    
	public Vector3D getLoadApplicationPoint() {
		return loadApplicationPoint;
	}

	public void setLoadApplicationPoint(Vector3D loadApplicationPoint) {
		this.loadApplicationPoint = loadApplicationPoint;
	}

	public PuntualForce getPuntualForce() {
		return puntualForce;
	}

	public void setPuntualForce(PuntualForce puntualForce) {
		this.puntualForce = puntualForce;
	}

	public String getLoadCaseID() {
		return LoadCaseID;
	}

	public void setLoadCaseID(String loadCaseID) {
		LoadCaseID = loadCaseID;
	}

}
