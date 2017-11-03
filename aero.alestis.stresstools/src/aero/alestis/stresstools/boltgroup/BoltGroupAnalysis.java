package aero.alestis.stresstools.boltgroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import aero.alestis.stresstools.general.Fastener;
import aero.alestis.stresstools.loadcases.PuntualForce;
import aero.alestis.stresstools.materials.IFastenerMaterial;
import aero.alestis.stresstools.utils.ForceUtilities;

public class BoltGroupAnalysis {
	private Plane boltsPlane;
	private List<BoltGroupLoadCase> bgLoadCases;
	private List<Fastener> fastenerGeometry;
	private Map<String, IFastenerMaterial> materialsMap;
	//private Vector3D referencePoint;//esto cambiarlo para cada bgresult
	private RealMatrix changeOfBasisMatrix;
	
	public List<BoltGroupLoadCase> getBgLoadCases() {
		return bgLoadCases;
	}
	
	
	 

	public void setBgLoadCases(List<BoltGroupLoadCase> listOfLoadCases) {
		this.bgLoadCases = listOfLoadCases;
	}

	public List<Fastener> getFastenerGeometry() {
		return fastenerGeometry;
	}

	public void setFastenerGeometry(List<Fastener> fastenerGeometry) {
		this.fastenerGeometry = fastenerGeometry;
	}

	public Map<String, IFastenerMaterial> getMaterialsMap() {
		return materialsMap;
	}

	public void setMaterialsMap(Map<String, IFastenerMaterial> materialsMap) {
		this.materialsMap = materialsMap;
	}
	

    public Plane getFittingPlane() throws BoltGroupPlaneException{
    	
    	if(fastenerGeometry.size() < 3) throw new BoltGroupPlaneException("Numero de remaches menor de 3");
    	boltsPlane = calculatePlane(fastenerGeometry);
        if(boltsPlane==null) throw new BoltGroupPlaneException("No ha sido capaz de encontrar un plano entre todos los puntos...");
        //boltsPlane.getNormal().negate();
        System.out.println("EL PLANO\t"+ boltsPlane.getNormal().toString());
        createBaseChangeMatrix();
    	return boltsPlane;
    }
    
    public void createBaseChangeMatrix() {
    	double[][] arrayParaMatriz = {boltsPlane.getNormal().negate().toArray(), boltsPlane.getV().toArray(), boltsPlane.getU().toArray()};
    	//RealMatrix laMatriz = MatrixUtils.createRealMatrix(arrayParaMatriz);
    	this.setChangeOfBasisMatrix(MatrixUtils.createRealMatrix(arrayParaMatriz));
    	//laMatriz.transpose();

        System.out.println("LA MATRIZ DE CAMBIO DE BASE ES ==============\t"+ this.getChangeOfBasisMatrix());
 
    }
    
    

	private static Plane calculatePlane(List<Fastener> listOfFasteners) throws BoltGroupPlaneException{
		double TOLERANCIA_PLANO = 1;
		double[] punto_1;
		double[] punto_2;
		double[] punto_3;
		double[] punto_4;
		double[][] matriz = new double[4][4];
		for(int i = 0 ; i < listOfFasteners.size(); i++) {
			System.out.println("FFFFFFFFFFFF");
    	    System.out.println(listOfFasteners.get(i).getFastenerCords());

		}
		if(listOfFasteners.size() == 3) {
			return new Plane(listOfFasteners.get(0).getFastenerCords(),listOfFasteners.get(1).getFastenerCords(),listOfFasteners.get(2).getFastenerCords(),0.001);
		}
    	for(int i = 0; i <= listOfFasteners.size()-4; i++) {
            for(int j = i+1; j <= listOfFasteners.size()-3;j++) {
                for(int k = j+1; k <=listOfFasteners.size()-2; k++) {
                	for(int p = k+1; p <= listOfFasteners.size()-1; p++) {
                	    System.out.println(i+"\t"+j+"\t"+k+"\t"+p);
                	    punto_1 = Arrays.copyOf(listOfFasteners.get(i).getFastenerCords().toArray(), listOfFasteners.get(i).getFastenerCords().toArray().length+1);
                	    punto_1[3] = 1.;
                	    punto_2 = Arrays.copyOf(listOfFasteners.get(j).getFastenerCords().toArray(), listOfFasteners.get(j).getFastenerCords().toArray().length+1);
                	    punto_2[3] = 1.;
                	    punto_3 = Arrays.copyOf(listOfFasteners.get(k).getFastenerCords().toArray(), listOfFasteners.get(k).getFastenerCords().toArray().length+1);
                	    punto_3[3] = 1.;
                	    punto_4 = Arrays.copyOf(listOfFasteners.get(p).getFastenerCords().toArray(), listOfFasteners.get(p).getFastenerCords().toArray().length+1);
                	    punto_4[3] = 1.;
                	    matriz[0] = punto_1;
                	    matriz[1] = punto_2;
                	    matriz[2] = punto_3;
                	    matriz[3] = punto_4;
                	    Array2DRowRealMatrix laMatriz = new Array2DRowRealMatrix(matriz);
                	    LUDecomposition LUDmatriz = new LUDecomposition(laMatriz);
                	    System.out.println(listOfFasteners.get(0).getFastenerCords());
                	    System.out.println(listOfFasteners.get(1).getFastenerCords());
                	    System.out.println(listOfFasteners.get(2).getFastenerCords());
                	    System.out.println("El determinante\t" + LUDmatriz.getDeterminant());
                	    if(Math.abs(LUDmatriz.getDeterminant()) > TOLERANCIA_PLANO) {
                	    	throw new BoltGroupPlaneException("Estos 4 puntos no son coplanares\t"+i+"\t"+j+"\t"+k+"\t"+p );
                	    }
   
                	}

                }
            }
        }
        
		return new Plane(listOfFasteners.get(0).getFastenerCords(), listOfFasteners.get(1).getFastenerCords(), listOfFasteners.get(2).getFastenerCords(),0.001);
	}
	public void analyze() {
		System.out.println("NUMERO DE CASOS DE CARGA:\t" +bgLoadCases.size());
		for(BoltGroupLoadCase lc :bgLoadCases) {
			setReferencePoint(lc);
			setTheReferenceFastenerList(lc);
			setShearCentroidPoint(lc);
			moveTheForceToReferencePoint(lc);
			momentAboutPointS(lc);
			calculateShearForcesInFasteners(lc);
			setTheTensionCentroidPoint(lc);
			momentsAboutPointT(lc);
			
			calculateAlpha(lc);
			calculateTransformTheAppliedLoadToPpalAxis(lc);
			
			//calculateTensionForcesInFasteners(lc);

			
		}
	}
	
	private void calculateTransformTheAppliedLoadToPpalAxis(BoltGroupLoadCase lc) {
		System.out.println("calculateTransformTheAppliedLoadToPpalAxis...");
		System.out.println("ANGULO..."+Math.toDegrees(lc.getBgResult().getAlpha()));

		List<Fastener> transformedFastenerList = new ArrayList<Fastener>();
		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			Fastener f = fast;
			double yAi = fast.getFastenerCords().getY()*Math.cos(lc.getBgResult().getAlpha()) + fast.getFastenerCords().getZ()*Math.sin(lc.getBgResult().getAlpha());
			double zAi = -fast.getFastenerCords().getY()*Math.sin(lc.getBgResult().getAlpha()) + fast.getFastenerCords().getZ()*Math.cos(lc.getBgResult().getAlpha());
			f.setFastenerCords(new Vector3D(0.0, yAi,zAi));
			transformedFastenerList.add(f);
			System.out.println("FASTENER TRANSFORMADO\t"+f.getFastenerCords().getY()+", "+f.getFastenerCords().getZ()+" FASTENER ORIGINAL "+fast.getFastenerCords().getY()+", "+fast.getFastenerCords().getZ());
		}
		Double yta, zta;
		yta = lc.getBgResult().getTensionCentroidPoint().getY()*Math.cos(lc.getBgResult().getAlpha()) + 
				lc.getBgResult().getTensionCentroidPoint().getZ()*Math.sin(lc.getBgResult().getAlpha());
		System.out.println("R______________fddsdsds____R\t"+ lc.getBgResult().getTensionCentroidPoint().getY() +"sdsdsdsd"+yta);
		zta = -lc.getBgResult().getTensionCentroidPoint().getY()*Math.sin(lc.getBgResult().getAlpha()) + 
				lc.getBgResult().getTensionCentroidPoint().getZ()*Math.cos(lc.getBgResult().getAlpha());
		System.out.println("R______________fddsdsds____R\t"+ lc.getBgResult().getTensionCentroidPoint().getZ() +"sdsdsdsd"+zta);
		

	}

	private void calculateAlpha(BoltGroupLoadCase lc) {
		System.out.println("calculateAlpha...");
		Double sumatorioNumerador = 0.0,  sumatorioDenom_1 = 0.0, sumatorioDenom_2 = 0.0;
		Double alpha;
		System.out.println("################################################################################");
		System.out.println("Tension centroid point: (0, "+lc.getBgResult().getTensionCentroidPoint().getY()+", "+lc.getBgResult().getTensionCentroidPoint().getZ()+")");

		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast.getFastenerType());
			sumatorioNumerador += fastMat.getFtall()*(fast.getFastenerCords().getY()-lc.getBgResult().getTensionCentroidPoint().getY())*(
					fast.getFastenerCords().getZ()-lc.getBgResult().getTensionCentroidPoint().getZ());
			sumatorioDenom_1 += fastMat.getFtall()*(Math.pow(fast.getFastenerCords().getY()-lc.getBgResult().getTensionCentroidPoint().getY(), 2.0));
			sumatorioDenom_2 += fastMat.getFtall()*(Math.pow(fast.getFastenerCords().getZ()-lc.getBgResult().getTensionCentroidPoint().getZ(), 2.0));

		}
		alpha = Math.atan(2*(sumatorioNumerador/(sumatorioDenom_1-sumatorioDenom_2)))/2;
		lc.getBgResult().setAlpha(alpha);
		System.out.println("calculateAlpha...\t"+Math.toDegrees(alpha));
		lc.getBgResult().setyTA(lc.getBgResult().getTensionCentroidPoint().getY()*Math.cos(alpha)+lc.getBgResult().getTensionCentroidPoint().getZ()*Math.sin(alpha));
		lc.getBgResult().setzTA(-lc.getBgResult().getTensionCentroidPoint().getY()*Math.sin(alpha)+lc.getBgResult().getTensionCentroidPoint().getZ()*Math.cos(alpha));
		System.out.println("yTA...\t"+lc.getBgResult().getyTA());
		System.out.println("zTA...\t"+lc.getBgResult().getzTA());
		
		lc.getBgResult().setMySA(lc.getBgResult().getMomentAboutPointT_Y()*Math.cos(alpha)+lc.getBgResult().getMomentAboutPointT_Z()*Math.sin(alpha));
		lc.getBgResult().setMzSA(-lc.getBgResult().getMomentAboutPointT_Y()*Math.sin(alpha)+lc.getBgResult().getMomentAboutPointT_Z()*Math.cos(alpha));
		System.out.println("MySA...\t"+lc.getBgResult().getMySA());
		System.out.println("MzSA...\t"+lc.getBgResult().getMzSA());
	}

	@SuppressWarnings("unused")
	private void calculateTensionForcesInFasteners(BoltGroupLoadCase lc) {
		System.out.println("calculateTensionForcesInFasteners...");
		System.out.println("\tcalculateAlpha...");
		System.out.println(Math.sin(Math.PI/2));
		System.out.println(Math.tan(Math.PI));
		System.out.println(Math.toDegrees(Math.atan(Math.tan(Math.PI/3))));
		Double sumatorioAdmisiblesFast = 0.0,  sumatorioDenominador_2 = 0.0, sumatorioDenominador_3 = 0.0;
		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast.getFastenerType());
			sumatorioAdmisiblesFast += fastMat.getFtall();
			sumatorioDenominador_2 += fastMat.getFtall()*
					(Math.pow(fast.getFastenerCords().getZ()-lc.getBgResult().getTensionCentroidPoint().getZ(), 2));
			sumatorioDenominador_3 += fastMat.getFtall()*
					(Math.pow(fast.getFastenerCords().getY()-lc.getBgResult().getTensionCentroidPoint().getY(), 2));
			
		}
		for(Fastener fast2: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast2.getFastenerType());
			Double F1 = 0.0, F2=0.0, F3= 0.0;
			F1 = lc.getBgResult().getForceAtReferencePoint().getFx();
			F2 = lc.getBgResult().getMomentAboutPointT_Y()*(fastMat.getFtall()*(fast2.getFastenerCords().getZ()-lc.getBgResult().getTensionCentroidPoint().getZ())/sumatorioDenominador_2);
			F3 = lc.getBgResult().getMomentAboutPointT_Z()*(fastMat.getFtall()*(fast2.getFastenerCords().getY()-lc.getBgResult().getTensionCentroidPoint().getY())/sumatorioDenominador_3);
			Double FT = F1+F2-F3;
			System.out.println("Fuerza en el fastener...\t"+ FT);

		}
	}

	private void momentsAboutPointT(BoltGroupLoadCase lc) {
		System.out.println("momentsAboutPointT...");
		PuntualForce pf = lc.getBgResult().getForceAtReferencePoint();
		lc.getBgResult().setMomentAboutPointT_Y(pf.getMy() - pf.getFx()*lc.getBgResult().getShearCentroidPoint().getZ());
		lc.getBgResult().setMomentAboutPointT_Z(pf.getMz() + pf.getFx()*lc.getBgResult().getShearCentroidPoint().getY());

		//System.out.println("La fuerza en el plano:  (N·mm)\t\t"+ pf.getMx()+"\t"+pf.getMy()+"\t"+pf.getMz());
		System.out.println("Moment about T - - Y:\t"+lc.getBgResult().getMomentAboutPointT_Y());	
		System.out.println("Moment about T - - Z:\t"+lc.getBgResult().getMomentAboutPointT_Z());		

	}

	private void setTheTensionCentroidPoint(BoltGroupLoadCase lc) {
		System.out.println("setTheTensionCentroidPoint...");
		Double numerador_y = 0.0,  denominador = 0.0;
		Double numerador_z = 0.0;
		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast.getFastenerType());
			numerador_y += fast.getFastenerCords().getY()*fastMat.getFtall();
			numerador_z += fast.getFastenerCords().getZ()*fastMat.getFtall();
			denominador += fastMat.getFtall();
			System.out.println("Admisible tension:\t"+fastMat.getFtall());
		}
		System.out.println("TENSION CENTROID POINT Y:\t"+numerador_y/denominador);
		System.out.println("TENSION CENTROID POINT Z:\t"+numerador_z/denominador);
		lc.getBgResult().setTensionCentroidPoint(new Vector3D(0.0,numerador_y/denominador,numerador_z/denominador));
	}

	private void calculateShearForcesInFasteners(BoltGroupLoadCase lc) {
		Double sumatorioAdmisiblesFast = 0.0,  sumatorioDenominador = 0.0;
		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast.getFastenerType());
			sumatorioAdmisiblesFast += fastMat.getFsall();
			sumatorioDenominador += fastMat.getFsall()*
					(Math.pow(fast.getFastenerCords().getY()-lc.getBgResult().getShearCentroidPoint().getY(), 2)
				    +Math.pow(fast.getFastenerCords().getZ()-lc.getBgResult().getShearCentroidPoint().getZ(), 2));
		}
		for(Fastener fast2: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat2 = materialsMap.get(fast2.getFastenerType());
			@SuppressWarnings("unused")
			Double Fsi = 0.0, Fsyi = 0.0, Fszi = 0.0;
			Fsyi = lc.getBgResult().getForceAtReferencePoint().getFy()*(fastMat2.getFsall()/sumatorioAdmisiblesFast)
				  -lc.getBgResult().getMomentAbutPointS()*(fastMat2.getFsall()*
						  (fast2.getFastenerCords().getZ()-lc.getBgResult().getShearCentroidPoint().getZ())/sumatorioDenominador);
			Fszi = lc.getBgResult().getForceAtReferencePoint().getFz()*(fastMat2.getFsall()/sumatorioAdmisiblesFast)
					  -lc.getBgResult().getMomentAbutPointS()*(fastMat2.getFsall()*
							  (fast2.getFastenerCords().getY()-lc.getBgResult().getShearCentroidPoint().getY())/sumatorioDenominador);
		System.out.println("SSSSSSSSSSSSSSS\t"+ Math.pow(Math.pow(Fsyi, 2)+Math.pow(Fszi, 2), 0.5));
		}
		
		
	}


	private void momentAboutPointS(BoltGroupLoadCase lc) {
		System.out.println("momentAboutPointS...");
		PuntualForce pf = lc.getBgResult().getForceAtReferencePoint();
		lc.getBgResult().setMomentAbutPointS(pf.getMx() + pf.getFy()*lc.getBgResult().getShearCentroidPoint().getZ() -pf.getFz()*lc.getBgResult().getShearCentroidPoint().getY());
		//System.out.println("La fuerza en el plano:  (N·mm)\t\t"+ pf.getMx()+"\t"+pf.getMy()+"\t"+pf.getMz());
		System.out.println("Moment about S:\t"+lc.getBgResult().getMomentAbutPointS());
	}

	private void moveTheForceToReferencePoint(BoltGroupLoadCase lc) {
		System.out.println("moveTheForceToTheReferencePoint...");
		PuntualForce pf = ForceUtilities.translateForce(lc.getLoadCaseForce(), lc.getLoadCasePoint(), lc.getBgResult().getReferencePoint());
		//double jander = pf.getMx() + pf.getFy()*lc.getBgResult().getShearCentroidPoint().getZ() -pf.getFz()*lc.getBgResult().getShearCentroidPoint().getY();
		//System.out.println("Moment about S:\t"+jander);
		Vector3D force = new Vector3D(pf.getFx(),pf.getFy(),pf.getFz());
		Vector3D newF = new Vector3D(changeOfBasisMatrix.operate(force.toArray()));
		Vector3D moment = new Vector3D(pf.getMx(),pf.getMy(),pf.getMz());
		Vector3D newM = new Vector3D(changeOfBasisMatrix.operate(moment.toArray()));
		PuntualForce pf2 = new PuntualForce(lc.getLoadCaseID(),newF.getX(),newF.getY(),newF.getZ(),newM.getX(),newM.getY(),newM.getZ());
		lc.getBgResult().setForceAtReferencePoint(pf2);

	}

	private void setShearCentroidPoint(BoltGroupLoadCase lc) {
		System.out.println("setShearCentroidPoint...");
		Double numerador_y = 0.0,  denominador = 0.0;
		Double numerador_z = 0.0;
		for(Fastener fast: lc.getBgResult().getReferenceFasteners()) {
			IFastenerMaterial fastMat = materialsMap.get(fast.getFastenerType());
			numerador_y += fast.getFastenerCords().getY()*fastMat.getFsall();
			numerador_z += fast.getFastenerCords().getZ()*fastMat.getFsall();
			denominador += fastMat.getFsall();
			System.out.println("Admisible shear:\t"+fastMat.getFsall());
		}
		System.out.println("SHEAR CENTROID POINT Y:\t"+numerador_y/denominador);
		System.out.println("SHEAR CENTROID POINT Z:\t"+numerador_z/denominador);
		lc.getBgResult().setShearCentroidPoint(new Vector3D(0.0,numerador_y/denominador,numerador_z/denominador));
	}

	private void setTheReferenceFastenerList(BoltGroupLoadCase lc) {
		List<Fastener> referenceFastenerList = new ArrayList<Fastener>();
		RealMatrix inversa = MatrixUtils.inverse(getChangeOfBasisMatrix());

		for( Fastener fastener: this.getFastenerGeometry() ) {
			RealVector rv = inversa.operate(MatrixUtils.createRealVector(fastener.getFastenerCords().subtract(lc.getBgResult().getReferencePoint()).toArray()));
			System.out.println("El vector Real será el fastener en el plano:\t"+rv.toString());
			Fastener refFastener = new Fastener("Ref_"+fastener.getFastenerID(),new Vector3D(rv.toArray()),fastener.getFastenerType());
			referenceFastenerList.add(refFastener);
		}
		lc.getBgResult().setReferenceFasteners(referenceFastenerList);
		System.out.println("NUMERO FASTENERS EN EL PLANO DE REFERENCIA:\t"+ lc.getBgResult().getReferenceFasteners().size());
	}

	private void setReferencePoint(BoltGroupLoadCase lc) {
		lc.getBgResult().setReferencePoint(boltsPlane.project((Point<Euclidean3D>)lc.getLoadCasePoint()));
		System.out.println("EL PUNTO SOBREE EL PLANO:\t" +lc.getBgResult().getReferencePoint());
	}

	public RealMatrix getChangeOfBasisMatrix() {
		return changeOfBasisMatrix;
	}

	public void setChangeOfBasisMatrix(RealMatrix changeOfBasisMatrix) {
		this.changeOfBasisMatrix = changeOfBasisMatrix;
	}

	
}





