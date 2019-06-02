package com.flinders.poc.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * Export Service implementation
 * 
 * @author mswahithali
 *
 */
@Log4j(topic = "flinderswebLogger")
@Service("exportRuleService")
public class ExportRuleServiceImpl implements ExportService {

	@Getter
	@Setter
	private String sheetName;

	@Getter
	@Setter
	private String[] header;

	@Getter
	@Setter
	private Object data;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("policyDeviationService") private PolicyDeviationService
	 * policyDeviationService;
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void updateWorksheet(SXSSFSheet sheet) {
		AtomicInteger rowCounter = new AtomicInteger(1);

		/*
		 * List<PolicyRuleDTO> ruleList = (List<PolicyRuleDTO>) getData();
		 * ruleList.stream().forEach(dto -> { int colNum = 0; Row row =
		 * sheet.createRow(rowCounter.getAndIncrement()); Cell cell =
		 * row.createCell(colNum++); cell.setCellValue(dto.getRuleCode());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getRuleDesc());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getProdGrp());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getProdType());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getCondRel());
		 * cell.setCellStyle(getContentStyle(sheet));
		 * 
		 * cell = row.createCell(colNum++); cell.setCellValue(dto.getFailAction());
		 * cell.setCellStyle(getContentStyle(sheet));
		 * 
		 * cell = row.createCell(colNum++); if (dto.getDeviationId() != null &&
		 * dto.getDeviationId() > 0) { try { //PolicyDeviationDTO deviationDTO =
		 * policyDeviationService.getPolicyDeviationById(dto.getDeviationId());
		 * PolicyDeviationDTO deviationDTO = null;
		 * cell.setCellValue(deviationDTO.getDeviationLabel()); } //catch (AppException
		 * e) { catch (Exception e) { log.error("Error Getting Deviation", e); } } else
		 * { cell.setCellValue(""); } cell.setCellStyle(getContentStyle(sheet));
		 * 
		 * cell = row.createCell(colNum++); cell.setCellValue(dto.getCreatedBy());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getCreationDate());
		 * cell.setCellStyle(getContentStyleWithDateFormat(sheet)); cell =
		 * row.createCell(colNum++); cell.setCellValue(dto.getModifiedBy());
		 * cell.setCellStyle(getContentStyle(sheet)); cell = row.createCell(colNum++);
		 * cell.setCellValue(dto.getModificationDate());
		 * cell.setCellStyle(getContentStyleWithDateFormat(sheet)); });
		 */
	}

}
