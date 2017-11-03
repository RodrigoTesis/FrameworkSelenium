package com.tsoftlatam.automatizacion.test.extentReport_2412;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.tsoftlatam.automatizacion.helpers.Excel;
import com.tsoftlatam.automatizacion.helpers.NavegadorDriver;
import com.tsoftlatam.automatizacion.test.page.DashboardPage;
import com.tsoftlatam.automatizacion.test.page.LeaveListPage;
import com.tsoftlatam.automatizacion.test.page.LoginPage;

@Test
public class Test1Ejemplo extends BaseTest {

	public void paso1() {
		childTest = extent.startTest(nombreTest, "Ingresamos al login de la pagina");
		LoginPage loginPage = new LoginPage(NavegadorDriver.driver);
		StringBuilder msgError = new StringBuilder();
		try {
			if (loginPage.isElementPresent()) {
				List<List<String>> datos = Excel
						.getDatosHojasExcel("./src/test/resources/datapool/datos_de_prueba.xlsx", 0);
				DashboardPage dashboardPage = loginPage.login(datos.get(1).get(0), datos.get(1).get(1));
				if (dashboardPage.isElementPresent()) {
					assertTrue(true, "NO ESTAMOS EN LE DASHBOARD DE LA SHET");
				} else {
					msgError.append("NO LLEGAMOS AL DASHBOARD");
					throw new Exception(msgError.toString());
				}
			} else {
				msgError.append("NO LLEGAMOS AL LOGIN");
				throw new Exception(msgError.toString());
			}
			generarStepExito("TODO BIEN");
		} catch (Exception e) {
			LOG.fatal(e.getMessage());
			generarStepFail(msgError.toString(), LogStatus.FAIL);
			Assert.fail(msgError.toString());
		}
	}

	public void paso2() {
		childTest = extent.startTest(nombreTest, "Nos dirigimos al Leave List");
		DashboardPage dashboardPage = new DashboardPage(NavegadorDriver.driver);
		StringBuilder msgError = new StringBuilder();
		try {
			LeaveListPage leaveListPage = dashboardPage.clickLeavelistLink();
			if (leaveListPage.isElementPresent()) {
				Assert.assertTrue(true);
			} else {
				msgError.append("NO ESTAMOS EN LA PAGINA LEAVE LIST");
				throw new Exception(msgError.toString());
			}
			generarStepExito("TODO BIEN");
		} catch (Exception e) {
			LOG.fatal(e.getMessage());
			generarStepFail(msgError.toString(), LogStatus.FAIL);
			Assert.fail(msgError.toString());
		}

	}
}
