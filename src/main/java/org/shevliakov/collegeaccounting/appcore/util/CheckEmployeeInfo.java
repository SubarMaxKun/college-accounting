package org.shevliakov.collegeaccounting.appcore.util;

import java.util.Calendar;
import java.util.Date;
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
    if (employee.getFullName().isEmpty()) {
      new FullNameCanNotBeEmpty("ПІБ не можуть бути пустими").showAllert();
      return false;
    }
    if (employee.getRank() == null) {
      new RankCanNotBeEmpty("Звання не може бути пустим").showAllert();
      return false;
    }
    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Calendar employeeCalendar = Calendar.getInstance();
    employeeCalendar.setTime(employee.getBirthDate());
    Integer employeeBirtYear = employeeCalendar.get(Calendar.YEAR);
    if ((currentYear - employeeBirtYear) < 18) {
      new BirthDateCanNotBeEmpty("Вік не може бути нижче 18").showAllert();
      return false;
    }
    if (employee.getRegistrationNumber().isEmpty()) {
      new RegistrationNumberCanNotBeEmpty(
          "Обліковий номер/РНОКПП не може бути пустим").showAllert();
      return false;
    }
    if (employee.getMilitarySpecialty().isEmpty()) {
      new MilitarySpecialtyCanNotBeEmpty(
          "Військово-облікова спеціальність не може бути пустою").showAllert();
      return false;
    }
    if (employee.getTraining() == null) {
      new TrainingCanNotBeEmpty("Склад підготовки не може бути пустим").showAllert();
      return false;
    }
    if (employee.getAccountingCategory().isEmpty()) {
      new AccountingCategoryCanNotBeEmpty("Категорія обліку не може бути пустою").showAllert();
      return false;
    }
    if (employee.getDegree().isEmpty()) {
      new DegreeCanNotBeEmpty("Освіта не може бути пустою").showAllert();
      return false;
    }
    if (employee.getIdInfo().isEmpty()) {
      new IdInfoCanNotBeEmpty("Реквізити паспорта України не можуть бути пустими").showAllert();
      return false;
    }
    return true;
  }
}
