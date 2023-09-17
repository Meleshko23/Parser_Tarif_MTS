import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.MTSTariff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {


    public static void writeDataToExcel(List<MTSTariff> tariffs, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MTS Tariffs");

        // Создаем заголовки столбцов
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Description");
        headerRow.createCell(2).setCellValue("Price");
        headerRow.createCell(3).setCellValue("Options");

        // Записываем данные о тарифах
        int rowNum = 1;
        for (MTSTariff tariff : tariffs) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(tariff.getName());
            row.createCell(1).setCellValue(tariff.getDescription());
            row.createCell(2).setCellValue(tariff.getPrice());

            // Преобразуем список опций в одну строку для записи в ячейку
            StringBuilder optionsStringBuilder = new StringBuilder();
            for (String option : tariff.getOptions()) {
                optionsStringBuilder.append(option).append(", ");
            }
            String optionsString = optionsStringBuilder.toString();
            if (optionsString.endsWith(", ")) {
                optionsString = optionsString.substring(0, optionsString.length() - 2);
            }
            row.createCell(3).setCellValue(optionsString);
        }

        // Записываем данные в файл
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
    }
}
