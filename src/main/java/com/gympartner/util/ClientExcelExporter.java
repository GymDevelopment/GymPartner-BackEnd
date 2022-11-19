package com.gympartner.util;

import com.gympartner.entities.Client;
import com.gympartner.entities.Gym;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ClientExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Client> clients;
    public ClientExcelExporter (List<Client> clients) {
        this.clients = clients;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Result");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "LastName", style);
        createCell(row, 3, "Gym", style);
        createCell(row, 4, "Email", style);
        createCell(row, 5, "PersonalGoal", style);
        createCell(row, 6, "Birthday", style);
        createCell(row, 7, "PhysicalState", style);
        createCell(row, 8, "Tall", style);
        createCell(row, 9, "Weight", style);
        createCell(row, 10, "Coach", style);
        createCell(row, 11, "Phone", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }  else if(value instanceof String) {
            cell.setCellValue((String) value);
        } else {
            cell.setCellValue(value.toString());
        }

        cell.setCellStyle(style);

    }


    private void writeDataLines() {

        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Client result: clients) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getId()), style);
            createCell(row, columnCount++, result.getName(), style);
            createCell(row, columnCount++, result.getLastName(), style);
            createCell(row, columnCount++, result.getGym().getName(), style);
            createCell(row, columnCount++, result.getEmail(), style);
            createCell(row, columnCount++, result.getPersonalGoal(), style);
            createCell(row, columnCount++, result.getBirthday(), style);
            createCell(row, columnCount++, result.getPhysicalState(), style);
            createCell(row, columnCount++, result.getTall(), style);
            createCell(row, columnCount++, result.getWeight(), style);
            createCell(row, columnCount++, result.getCoach().getName(), style);
            createCell(row, columnCount, result.getPhone(), style);

        }
    }


    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine(); //write the header
        writeDataLines(); //write the data

        ServletOutputStream servletOutput = response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();

        servletOutput.close();
    }
}
