package com.somapait.shoppingonline.core.config.xml.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameter implements Serializable {

	public ServletPath getServletPath() {
		return servletPath;
	}

	public void setServletPath(ServletPath servletPath) {
		this.servletPath = servletPath;
	}

	private static final long serialVersionUID = -578325849007499211L;

	// @XmlAttribute
	// @XmlElement
	private String filename;
	private Application application;
	private Database[] database;
	private ServletPath servletPath;


	public String getFilename() {
		return filename;
	}

	@XmlElement
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Application getApplication() {
		return application;
	}

	@XmlElement
	public void setApplication(Application application) {
		this.application = application;
	}

	public Database[] getDatabase() {
		return database;
	}

	@XmlElement
	public void setDatabase(Database[] database) {
		this.database = database;
	}

}
