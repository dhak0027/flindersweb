package com.flinders.poc.service;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.constant.AppConstants;

/**
 * Export Service interface
 * @author mswahithali
 *
 */

public interface ExportService {
	String getSheetName();
	void setSheetName(String sheetName);
	String[] getHeader();
	void setHeader(String[] header);
	Object getData();
	void setData(Object data);
	void updateWorksheet(SXSSFSheet sheet);
	
	Logger log = Logger.getLogger(ExportService.class);
	
	default public void export(HttpServletResponse response, String exportFilename) throws AppException{
		SXSSFWorkbook workbook = createWorkbook();
		OutputStream outputStream = null;
		try {
			// MIME type of the file
//			response.setContentType("application/octet-stream");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			
			// Response header
			response.setHeader("Content-Disposition", "attachment; filename=\""+exportFilename+"\"");
			// Read from the file and write into the response
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			
			
		} catch (Exception e) {
			log.error("exportVocabularyByCriteria", new AppException("EXPORTERR", "Error export vocabulary", e));
			throw new AppException("EXPORTERR", "Error export vocabulary", e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}

				if (workbook != null) {
					workbook.close();
				}
			} catch (Exception e2) {
				log.error("exportVocabularyByCriteria", new AppException("EXPORTERR", "Error export vocabulary", e2));
				throw new AppException("EXPORTERR", "Error export vocabulary", e2);
			}
		}
	}
	
	default SXSSFWorkbook createWorkbook() {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
    	SXSSFSheet sheet = workbook.createSheet(getSheetName() != null ? getSheetName() : "Sheet1");
    	updateHeader(sheet);
    	updateWorksheet(sheet);
    	sheet.trackAllColumnsForAutoSizing();
    	int count = 0; 
    	while (count <= getHeader().length) {
			sheet.autoSizeColumn(count);
			count++;
		}
		return workbook;
	}
	
	default void updateHeader(SXSSFSheet sheet){
		int colNum = 0;
		Row row = sheet.createRow(0);
		for (String headerCol : getHeader()) {
			Cell cell = row.createCell(colNum);
			cell.setCellValue(headerCol);
			cell.setCellStyle(getHeaderStyle(sheet));
			colNum++;
		}
	}
	
	default  CellStyle getHeaderStyle(SXSSFSheet sheet){
		Font font = createFont(sheet.getWorkbook(), HSSFColorPredefined.WHITE.getIndex(), (short)12, true);
		return createStyle(sheet.getWorkbook(), font,  HorizontalAlignment.CENTER, HSSFColorPredefined.LIGHT_BLUE.getIndex(), true, HSSFColorPredefined.GREY_80_PERCENT.getIndex());
	}
	
	
	default CellStyle getContentStyle(SXSSFSheet sheet){
		Font font = createFont(sheet.getWorkbook(), HSSFColorPredefined.BLACK.getIndex(), (short)10, true);
		return createStyle(sheet.getWorkbook(), font,  HorizontalAlignment.LEFT, HSSFColorPredefined.WHITE.getIndex(), true, HSSFColorPredefined.GREY_80_PERCENT.getIndex());
	}
	
	default CellStyle getContentStyleWithDateFormat(SXSSFSheet sheet){
		Font font = createFont(sheet.getWorkbook(), HSSFColorPredefined.BLACK.getIndex(), (short)10, true);
		CellStyle cellStyle = createStyle(sheet.getWorkbook(), font,  HorizontalAlignment.LEFT, HSSFColorPredefined.WHITE.getIndex(), true, HSSFColorPredefined.GREY_80_PERCENT.getIndex());
		CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(AppConstants.dateTimeFormat));
		return cellStyle;
	}
	
	
	default Font createFont(SXSSFWorkbook workbook, short fontColor, short fontHeight, boolean fontBold) {
		Font font = workbook.createFont();
		font.setBold(fontBold);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
 		return font;
	}
	
	default CellStyle createStyle(SXSSFWorkbook workbook, Font font, HorizontalAlignment cellAlign, short cellColor, boolean cellBorder, short cellBorderColor) {
		 
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(cellAlign);
		style.setFillForegroundColor(cellColor);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (cellBorder) {
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
 
			style.setTopBorderColor(cellBorderColor);
			style.setLeftBorderColor(cellBorderColor);
			style.setRightBorderColor(cellBorderColor);
			style.setBottomBorderColor(cellBorderColor);
		}
 
		
		return style;
	}
	
}
