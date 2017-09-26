package aero.alestis.stresstools.loadcases;

public abstract class Load implements ILoad{

	private String Load_ID;

	public String getLoad_ID() {
		return Load_ID;
	}

	public void setLoad_ID(String load_ID) {
		Load_ID = load_ID;
	}
	
}
