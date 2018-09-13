package com.somapait.shoppingonline.core.config.xml.service;

import util.log.LogUtil;
import util.xml.XMLUtil;

import com.somapait.shoppingonline.core.config.xml.domain.Application;
import com.somapait.shoppingonline.core.config.xml.domain.Database;
import com.somapait.shoppingonline.core.config.xml.domain.Parameter;

public class XMLService {

	protected void create(String filePath) throws Exception {
		try {

			Parameter parameter = new Parameter();

			Application application = new Application();
			application.setTitle("Shopping Online Web");
			application.setApplicationLocale("th");
			application.setDatabaseLocale("en");
			application.setDatabaseGoldenGateLocale("th");
			application.setLpp("10,20,30,40,50,60,70,80,90,100");
			application.setMaxExceed("10");
			application.setMaxExceedReport("5");
			application.setDefaultSecUserId("0");

			Database oracle_oc = new Database();
			oracle_oc.setLookup("PG_EXAM");
			oracle_oc.setJndi(true);
			oracle_oc.setSchemas("[PG_EXAM]:PG_EXAM_SHOPPING");

			Database[] databases = new Database[1];
			databases[0] = oracle_oc;

			parameter.setFilename(filePath);
			parameter.setApplication(application);
			parameter.setDatabase(databases);

			XMLUtil.objectToXML(filePath, parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	protected Parameter load(String filePath) throws Exception {
		Parameter parameter = new Parameter();
		try {
			LogUtil.INITIAL.debug("path :- " + filePath);
			parameter = (Parameter) XMLUtil.xmlToObject(filePath, parameter);

			LogUtil.INITIAL.debug("Application Locale :- " + parameter.getApplication().getApplicationLocale());
			LogUtil.INITIAL.debug("Database Locale :- " + parameter.getApplication().getDatabaseLocale());

		} catch (Exception e) {
			throw e;
		}
		return parameter;
	}
}
