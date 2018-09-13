package com.somapait.shoppingonline.core.config.parameter.domain;

import java.io.Serializable;
import java.util.Locale;

import com.somapait.shoppingonline.core.config.xml.domain.Application;

public class ApplicationConfig implements Serializable {

	private static final long serialVersionUID = 3215828145433104054L;

	private Application application;
	private Locale applicationLocale;
	private Locale databaseGoldenGateLocale;
	private Locale databaseLocale;

	public ApplicationConfig(Application application) {
		this.application = application;
		init();
	}

	private void init() {
		String appLocaleString = application.getApplicationLocale();
		applicationLocale = new Locale(appLocaleString.toLowerCase(), appLocaleString.toUpperCase());

		String dbLocaleString = application.getDatabaseLocale();
		databaseLocale = new Locale(dbLocaleString.toLowerCase(), dbLocaleString.toUpperCase());

		String databaseGoldenGateString = application.getDatabaseGoldenGateLocale();
		databaseGoldenGateLocale = new Locale(databaseGoldenGateString.toLowerCase(), databaseGoldenGateString.toUpperCase());
	}

	public Locale getApplicationLocale() {
		return applicationLocale;
	}

	public Locale getDatabaseLocale() {
		return databaseLocale;
	}

	public String[] getLpp() {
		return application.getLpp().split(",");
	}

	public long getMaxExceed() {
		return Long.parseLong(application.getMaxExceed());
	}

	public long getMaxExceedReport() {
		return Long.parseLong(application.getMaxExceedReport());
	}

	public String getTitle() {
		return application.getTitle();
	}

	public String getDefaultSecUserId() {
		return application.getDefaultSecUserId();
	}

	public Locale getDatabaseGoldenGateLocale() {
		return databaseGoldenGateLocale;
	}
}