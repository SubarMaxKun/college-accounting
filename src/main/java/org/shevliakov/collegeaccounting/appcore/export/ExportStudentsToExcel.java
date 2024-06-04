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
import org.shevliakov.collegeaccounting.entity.Student;

/**
 * Export the list of students to an Excel file.
 */
public class ExportStudentsToExcel {

  /**
   * Export the list of students to an Excel file.
   *
   * @param students the list of students to export
   */
  public void export(List<Student> students) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Зберегти файл");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile == null) {
      return;
    }

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Облік студентів");
    // Create the header row
    Row headerRow = sheet.createRow(0);
    String[] headers = {"ПІБ", "Група", "Дата народження", "Адреса проживання",
        "Стоїть на обліку у ТЦК", "Примітки"};

    // Populate the header row
    int cellNum = 0;
    for (String header : headers) {
      Cell cell = headerRow.createCell(cellNum++);
      cell.setCellValue(header);
    }
    // Populate employee data
    int rownum = 1; // Start from the second row (after the header)
    for (Student student : students) {
      Row row = sheet.createRow(rownum++);
      Object[] objArr = new Object[]{student.getFullName(), student.getGroup().getCode(),
          student.getBirthDate().toString(), student.getAddress(), student.getOnTck(),
          student.getNotes()};
      cellNum = 0;
      for (Object obj : objArr) {
        Cell cell = row.createCell(cellNum++);
        try {
          cell.setCellValue(obj.toString());
        } catch (NullPointerException e) {
          cell.setCellValue("");
        }
      }
    }
    // Write the workbook in file
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
