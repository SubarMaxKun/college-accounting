package org.shevliakov.collegeaccounting.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.shevliakov.collegeaccounting.entity.Student;

public class Bla {
  public void addEditButtonsToTable(TableColumn<Student, Button> editColumn) {
    Callback<TableColumn<Student, Button>, TableCell<Student, Button>> cellFactory = param -> new TableCell<>() {
      final Button btn = new Button("Edit");

      @Override
      public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
          setText(null);
        } else {
          btn.setOnAction(event -> {
            Student student = getTableView().getItems().get(getIndex());
            System.out.println(student);
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    editColumn.setCellFactory(cellFactory);
  }
}
