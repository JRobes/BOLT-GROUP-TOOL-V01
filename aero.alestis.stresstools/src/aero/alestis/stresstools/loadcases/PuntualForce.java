package aero.alestis.stresstools.loadcases;

public class PuntualForce {
	private String forceID;
	private Double Fx, Fy, Fz, Mx, My, Mz;
	
	public PuntualForce(String lc, Double Fx, Double Fy, Double Fz,Double Mx, Double My, Double Mz) {
		this.forceID = lc;
		this.Fx = Fx;
		this.Fy = Fy;
		this.Fz = Fz;
		this.Mx = Mx;
		this.My = My;
		this.Mz = Mz;
	}
	
	public PuntualForce() {
		// TODO Auto-generated constructor stub
	}

	public Double getFx() {
		return Fx;
	}

	public Double getFy() {
		return Fy;
	}

	public Double getFz() {
		return Fz;
	}

	public Double getMx() {
		return Mx;
	}

	public Double getMy() {
		return My;
	}

	public Double getMz() {
		return Mz;
	}

	public String getLoadCase() {
		return forceID;
	}
	
	
}
