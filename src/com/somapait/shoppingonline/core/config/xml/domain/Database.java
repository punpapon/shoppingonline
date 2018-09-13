package com.somapait.shoppingonline.core.config.xml.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Database implements Serializable {

	private static final long serialVersionUID = -2642679205798150121L;

	private String lookup;
	private boolean jndi;
	private String schemas;

	public String getLookup() {
		return lookup;
	}

	@XmlElement
	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getSchemas() {
		return schemas;
	}

	@XmlElement
	public void setSchemas(String schemas) {
		this.schemas = schemas;
	}
	public boolean isJndi() {
		return jndi;
	}

	@XmlElement
	public void setJndi(boolean jndi) {
		this.jndi = jndi;
	}
}
