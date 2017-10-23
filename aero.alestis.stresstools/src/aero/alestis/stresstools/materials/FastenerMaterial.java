package aero.alestis.stresstools.materials;

public class FastenerMaterial extends MetallicMaterial implements IFastenerMaterial {
	private Double fastenerDiameter;
	public FastenerMaterial(String materialID, Double Ftuall, Double Fsuall, Double fastDiameter) {
		super.setFsall(Fsuall);
		super.setFtall(Ftuall);
		super.materialID = materialID;
		fastenerDiameter = fastDiameter;
	}

	@Override
	public String getMaterialID() {
		return this.materialID;
	}

	@Override
	public Double getMaterialDiameter() {
		return fastenerDiameter;
	}


}
