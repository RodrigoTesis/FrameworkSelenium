package com.tsoftlatam.automatizacion.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;

	public static ExtentReports getExtent() {
		if (extent != null) {
			return extent;
		}
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		htmlReporter = new ExtentHtmlReporter(Constantes.REPORT_PATH + "/index.html");
		htmlReporter.config().setChartVisibilityOnOpen(true);

		htmlReporter.config().setDocumentTitle("QAV automation report");
		htmlReporter.config().setReportName("Regression cycle");
		return htmlReporter;
	}

	public static ExtentTest createTest(String nombre, String descripcion) {
		test = extent.createTest(nombre, descripcion);
		return test;
	}
}
