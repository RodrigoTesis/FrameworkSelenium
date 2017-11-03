package com.tsoftlatam.automatizacion.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class NavegadorDriver {
	public static WebDriver driver;
	private static final Logger LOG = LogManager.getLogger(NavegadorDriver.class);

	public static void setUp(String navegador) {
		try {
			if (navegador.equalsIgnoreCase("firefox") || navegador.equalsIgnoreCase("mozilla")) {
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/Mozilla/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (navegador.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/Chrome/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (navegador.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						"./src/test/resources/drivers/IExplorer/IEDriverServer_x32.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(capabilities);
			} else {
				LOG.error("ERROR - Opcion de navegador no valida");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOG.fatal("FATAL - No es posible instanciar el driver", e.getMessage());
		}

	}
}
