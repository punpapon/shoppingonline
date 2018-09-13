package com.somapait.shoppingonline.core.config.xml.service;

import com.somapait.shoppingonline.core.config.xml.domain.Parameter;

import util.log.LogUtil;

public class XMLManager {

	public static final String COMMA = ",";
	public static final String XML_PATH = System.getProperty("user.dir") + "/WebContent/WEB-INF/parameter.xml";

	private XMLService service = new XMLService();

	public void create() throws Exception {
		LogUtil.INITIAL.info("");
		try {
			service.create(XML_PATH);
		} catch (Exception e) {
			throw e;
		}
	}

	public Parameter load() throws Exception {
		LogUtil.INITIAL.info("");
		Parameter parameter = null;
		try {
			parameter = service.load(XML_PATH);
		} catch (Exception e) {
			throw e;
		}
		return parameter;
	}

	public Parameter load(String xml_path) throws Exception {
		LogUtil.INITIAL.info("");
		Parameter parameter = null;
		try {
			parameter = service.load(xml_path);
		} catch (Exception e) {
			throw e;
		}
		return parameter;
	}

	public static void main(String[] args) {
		XMLManager m = new XMLManager();
		try {
			m.create();
			m.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
