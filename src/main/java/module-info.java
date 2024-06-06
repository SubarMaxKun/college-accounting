open module org.shevliakov.collegeaccounting {
  requires jakarta.persistence;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires lombok;
  requires org.apache.poi.ooxml;
  requires org.apache.poi.poi;
  requires spring.context;
  requires spring.data.jpa;
  requires spring.jdbc;
  requires spring.orm;
  requires spring.tx;

  exports org.shevliakov.collegeaccounting;
}