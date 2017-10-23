package aero.alestis.stresstools.general;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Fastener {
	private String fastenerID;
	private String FastenerType;
	private GeneralPoint fastenerLocation;
	private Vector3D fastenerCords;
	public Fastener(String fastID, GeneralPoint coords, String fastType ) {
		
	}
	
	public Fastener() {
		// TODO Auto-generated constructor stub
	}

	public GeneralPoint getFastenerLocation() {
		
		return fastenerLocation;
	}
	public void setFastenerLocation(GeneralPoint fastenerLocation) {
		
		this.fastenerLocation = fastenerLocation;
	}
	public void setFastenerType(String fastenerType) {
		
		this.FastenerType = fastenerType;
	}
	public String getFastenerType() {
		return FastenerType;
		
	}
	public String getFastenerID() {
		return fastenerID;
	}
	public void setFastenerID(String fastenerID) {
		this.fastenerID = fastenerID;
	}

	public Vector3D getFastenerCords() {
		return fastenerCords;
	}

	public void setFastenerCords(Vector3D fastenerCords) {
		this.fastenerCords = fastenerCords;
	}


}
