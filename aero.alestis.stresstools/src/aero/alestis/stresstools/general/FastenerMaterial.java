package aero.alestis.stresstools.general;

public class FastenerMaterial {
	private String fastenerType;
	private Double Ftu;
	private Double Fsu;
	
	public FastenerMaterial() {
		
	}

	public String getFastenerID() {
		return fastenerType;
	}

	public void setFastenerID(String fastenerID) {
		this.fastenerType = fastenerID;
	}

	public Double getFtu() {
		return Ftu;
	}

	public void setFtu(Double ftu) {
		Ftu = ftu;
	}

	public Double getFsu() {
		return Fsu;
	}

	public void setFsu(Double fsu) {
		Fsu = fsu;
	}

}
