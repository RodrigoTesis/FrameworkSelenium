package com.tsoftlatam.automatizacion.test.extentReport_2412;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tsoftlatam.automatizacion.helpers.Constantes;
import com.tsoftlatam.automatizacion.helpers.NavegadorDriver;

public abstract class BaseTest {

	static final Logger LOG = LogManager.getLogger(BaseTest.class);
	static ExtentReports extent;
	static ExtentTest parentTest;
	static ExtentTest childTest;
	String nombreTest;

	@BeforeSuite
	public void beforeSuite() {
		extent = new ExtentReports(Constantes.REPORT_PATH + "/index.html");
	}

	@BeforeClass
	@Parameters("browser")
	public synchronized void setup(String browser) {
		parentTest = extent.startTest(this.getClass().getSimpleName());
		NavegadorDriver.setUp(browser);
		NavegadorDriver.driver.get("http://opensource.demo.orangehrmlive.com/");
	}

	@BeforeMethod
	public void obtenerNombreDeLosMetodos(Method testMethod) {
		nombreTest = testMethod.getName();
	}

	@AfterMethod
	public void afterMethod() {
		parentTest.appendChild(childTest);
		extent.endTest(parentTest);
	}

	@AfterClass
	public void tearDown() {
		NavegadorDriver.driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		extent.close();
		// NavegadorDriver.driver.quit();
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

	public static void generarStepFail(String msgError, LogStatus status) {
		childTest.log(status, msgError + "\n" + childTest.addScreenCapture(crearScreenShot(NavegadorDriver.driver)));
	}

	public static void generarStepExito(String msgExito) {
		childTest.log(LogStatus.PASS, msgExito);
	}
}
