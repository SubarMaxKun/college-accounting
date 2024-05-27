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
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Student;

public class ExportEmployeesToExcel {

  public void export(List<Employee> employees) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Зберегти файл");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile == null) {
      return;
    }

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Облік працівників");

    int rownum = 0;
    for (Employee employee : employees) {
      Row row = sheet.createRow(rownum++);
      Object[] objArr = new Object[]{
          employee.getFullName(),
          employee.getRank().getName(),
          employee.getBirthDate().toString(),
          employee.getRegistrationNumber(),
          employee.getMilitarySpecialty(),
          employee.getTraining().getName(),
          employee.getAccountingCategory(),
          employee.getDegree(),
          employee.getIdInfo()
      };
      int cellNum = 0;
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
