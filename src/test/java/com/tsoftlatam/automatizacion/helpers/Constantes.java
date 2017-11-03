package com.tsoftlatam.automatizacion.helpers;

import java.util.Properties;

public class Constantes {
	private static LeerProperties propiedades = new LeerProperties();
	private static Properties properties = propiedades.getProperties("variables.properties");

	public static final String REPORT_PATH = properties.getProperty("extentPath");
}
