package com.flinders.poc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.flinders.poc.constant.AppConstants;

/**
 * Spring Boot Application Configuration
 * 
 * @author mswahithali
 */

@Configuration
@PropertySource("file:${APP_PROPS}/flindersweb.properties")
public class AppConfiguration
		extends WebMvcConfigurerAdapter {
	
	@Value("${ui.datetime.format}")
	private String uiDateTimeFormat;

	@Value("${ui.date.format}")
	private String uiDateFormat;

	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/**/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(AppConstants.MAX_UPLOAD_SIZE_IN_MB * 2);
		cmr.setMaxUploadSizePerFile(AppConstants.MAX_UPLOAD_SIZE_IN_MB);
		return cmr;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
