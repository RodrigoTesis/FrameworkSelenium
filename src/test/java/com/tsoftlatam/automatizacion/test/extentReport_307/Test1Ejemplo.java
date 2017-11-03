package com.tsoftlatam.automatizacion.test.extentReport_307;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tsoftlatam.automatizacion.helpers.NavegadorDriver;

@Test
public class Test1Ejemplo extends BaseTest {

	public void paso1() throws InterruptedException, IOException {
		NavegadorDriver.driver.get("http://www.google.cl");
		childTest.get().pass("Paso");
		Thread.sleep(2000);

		// childTest.get().addScreenCaptureFromPath(crearScreenShot(NavegadorDriver.driver));
	}

	public void paso2() throws InterruptedException, IOException {

		childTest.get().fail(
				"ESTO HA FALLADO " + childTest.get().addScreenCaptureFromPath(crearScreenShot(NavegadorDriver.driver)));
		// childTest.get().addScreenCaptureFromPath(crearScreenShot(NavegadorDriver.driver));
		Assert.fail();

	}
}
