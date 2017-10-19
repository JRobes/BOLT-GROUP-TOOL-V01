
import java.io.IOException;

import org.apache.commons.math3.geometry.euclidean.threed.Plane;

import aero.alestis.stresstools.boltgroup.BoltGroupAnalysis;
import aero.alestis.stresstools.boltgroup.BoltGroupPlaneException;
import aero.alestis.stresstools.boltgroup.parsers.BoltGroupExcelParser;

public class excel_example {
	private static BoltGroupAnalysis analysis = new BoltGroupAnalysis();
	private static String EXCEL_FILE = System.getProperty("user.dir")+"/J-BOLT-GROUP-V01.xls";
	//private static String EXCEL_FILE = "C:\\Users\\javier.robes\\Desktop\\TASKS\\52_BOLT-GROUP-TOOL\\Documentos\\J-BOLT-GROUP-V01.xls";
	private static BoltGroupExcelParser parser;
	public static void main(String[] args) throws IOException {
	    parser = new BoltGroupExcelParser(EXCEL_FILE);
		parser.parse(analysis);
		
		
		try {
			Plane plano = analysis.getFittingPlane();
			
		} catch (BoltGroupPlaneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(analysis.getFastenerGeometry().size());
		
		System.out.println(System.getProperty("user.dir"));
		analysis.analyze();
		
	}

}
