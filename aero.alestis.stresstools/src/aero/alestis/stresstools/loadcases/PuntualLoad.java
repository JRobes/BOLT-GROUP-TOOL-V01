package aero.alestis.stresstools.loadcases;

import aero.alestis.stresstools.general.GeneralPoint;

public class PuntualLoad extends Load {
	
	private String LoadCaseID;
	private GeneralPoint loadApplicationPoint;
	private PuntualForce puntualForce;
	
	@Override
	public int getLoadType() {
		return LoadType.PUNTUAL_LOAD;
	}
    
	public GeneralPoint getLoadApplicationPoint() {
		return loadApplicationPoint;
	}

	public void setLoadApplicationPoint(GeneralPoint loadApplicationPoint) {
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
