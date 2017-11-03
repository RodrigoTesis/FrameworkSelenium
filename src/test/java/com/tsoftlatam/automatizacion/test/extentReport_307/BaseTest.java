package com.tsoftlatam.automatizacion.test.extentReport_307;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.tsoftlatam.automatizacion.helpers.Constantes;
import com.tsoftlatam.automatizacion.helpers.ExtentReportManager;
import com.tsoftlatam.automatizacion.helpers.NavegadorDriver;

public abstract class BaseTest {

	private static final Logger LOG = LogManager.getLogger(BaseTest.class);
	static ExtentReports extent;
	static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();
	static ThreadLocal<ExtentTest> childTest = new ThreadLocal();
	String nombreTest;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentReportManager.getExtent();
	}

	@BeforeClass
	@Parameters("browser")
	public synchronized void beforeClass(String browser) {
		ExtentTest parent = extent.createTest(this.getClass().getSimpleName());
		parentTest.set(parent);
		NavegadorDriver.setUp(browser);
	}

	@BeforeMethod
	public synchronized void beforeMethod(Method method) {
		ExtentTest child = parentTest.get().createNode(method.getName());
		childTest.set(child);
	}

	@AfterMethod
	public synchronized void afterMethod() {
		extent.flush();
	}

	// @AfterMethod
	// public synchronized void afterMethod(ITestResult result) {
	// if (result.getStatus() == ITestResult.FAILURE)
	// childTest.get().fail(result.getThrowable());
	// else if (result.getStatus() == ITestResult.SKIP)
	// childTest.get().skip(result.getThrowable());
	// else
	// childTest.get().pass("Test passed");
	//
	// extent.flush();
	// }

	@AfterClass
	public void tearDown() {
		NavegadorDriver.driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		// extent.flush();
	}

	public static String crearScreenShot(WebDriver driver) {
		UUID uuid = UUID.randomUUID();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(Constantes.REPORT_PATH + "/evidencia/" + uuid + ".png"));
		} catch (IOException e) {
			LOG.error("ERROR - No ha sido posible generar la evidencia en extends", e.getMessage());
		}
		return "evidencia/" + uuid + ".png";
	}
}
