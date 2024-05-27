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

public class ExportStudentsToExcel {

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

    int rownum = 0;
    for (Student student : students) {
      Row row = sheet.createRow(rownum++);
      Object[] objArr = new Object[]{
          student.getFullName(),
          student.getGroup().getCode(),
          student.getBirthDate().toString(),
          student.getAddress()
      };
      int cellnum = 0;
      for (Object obj : objArr) {
        Cell cell = row.createCell(cellnum++);
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
