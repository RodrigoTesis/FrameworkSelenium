package com.tsoftlatam.automatizacion.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private static final Logger LOG = LogManager.getLogger(Excel.class);

	private static Workbook getTipoWorkbook(String rutaExcel) {
		Workbook workbook = null;
		FileInputStream archivoExcel = null;
		try {
			archivoExcel = new FileInputStream(rutaExcel);
			if (rutaExcel.contains(".xlsx")) {
				workbook = new XSSFWorkbook(archivoExcel);
			} else if (rutaExcel.contains(".xls")) {
				workbook = new HSSFWorkbook(archivoExcel);
			} else {
				throw new IllegalArgumentException("El archivo agregado no es formato reconocido como Excel");
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return workbook;
	}

	public static List<List<String>> getDatosHojasExcel(String rutaExcel, int hoja) {
		List<List<String>> datosExcel = new ArrayList<List<String>>();
		Workbook libro = getTipoWorkbook(rutaExcel);
		DataFormatter formato = null;
		Sheet libroHoja = null;
		try {
			formato = new DataFormatter();
			libroHoja = libro.getSheetAt(hoja);
			Iterator<Row> filas = libroHoja.iterator();

			while (filas.hasNext()) {
				Row fila = filas.next();
				Iterator<Cell> columnas = fila.cellIterator();
				List<String> data = new ArrayList<String>();
				while (columnas.hasNext()) {
					Cell columna = columnas.next();
					data.add(formato.formatCellValue(columna));
				}
				datosExcel.add(data);
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			if (libro != null) {
				try {
					libro.close();
				} catch (IOException io) {
					LOG.error(io.getMessage());
				}
			}
		}
		return datosExcel;
	}
}
