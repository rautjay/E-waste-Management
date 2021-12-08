package com.project.helper;

import java.io.IOException;
import java.util.Date;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import com.project.entities.Request;
 
public class RequestExportExcel {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private List<Request> RequestList;
     
  
 
    public RequestExportExcel(List<Request> requestList) {
		super();
		RequestList = requestList;
		workbook = new HSSFWorkbook(); 
				}

	private void writeHeaderLine() {
        sheet = workbook.createSheet("request");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        style = workbook.createCellStyle();
       style.setDataFormat(creationHelper.createDataFormat().getFormat("dddd dd/mm/yyyy"));
  
         
         HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 40);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
  
         
        createCell(row, 0, "RequestId", style);      
        createCell(row, 1, "mobile", style);       
        createCell(row, 2, "Date", style);    
        createCell(row, 3, "Status", style);
        createCell(row, 4, "flatno", style);
        createCell(row, 5, "streetname", style);
        createCell(row, 6, "landmark", style);
        createCell(row, 7, "city", style);
        createCell(row, 8, "state", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if (value instanceof Long) {
			
        	cell.setCellValue((Long) value);
		}else if (value instanceof Date) {
			
		
			cell.setCellValue((Date)value);
		}
      
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 25);
        style.setFont(font);
     
        
                 
        for (Request request : RequestList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++,request.getRequestId() , style);
            createCell(row, columnCount++, request.getNew_mobile(), style);
            createCell(row, columnCount++, request.getDate(), style);
            createCell(row, columnCount++, request.getStatus(), style);
            createCell(row, columnCount++, request.getUser().getUserAddress().getFlatno(), style);
            createCell(row, columnCount++, request.getUser().getUserAddress().getStreetname(), style);
            createCell(row, columnCount++, request.getUser().getUserAddress().getLandmark(), style);
            createCell(row, columnCount++, request.getUser().getUserAddress().getCity(), style);
            createCell(row, columnCount++, request.getUser().getState(), style);
            
            
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}



	
	
	

