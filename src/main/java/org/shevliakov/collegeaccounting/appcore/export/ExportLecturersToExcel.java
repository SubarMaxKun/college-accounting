package org.shevliakov.collegeaccounting.appcore.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.shevliakov.collegeaccounting.entity.Lecturer;

public class ExportLecturersToExcel {

  public void export(List<Lecturer> lecturers) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Зберегти файл");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile == null) {
      return;
    }

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Облік викладачів");

    // Create the header row
    Row headerRow = sheet.createRow(0);
    String[] headers = {"ПІБ", "Посада", "Кваліфікаційна категорія", "Педагогічне звання",
        "Рік попередньої атестації", "Рік наступної атестації",
        "Рік, кількість годин"};

    // Populate the header row
    int cellNum = 0;
    for (String header : headers) {
      Cell cell = headerRow.createCell(cellNum++);
      cell.setCellValue(header);
    }

    // Populate employee data
    int rownum = 1; // Start from the second row (after the header)
    for (Lecturer lecturer : lecturers) {
      Row row = sheet.createRow(rownum++);
      Object[] objArr = new Object[]{lecturer.getFullName(), lecturer.getPosition(),
          lecturer.getCategory().getName(), lecturer.getTitle().getName(),
          lecturer.getLastCertification(), lecturer.getNextCertification(), lecturer.getHours()};
      cellNum = 0;
      for (Object obj : objArr) {
        Cell cell = row.createCell(cellNum++);
        cell.setCellValue(obj.toString());
      }
    }

    try {
      FileOutputStream out = new FileOutputStream(selectedFile);
      workbook.write(out);
      out.close();
      System.out.println(selectedFile.getName() + " written successfully on disk.");
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Помилка");
      alert.setHeaderText("Помилка при збереженні файлу");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }
  }

}
