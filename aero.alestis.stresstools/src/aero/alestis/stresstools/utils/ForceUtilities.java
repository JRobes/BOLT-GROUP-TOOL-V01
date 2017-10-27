package aero.alestis.stresstools.utils;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import aero.alestis.stresstools.loadcases.PuntualForce;

public final class ForceUtilities {
	public static PuntualForce translateForce(PuntualForce forceOriginal, Vector3D origin, Vector3D endPoint) {
		Double Mx = forceOriginal.getMx() - forceOriginal.getFy()*(origin.getZ()-endPoint.getZ())+forceOriginal.getFz()*(origin.getY()-endPoint.getY());
		Double My = forceOriginal.getMy() + forceOriginal.getFx()*(origin.getZ()-endPoint.getZ())-forceOriginal.getFz()*(origin.getX()-endPoint.getX());
		Double Mz = forceOriginal.getMz() - forceOriginal.getFx()*(origin.getY()-endPoint.getY())+forceOriginal.getFy()*(origin.getX()-endPoint.getX());
		return new PuntualForce(forceOriginal.getLoadCase(), forceOriginal.getFx(),	forceOriginal.getFy(), forceOriginal.getFz(), Mx, My, Mz);
	}
}
