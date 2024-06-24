package org.shevliakov.collegeaccounting.appcore.util;

import java.util.Calendar;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.shevliakov.collegeaccounting.exception.FieldEmptyNullPointerException;

public class CheckEmployeeInfo {

  public Boolean check(Employee employee) {
    if (employee.getFullName().isEmpty() || employee.getFullName().isBlank()) {
      new FieldEmptyIllegalArgumentException("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    if (employee.getRank() == null) {
      new FieldEmptyIllegalArgumentException("Звання не може бути пустим").showAlert();
      return false;
    }
    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Calendar employeeCalendar = Calendar.getInstance();
    employeeCalendar.setTime(employee.getBirthDate());
    Integer employeeBirtYear = employeeCalendar.get(Calendar.YEAR);
    if ((currentYear - employeeBirtYear) < 18) {
      new FieldEmptyNullPointerException("Вік не може бути нижче 18").showAlert();
      return false;
    }
    if (employee.getAddressOfLiving().isEmpty() || employee.getAddressOfLiving().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Фактична адреса проживання не може бути порожньою").showAlert();
      return false;
    }
    if (employee.getAddressOfRegistration().isEmpty() || employee.getAddressOfRegistration().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Адреса прописки не може бути порожньою").showAlert();
      return false;
    }
    if (employee.getFamily().isEmpty() || employee.getFamily().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Сімейний стан не може бути порожнім").showAlert();
      return false;
    }
    if (employee.getTckName().isEmpty() || employee.getTckName().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Найменування РТЦК та СП не може бути порожнім").showAlert();
      return false;
    }
    if (employee.getJobInfo().isEmpty() || employee.getJobInfo().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Інформація про посаду та реквізити не може бути порожньою").showAlert();
      return false;
    }
    if (employee.getRegistrationNumber().isEmpty() || employee.getRegistrationNumber().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Обліковий номер/РНОКПП не може бути пустим").showAlert();
      return false;
    }
    if (employee.getMilitarySpecialty().isEmpty() || employee.getMilitarySpecialty().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Військово-облікова спеціальність не може бути пустою").showAlert();
      return false;
    }
    try {
      Integer.parseInt(employee.getMilitarySpecialty());
    } catch (NumberFormatException e) {
      new FieldEmptyIllegalArgumentException(
          "Військово-облікова спеціальність повинна бути числом").showAlert();
      return false;
    }
    if (employee.getTraining() == null) {
      new FieldEmptyIllegalArgumentException("Склад підготовки не може бути пустим").showAlert();
      return false;
    }
    if (employee.getAccountingCategory().isEmpty() || employee.getAccountingCategory().isBlank()) {
      new FieldEmptyIllegalArgumentException("Категорія обліку не може бути пустою").showAlert();
      return false;
    }
    if (employee.getDegree().isEmpty() || employee.getDegree().isBlank()) {
      new FieldEmptyIllegalArgumentException("Освіта не може бути пустою").showAlert();
      return false;
    }
    if (employee.getIdInfo().isEmpty() || employee.getIdInfo().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Реквізити паспорта України не можуть бути пустими").showAlert();
      return false;
    }
    return true;
  }
}
