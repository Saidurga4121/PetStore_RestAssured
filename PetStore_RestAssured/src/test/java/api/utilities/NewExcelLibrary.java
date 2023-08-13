package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;




import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class NewExcelLibrary {

	    File f;
		FileOutputStream fos;
		FileInputStream fis;
		Sheet sh;
		Row row;
		Cell cell;
		Workbook wb;
		public CellStyle style; 
		String path=null;
		
		public NewExcelLibrary(String path)
		{
			this.path=path;
			
		}
		public int getRowCount(String SheetName) throws IOException {
			f= new File(path);
		    fis= new FileInputStream(f);
			wb= new XSSFWorkbook(fis);
			sh=wb.getSheet(SheetName);
			int rowCount=sh.getLastRowNum();
			//
			wb.close();
			fis.close();
			return rowCount;
		}
		public int getCellCount(String SheetName, int rowNum) throws IOException {
			f= new File(path);
		    fis= new FileInputStream(f);
			wb= new XSSFWorkbook(fis);
			sh=wb.getSheet(SheetName);
			row=sh.getRow(rowNum);
			int cellCount=row.getLastCellNum();
			//
			wb.close();
			fis.close();
			return cellCount;
		}
		public String getCellData(String SheetName, int rowNum, int cellNum) throws IOException {
			f= new File(path);
		    fis= new FileInputStream(f);
			wb= new XSSFWorkbook(fis);
			sh=wb.getSheet(SheetName);
			row=sh.getRow(rowNum);
			cell=row.getCell(cellNum);
			// String cellvalue=cell.getStringCellValue();
			
			DataFormatter formatter = new DataFormatter();
			String data;
			try {
			data=formatter.formatCellValue(cell);  // returns the formatted cell value as string regardless of value.
			}
			catch(Exception e){
				data="";
			}
			//
			wb.close();
			fis.close();
			return data;
		}
		public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
		{
			File xlfile=new File(path);
			if(!xlfile.exists())    // If file not exists then create new file
			{
			wb=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			wb.write(fos);
			}
					
			fis=new FileInputStream(path);
			wb=new XSSFWorkbook(fis);
				
			if(wb.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
				wb.createSheet(sheetName);
			sh=wb.getSheet(sheetName);
						
			if(sh.getRow(rownum)==null)   // If row not exists then create new Row
					sh.createRow(rownum);
			row=sh.getRow(rownum);
			
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fos=new FileOutputStream(path);
			wb.write(fos);		
			wb.close();
			fis.close();
			fos.close();
		}
		
		
		public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
		{
			fis=new FileInputStream(path);
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetName);
			
			row=sh.getRow(rownum);
			cell=row.getCell(colnum);
			
			style=wb.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
					
			cell.setCellStyle(style);
			wb.write(fos);
			wb.close();
			fis.close();
			fos.close();
		}
		
		
		public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
		{
			fis=new FileInputStream(path);
			wb=new XSSFWorkbook(fis);
			sh=wb.getSheet(sheetName);
			row=sh.getRow(rownum);
			cell=row.getCell(colnum);
			
			style=wb.createCellStyle();
			
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
			
			cell.setCellStyle(style);		
			wb.write(fos);
			wb.close();
			fis.close();
			fos.close();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	


