package com.mycompany.student.clubs;

import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author eimwe
 */
public class ExcelExporter {
 
    public static void exportToExcel(JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }

        // Create data rows
        for (int row = 0; row < model.getRowCount(); row++) {
            Row dataRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = dataRow.createCell(col);
                cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
            }
        }
        
        

        // Create a File object with the export path
        File exportFile = new File(filePath);

        // Make sure the export directory exists, create it if not
        if (!exportFile.getParentFile().exists()) {
            exportFile.getParentFile().mkdirs();
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Export successful!", "Export to Excel", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error exporting to Excel", "Export to Excel", JOptionPane.ERROR_MESSAGE);
        }
    }

}
