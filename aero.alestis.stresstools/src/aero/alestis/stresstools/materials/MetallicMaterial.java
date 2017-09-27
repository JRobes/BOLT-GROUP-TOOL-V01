package aero.alestis.stresstools.materials;

public abstract class MetallicMaterial extends StressMaterial {
	private Double Ftall, Fsall;
	public MetallicMaterial() {
		
	}
	public Double getFtall() {
		return Ftall;
	}
	public void setFtall(Double ftall) {
		Ftall = ftall;
	}
	public Double getFsall() {
		return Fsall;
	}
	public void setFsall(Double fsall) {
		Fsall = fsall;
	}
}
