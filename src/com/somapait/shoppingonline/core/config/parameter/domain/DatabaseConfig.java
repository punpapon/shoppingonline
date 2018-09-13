package com.somapait.shoppingonline.core.config.parameter.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.somapait.shoppingonline.core.config.xml.domain.Database;

public class DatabaseConfig implements Serializable {

	private static final long serialVersionUID = 7188603063139359374L;

	private String index;
	private Database database;
	private Map<String, String> schemas = new HashMap<String, String>();

	public DatabaseConfig(Database database) {
		this.database = database;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Map<String, String> getSchemas() {
		return schemas;
	}

	public void setSchema(Map<String, String> schemas) {
		this.schemas = schemas;
	}

	public String getLookup() {
		return database.getLookup();
	}

	public boolean isJndi() {
		return database.isJndi();
	}
}
