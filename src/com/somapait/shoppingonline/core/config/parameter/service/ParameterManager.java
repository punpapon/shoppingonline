package com.somapait.shoppingonline.core.config.parameter.service;

import java.util.HashMap;
import java.util.Map;

import com.somapait.shoppingonline.core.config.parameter.domain.ApplicationConfig;
import com.somapait.shoppingonline.core.config.parameter.domain.DatabaseConfig;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;
import com.somapait.shoppingonline.core.config.xml.domain.Database;
import com.somapait.shoppingonline.core.config.xml.domain.Parameter;
import com.somapait.shoppingonline.core.config.xml.service.XMLManager;

import util.log.LogUtil;

public class ParameterManager {

	public void init(String xmlPath) throws Exception {

		try {
			Parameter parameter = new XMLManager().load(xmlPath);

			ApplicationConfig application = new ApplicationConfig(parameter.getApplication());

			Map<String, DatabaseConfig> databaseConfs = new HashMap<String, DatabaseConfig>();
			for (int i = 0; i < parameter.getDatabase().length; i++) {
				Database database = parameter.getDatabase()[i];

				LogUtil.INITIAL.debug(i + ":- " + i + " " + database.isJndi() + " " + database.getLookup() + " " + database.getSchemas());

				Map<String, String> schemaMap = new HashMap<String, String>();
				String[] schemaArr = database.getSchemas().split(",");
				for (String schemaStr : schemaArr) {
					String[] schemaVal = schemaStr.split(":");
					schemaMap.put(schemaVal[0], schemaVal[1]);
				}

				DatabaseConfig databaseConf = new DatabaseConfig(database);
				databaseConf.setIndex(String.valueOf(i));
				databaseConf.setSchema(schemaMap);

				databaseConfs.put(databaseConf.getIndex(), databaseConf);
			}
			LogUtil.INITIAL.debug("DB Config :- [ok]");

			ParameterConfig.setApplication(application);
			ParameterConfig.setDatabases(databaseConfs);
			ParameterConfig.setParameter(parameter);

		} catch (Exception e) {
			throw e;
		}
	}
}
