package aero.alestis.stresstools.general;


public class Fastener {
	
	private String FastenerType;
	//IFastenerMaterial fastenerMaterial;
	private GeneralPoint fastenerLocation;
	
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


}
