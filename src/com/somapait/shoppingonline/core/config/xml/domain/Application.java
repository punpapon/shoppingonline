package com.somapait.shoppingonline.core.config.xml.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Application implements Serializable {

	private static final long serialVersionUID = -8328972062264715804L;

	private String title;
	private String applicationLocale;
	private String databaseLocale;
	private String databaseGoldenGateLocale;
	private String lpp;
	private String maxExceed;
	private String maxExceedReport;
	private String defaultSecUserId;

	public String getLpp() {
		return lpp;
	}

	@XmlElement
	public void setLpp(String lpp) {
		this.lpp = lpp;
	}

	public String getMaxExceed() {
		return maxExceed;
	}

	@XmlElement
	public void setMaxExceed(String maxExceed) {
		this.maxExceed = maxExceed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplicationLocale() {
		return applicationLocale;
	}

	@XmlElement
	public void setApplicationLocale(String applicationLocale) {
		this.applicationLocale = applicationLocale;
	}

	public String getDatabaseLocale() {
		return databaseLocale;
	}

	@XmlElement
	public void setDatabaseLocale(String databaseLocale) {
		this.databaseLocale = databaseLocale;
	}

	public String getMaxExceedReport() {
		return maxExceedReport;
	}

	@XmlElement
	public void setMaxExceedReport(String maxExceedReport) {
		this.maxExceedReport = maxExceedReport;
	}

	public String getDefaultSecUserId() {
		return defaultSecUserId;
	}

	@XmlElement
	public void setDefaultSecUserId(String defaultSecUserId) {
		this.defaultSecUserId = defaultSecUserId;
	}

	public String getDatabaseGoldenGateLocale() {
		return databaseGoldenGateLocale;
	}

	@XmlElement
	public void setDatabaseGoldenGateLocale(String databaseGoldenGateLocale) {
		this.databaseGoldenGateLocale = databaseGoldenGateLocale;
	}
}