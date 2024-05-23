package org.shevliakov.collegeaccounting.appcore.util;

import java.util.Calendar;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.exception.AccountingCategoryCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.BirthDateCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.DegreeCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.FullNameCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.IdInfoCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.MilitarySpecialtyCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.RankCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.RegistrationNumberCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.TrainingCanNotBeEmpty;

public class CheckEmployeeInfo {

  public Boolean check(Employee employee) {
    if (employee.getFullName().isEmpty() || employee.getFullName().isBlank()) {
      new FullNameCanNotBeEmpty("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    if (employee.getRank() == null) {
      new RankCanNotBeEmpty("Звання не може бути пустим").showAlert();
      return false;
    }
    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Calendar employeeCalendar = Calendar.getInstance();
    employeeCalendar.setTime(employee.getBirthDate());
    Integer employeeBirtYear = employeeCalendar.get(Calendar.YEAR);
    if ((currentYear - employeeBirtYear) < 18) {
      new BirthDateCanNotBeEmpty("Вік не може бути нижче 18").showAlert();
      return false;
    }
    if (employee.getRegistrationNumber().isEmpty() || employee.getRegistrationNumber().isBlank()) {
      new RegistrationNumberCanNotBeEmpty(
          "Обліковий номер/РНОКПП не може бути пустим").showAlert();
      return false;
    }
    if (employee.getMilitarySpecialty().isEmpty() || employee.getMilitarySpecialty().isBlank()) {
      new MilitarySpecialtyCanNotBeEmpty(
          "Військово-облікова спеціальність не може бути пустою").showAlert();
      return false;
    }
    try {
      Integer.parseInt(employee.getMilitarySpecialty());
    } catch (NumberFormatException e) {
      new MilitarySpecialtyCanNotBeEmpty(
          "Військово-облікова спеціальність повинна бути числом").showAlert();
      return false;
    }
    if (employee.getTraining() == null) {
      new TrainingCanNotBeEmpty("Склад підготовки не може бути пустим").showAlert();
      return false;
    }
    if (employee.getAccountingCategory().isEmpty() || employee.getAccountingCategory().isBlank()) {
      new AccountingCategoryCanNotBeEmpty("Категорія обліку не може бути пустою").showAlert();
      return false;
    }
    if (employee.getDegree().isEmpty() || employee.getDegree().isBlank()) {
      new DegreeCanNotBeEmpty("Освіта не може бути пустою").showAlert();
      return false;
    }
    if (employee.getIdInfo().isEmpty() || employee.getIdInfo().isBlank()) {
      new IdInfoCanNotBeEmpty("Реквізити паспорта України не можуть бути пустими").showAlert();
      return false;
    }
    return true;
  }
}
