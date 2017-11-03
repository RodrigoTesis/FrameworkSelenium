package com.tsoftlatam.automatizacion.test.extentReport_307;

import java.io.IOException;

import org.testng.annotations.Test;

import com.tsoftlatam.automatizacion.helpers.NavegadorDriver;

public class Test2Ejemplo2 extends BaseTest {

	@Test
	public void paso1() throws InterruptedException, IOException {
		NavegadorDriver.driver.get("http://www.google.cl");
		childTest.get().pass("Paso");
		Thread.sleep(2000);
	}

	@Test
	public void paso2() throws InterruptedException, IOException {
		childTest.get().fail("ESTO HA FALLADO");
		childTest.get().addScreenCaptureFromPath(crearScreenShot(NavegadorDriver.driver));

	}
}
