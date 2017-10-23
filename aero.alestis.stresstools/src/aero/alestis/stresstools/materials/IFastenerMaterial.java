package aero.alestis.stresstools.materials;

public interface IFastenerMaterial extends IStressMaterial {
	public String getMaterialID();
	public Double getMaterialDiameter();
	public Double getFsall();
	public Double getFtall();
}
