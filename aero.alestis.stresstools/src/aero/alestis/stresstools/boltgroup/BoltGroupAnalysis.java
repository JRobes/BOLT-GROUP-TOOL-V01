package aero.alestis.stresstools.boltgroup;

import java.util.List;

import aero.alestis.stresstools.general.Fastener;

public class BoltGroupAnalysis {
	private List<BoltGroupLoadCase> bgLoadCases;
	private List<Fastener> fastenerGeometry;
	
	public List<BoltGroupLoadCase> getBgLoadCases() {
		return bgLoadCases;
	}

	public void setBgLoadCases(List<BoltGroupLoadCase> bgLoadCases) {
		this.bgLoadCases = bgLoadCases;
	}

	public List<Fastener> getFastenerGeometry() {
		return fastenerGeometry;
	}

	public void setFastenerGeometry(List<Fastener> fastenerGeometry) {
		this.fastenerGeometry = fastenerGeometry;
	}
	
}
