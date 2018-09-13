package com.somapait.shoppingonline.core.config.parameter.domain;

import java.util.Map;

import com.somapait.shoppingonline.core.config.xml.domain.Parameter;

public class ParameterConfig {

	private static ApplicationConfig application;
	private static Map<String, DatabaseConfig> databases;
	private static Parameter parameter;

	public static void setApplication(ApplicationConfig application) {
		ParameterConfig.application = application;
	}

	public static void setDatabases(Map<String, DatabaseConfig> databases) {
		ParameterConfig.databases = databases;
	}

	public static ApplicationConfig getApplication() {
		return application;
	}

	public static Map<String, DatabaseConfig> getDatabases() {
		return databases;
	}

	public static DatabaseConfig getDatabase(String index) {
		return databases.get(index);
	}

	public static Parameter getParameter() {
		return parameter;
	}

	public static void setParameter(Parameter parameter) {
		ParameterConfig.parameter = parameter;
	}
}
