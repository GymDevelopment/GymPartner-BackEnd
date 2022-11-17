package com.gympartner.util;

import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Client;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssignedRoutineExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<AssignedRoutine> assignedRoutines;
    public AssignedRoutineExcelExporter (List<AssignedRoutine> assignedRoutines) {
        this.assignedRoutines = assignedRoutines;
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
        createCell(row, 1, "RoutineName", style);
        createCell(row, 2, "ClientName", style);
        createCell(row, 3, "CoachName", style);
        createCell(row, 4, "Done", style);
        createCell(row, 5, "Duration", style);
        createCell(row, 6, "Date", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);

    }


    private void writeDataLines() {

        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for( AssignedRoutine result: assignedRoutines) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getId()), style);
            createCell(row, columnCount++, result.getRoutine().getName(), style);
            createCell(row, columnCount++, result.getClient().getName(), style);
            createCell(row, columnCount++, result.getCoach().getName(), style);
            createCell(row, columnCount++, result.getDone(), style);
            createCell(row, columnCount++, result.getDuration(), style);
            createCell(row, columnCount, result.getDate(), style);
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
