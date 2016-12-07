package ArmyAnt.IO;
import java.io.File;
import java.io.IOException;

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelReader {

	public ExcelReader(String filepath) throws BiffException, IOException {
		// TODO Auto-generated constructor stub
		workbook = Workbook.getWorkbook(new File(filepath));
	}
	
	public String[] getAllSheets(){
		return workbook.getSheetNames();
	}

	public int getColumnNumber(String sheet){
		return workbook.getSheet(sheet).getColumns();
	}

	public int getColumnNumber(int sheetIndex){
		return workbook.getSheet(sheetIndex).getColumns();
	}
	
	public int getLinesNumber(String sheet){
		return workbook.getSheet(sheet).getRows();
	}
	
	public int getLinesNumber(int sheetIndex){
		return workbook.getSheet(sheetIndex).getRows();
	}
	
	public String getValue(String sheet, int line, int column){
		return workbook.getSheet(sheet).getCell(column, line).getContents();
	}
	
	public String getValue(int sheetIndex, int line, int column){
		return workbook.getSheet(sheetIndex).getCell(column, line).getContents();
	}
	
	private Workbook workbook = null;
}
