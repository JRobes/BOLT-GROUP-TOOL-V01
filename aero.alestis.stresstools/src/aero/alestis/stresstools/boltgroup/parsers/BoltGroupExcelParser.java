package aero.alestis.stresstools.boltgroup.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aero.alestis.stresstools.boltgroup.BoltGroupAnalysis;
import aero.alestis.stresstools.boltgroup.BoltGroupLoadCase;
import aero.alestis.stresstools.general.Fastener;
import aero.alestis.stresstools.general.GeneralPoint;
import aero.alestis.stresstools.materials.FastenerMaterial;
import aero.alestis.stresstools.materials.IFastenerMaterial;

public class BoltGroupExcelParser {
	private	FileInputStream file = null;

	private String path;
	public BoltGroupExcelParser(String path) {
		this.setPath(path);
	}
	
	public void parse(BoltGroupAnalysis analysis) throws IOException {
		file = new FileInputStream(new File(path));
		HSSFWorkbook workbook = null;
		workbook = new HSSFWorkbook(file);

		parseBoltGeometry(workbook, analysis);
		parseMaterials(workbook, analysis);
		//parseBoltMaterial()
		//parseLoadCases(workbook, analysis);
		file.close();
	}
	


	private void parseMaterials(HSSFWorkbook workbook, BoltGroupAnalysis analysis) {
		HSSFSheet sheet = workbook.getSheet("FASTENER_TYPES");
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		Map<String, IFastenerMaterial> materialsFromExcel = new HashMap<String,IFastenerMaterial>();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();		
			if (    row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING && 
					row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC && 
					row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if(!materialsFromExcel.containsKey(row.getCell(0).getStringCellValue())) {
					materialsFromExcel.put(row.getCell(0).getStringCellValue(), 
							new FastenerMaterial(row.getCell(0).getStringCellValue(), 
									             row.getCell(1).getNumericCellValue(),
									             row.getCell(2).getNumericCellValue()));
					System.out.println("MATERIAL PARA EL MAP");

				}
			}
			else {
				
			}
			
			
		}
		
		
		
		analysis.setMaterialsMap(materialsFromExcel);
	}

	@SuppressWarnings("unused")
	private void parseLoadCases(HSSFWorkbook workbook, BoltGroupAnalysis analysis)  throws IOException{
		HSSFSheet sheet = workbook.getSheet("LOAD-CASES");
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		GeneralPoint point = new GeneralPoint();
		BoltGroupLoadCase bglc = new BoltGroupLoadCase();
		Fastener fastener = new Fastener();
		List<Fastener> fastenerList =new ArrayList<Fastener>();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();			
			
			if (    row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING && 
					row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC && 
					row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC && 
					row.getCell(5).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(6).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC && 
					row.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(9).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				
				System.out.println(row.getCell(0).getStringCellValue());
				//point.setPointID("ddddd");
				//point.setPointID(row.getCell(0).getStringCellValue());
				point.setPunto(new Vector3D(row.getCell(1).getNumericCellValue(),
						                    row.getCell(2).getNumericCellValue(),
						                    row.getCell(3).getNumericCellValue()));
				
				fastener.setFastenerLocation(point);
				//fastener.setFastenerID(row.getCell(4).getStringCellValue());
				
				fastenerList.add(fastener);
				
			}
			else {
				
			}

		}
			
	//	file.close();
		
		
		
		analysis.setFastenerGeometry(fastenerList);		
	}

	private void parseBoltGeometry(HSSFWorkbook workbook, BoltGroupAnalysis analysis) throws IOException {
		HSSFSheet sheet = workbook.getSheet("GEOMETRY");
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		GeneralPoint point = new GeneralPoint();
		Fastener fastener = new Fastener();
		List<Fastener> fastenerList =new ArrayList<Fastener>();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();			
			
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING && 
					row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC && 
					row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC &&
					row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
				
				System.out.println(row.getCell(0).getStringCellValue());
				//point.setPointID("ddddd");
				//point.setPointID(row.getCell(0).getStringCellValue());
				//fastener.set
				point.setPointID(row.getCell(0).getStringCellValue());
				point.setPunto(new Vector3D(row.getCell(1).getNumericCellValue(),
						                    row.getCell(2).getNumericCellValue(),
						                    row.getCell(3).getNumericCellValue()));
				
				fastener.setFastenerLocation(point);
				fastener.setFastenerType(row.getCell(4).getStringCellValue());
				
				fastenerList.add(fastener);
				
			}
			else {
				
			}

		}
			
		//file.close();
		
		analysis.setFastenerGeometry(fastenerList);
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
}
