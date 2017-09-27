package aero.alestis.stresstools.materials;

public class FastenerMaterial extends MetallicMaterial implements IFastenerMaterial {
	private Double fastenerDiameter;
	public FastenerMaterial(String materialID, Double Ftuall, Double Fsuall) {
		super.setFsall(Fsuall);
		super.setFtall(Ftuall);
		super.materialID = materialID;
	}

	@Override
	public String getMaterialID() {
		return this.materialID;
	}

	@Override
	public Double getMaterialDiameter() {
		// TODO Auto-generated method stub
		return fastenerDiameter;
	}


}
