package aero.alestis.stresstools.boltgroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import aero.alestis.stresstools.general.Fastener;
import aero.alestis.stresstools.materials.IFastenerMaterial;

public class BoltGroupAnalysis {
	private Plane boltsPlane;
	private List<BoltGroupLoadCase> bgLoadCases;
	private List<Fastener> fastenerGeometry;
	private Map<String, IFastenerMaterial> materialsMap;
	private Vector3D referencePoint;//esto cambiarlo para cada bgresult
	private List<BoltGroupResult> bgResults;
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
	public Vector3D getReferencePoint() {
		return referencePoint;
	
	}

	public void setReferencePoint(Vector3D referencePoint) {
		this.referencePoint = referencePoint;
	}

    public Plane getFittingPlane() throws BoltGroupPlaneException{
    	
    	if(fastenerGeometry.size() < 3) throw new BoltGroupPlaneException("Numero de remaches menor de 3");
    	boltsPlane = calculatePlane(fastenerGeometry);
        if(boltsPlane==null) throw new BoltGroupPlaneException("No ha sido capaz de encontrar un plano entre todos los puntos...");
        System.out.println("EL PLANO\t"+ boltsPlane.getNormal().toString());
        createBaseChangeMatrix();
    	return boltsPlane;
    }
    
    public void createBaseChangeMatrix() {
    	double[][] arrayParaMatriz = {boltsPlane.getU().toArray(), boltsPlane.getV().toArray(),boltsPlane.getNormal().toArray()};
    	//RealMatrix laMatriz = MatrixUtils.createRealMatrix(arrayParaMatriz);
    	this.setChangeOfBasisMatrix(MatrixUtils.createRealMatrix(arrayParaMatriz));
    	//laMatriz.transpose();

        System.out.println("LA MATRIZ DE CAMBIO DE BASE ES ==============\t"+ this.getChangeOfBasisMatrix());
    	/*
        //laMatriz.operate();
    	double [] vector = {
    			getFastenerGeometry().get(0).getFastenerLocation().getPunto().getX(),
    			getFastenerGeometry().get(0).getFastenerLocation().getPunto().getY(),
    			getFastenerGeometry().get(0).getFastenerLocation().getPunto().getZ()};
    	double[] rv = laMatriz.operate(vector);
    	System.out.println(rv[0]);
    	System.out.println(rv[1]);
    	System.out.println(rv[2]);
    	double [] vector1 = {
    			getFastenerGeometry().get(1).getFastenerLocation().getPunto().getX(),
    			getFastenerGeometry().get(1).getFastenerLocation().getPunto().getY(),
    			getFastenerGeometry().get(1).getFastenerLocation().getPunto().getZ()};
    	double[] rv1 = laMatriz.operate(vector1);
    	System.out.println(rv1[0]);
    	System.out.println(rv1[1]);
    	System.out.println(rv1[2]);
    	double [] vector2 = {
    			getFastenerGeometry().get(2).getFastenerLocation().getPunto().getX(),
    			getFastenerGeometry().get(2).getFastenerLocation().getPunto().getY(),
    			getFastenerGeometry().get(2).getFastenerLocation().getPunto().getZ()};
    	double[] rv2 = laMatriz.operate(vector2);
    	System.out.println(rv2[0]);
    	System.out.println(rv2[1]);
    	System.out.println(rv2[2]);
    	
    	*/
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
    	    System.out.println(listOfFasteners.get(i).getFastenerLocation().getPunto());

		}
		if(listOfFasteners.size() == 3) {
			return new Plane(listOfFasteners.get(0).getFastenerLocation().getPunto(),listOfFasteners.get(1).getFastenerLocation().getPunto(),listOfFasteners.get(2).getFastenerLocation().getPunto(),0.001);
		}
    	for(int i = 0; i <= listOfFasteners.size()-4; i++) {
            for(int j = i+1; j <= listOfFasteners.size()-3;j++) {
                for(int k = j+1; k <=listOfFasteners.size()-2; k++) {
                	for(int p = k+1; p <= listOfFasteners.size()-1; p++) {
                	    System.out.println(i+"\t"+j+"\t"+k+"\t"+p);
                	    punto_1 = Arrays.copyOf(listOfFasteners.get(i).getFastenerLocation().getPunto().toArray(), listOfFasteners.get(i).getFastenerLocation().getPunto().toArray().length+1);
                	    punto_1[3] = 1.;
                	    punto_2 = Arrays.copyOf(listOfFasteners.get(j).getFastenerLocation().getPunto().toArray(), listOfFasteners.get(j).getFastenerLocation().getPunto().toArray().length+1);
                	    punto_2[3] = 1.;
                	    punto_3 = Arrays.copyOf(listOfFasteners.get(k).getFastenerLocation().getPunto().toArray(), listOfFasteners.get(k).getFastenerLocation().getPunto().toArray().length+1);
                	    punto_3[3] = 1.;
                	    punto_4 = Arrays.copyOf(listOfFasteners.get(p).getFastenerLocation().getPunto().toArray(), listOfFasteners.get(p).getFastenerLocation().getPunto().toArray().length+1);
                	    punto_4[3] = 1.;
                	    matriz[0] = punto_1;
                	    matriz[1] = punto_2;
                	    matriz[2] = punto_3;
                	    matriz[3] = punto_4;
                	    Array2DRowRealMatrix laMatriz = new Array2DRowRealMatrix(matriz);
                	    LUDecomposition LUDmatriz = new LUDecomposition(laMatriz);
                	    System.out.println(listOfFasteners.get(0).getFastenerLocation().getPunto());
                	    System.out.println(listOfFasteners.get(1).getFastenerLocation().getPunto());
                	    System.out.println(listOfFasteners.get(2).getFastenerLocation().getPunto());
                	    System.out.println("El determinante\t" + LUDmatriz.getDeterminant());
                	    if(Math.abs(LUDmatriz.getDeterminant()) > TOLERANCIA_PLANO) {
                	    	throw new BoltGroupPlaneException("Estos 4 puntos no son coplanares\t"+i+"\t"+j+"\t"+k+"\t"+p );
                	    }
   
                	}

                }
            }
        }
        
		return new Plane(listOfFasteners.get(0).getFastenerLocation().getPunto(), listOfFasteners.get(1).getFastenerLocation().getPunto(), listOfFasteners.get(2).getFastenerLocation().getPunto(),0.001);
	}
	public void analyze() {
		System.out.println("NUMERO DE CASOS DE CARGA:\t" +bgLoadCases.size());
		for(BoltGroupLoadCase lc :bgLoadCases) {
			setReferencePoint(lc, lc.getBgResult());
			setReferenceFasteners(lc, lc.getBgResult());
		}
	}
	
	private void setReferenceFasteners(BoltGroupLoadCase lc, BoltGroupResult bgresult) {
		List<Fastener> referenceFastenerList = new ArrayList<Fastener>();
		for( Fastener fastener: this.getFastenerGeometry() ) {
			Fastener newFastenLocation = new Fastener();
			fastener.getFastenerLocation().getPunto().subtract(referencePoint);
			RealMatrix inversa = MatrixUtils.inverse(getChangeOfBasisMatrix());
			RealVector rv = inversa.operate(
					MatrixUtils.createRealVector(fastener.getFastenerLocation().getPunto().subtract(referencePoint).toArray()));
			
			System.out.println(rv.toString());
		}
		
	}

	private void setReferencePoint(BoltGroupLoadCase lc, BoltGroupResult bgresult) {
		bgresult.setReferencePoint(boltsPlane.project((Point)lc.getLoadCasePoint().getPunto()));
		System.out.println("EL PUNTO SOBREE EL PLANO:\t" +bgresult.getReferencePoint().toString());
		
	}

	public RealMatrix getChangeOfBasisMatrix() {
		return changeOfBasisMatrix;
	}

	public void setChangeOfBasisMatrix(RealMatrix changeOfBasisMatrix) {
		this.changeOfBasisMatrix = changeOfBasisMatrix;
	}


	
}





