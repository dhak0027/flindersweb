package com.flinders.poc.dto;

import java.util.Date;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Report bean DTO
 * @author mswahithali
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO extends BasicDTO{

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter 
	private String reportType;
	
//	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Getter @Setter 
	private Date reportPeriodFrom;
	
//	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Getter @Setter 
	private Date reportPeriodTo;

}
