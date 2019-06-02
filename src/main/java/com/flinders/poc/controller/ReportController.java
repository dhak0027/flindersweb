package com.flinders.poc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.flinders.poc.common.controller.BasicController;
import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.dto.AdminAuditLogDTO;
import com.flinders.poc.dto.CriteriaDTO;
import com.flinders.poc.dto.ReportDTO;
import com.flinders.poc.service.AdminAuditLogService;

import lombok.extern.log4j.Log4j;

/**
 * Report controller for report generation
 * @author mswahithali
 *
 */

@Log4j(topic = "flinderswebLogger")
@Controller
@RequestMapping("/report")
public class ReportController extends BasicController {
	
	private static final CellProcessor[] PROCESSORS = new CellProcessor[] { 
			new FmtDate(AppConstants.dateTimeFormat),
		    new ConvertNullTo(AppConstants.FLAG_NA),
		    null,
		    null,
		    null,
		    null,
		    null,
		    null};
	
	@Autowired
	@Qualifier("adminAuditLogService")
	private AdminAuditLogService adminAuditLogService;

	@InitBinder("report")
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.dateFormat_mmddyyyy_slash);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Override
	@ModelAttribute("report")
	public ReportDTO createNewModel() {
		return new ReportDTO();
	}
	
	@Override
	@ModelAttribute("criteria")
	public CriteriaDTO createNewCriteria() {
		return new CriteriaDTO();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initReportPage(Model model) throws AppException {	
		log.info("Report - SearchForm");
		createNewCriteria();
		return "report";
	}
	
	@RequestMapping(value = "/generateCSV", method = RequestMethod.POST)
	public void downloadCSV(HttpServletResponse response, @ModelAttribute("report") ReportDTO report) throws AppException {
		
		List<AdminAuditLogDTO> auditLogs = adminAuditLogService.getAuditLog(report.getReportType(), report.getReportPeriodFrom(), report.getReportPeriodTo());
		
		String csvFileName = report.getReportType() + ".csv";

		response.setContentType("text/csv");

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		try {
			// uses the Super CSV API to generate CSV data from the model data 
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
					CsvPreference.STANDARD_PREFERENCE);
			
			String[] header = { "CreationDate", "AppAcronym", "AdminType", "Action", "KeyValue", "MemberType", "MemberValue", "CreatedBy" };
	
			csvWriter.writeHeader(header);
	
			for (AdminAuditLogDTO auditLog : auditLogs) {
				csvWriter.write(auditLog, header, PROCESSORS);
			}
	
			csvWriter.close();
		} catch (Exception e) {
			log.error("downloadCSV", new AppException("REPORT", "Error generating CSV report", e));
			throw new AppException("REPORT", "Error generating CSV report", e);
		}
		
	}
	
}
