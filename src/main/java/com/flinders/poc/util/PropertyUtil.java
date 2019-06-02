package com.flinders.poc.util;

import java.util.ResourceBundle;

/**
 * Property  Utility
 * @author mswahithali
 */

public class PropertyUtil {

	public static PropertyUtil appProperty = null;
	ResourceBundle resourceBundle = ResourceBundle
			.getBundle("AppProperty");

	private PropertyUtil() {
	}

	public static PropertyUtil getInstance() {
		if (appProperty == null) {
			appProperty = new PropertyUtil();
		}

		return appProperty;
	}

	public String getPropValue(String propKey) {
		return resourceBundle.getString(propKey);
	}

}
