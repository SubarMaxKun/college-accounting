package org.shevliakov.collegeaccounting.appcore.util;

import java.util.Calendar;
import org.shevliakov.collegeaccounting.entity.Student;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.shevliakov.collegeaccounting.exception.FieldEmptyNullPointerException;

public class CheckStudentInfo {

  public boolean check(Student student) {
    if (student.getFullName().isEmpty()) {
      new FieldEmptyIllegalArgumentException("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Calendar studentCalendar = Calendar.getInstance();
    studentCalendar.setTime(student.getBirthDate());
    Integer studentBirtYear = studentCalendar.get(Calendar.YEAR);
    if ((currentYear - studentBirtYear) < 16) {
      new FieldEmptyNullPointerException("Вік не може бути нижче 16").showAlert();
      return false;
    }
    if (student.getGroup() == null) {
      new FieldEmptyIllegalArgumentException("Група не може бути пустою").showAlert();
      return false;
    }
    if (student.getMilitaryDocument().isEmpty() || student.getMilitaryDocument().isBlank()) {
      new FieldEmptyIllegalArgumentException("Військово-обліковий документ не може бути пустим").showAlert();
      return false;
    }
    if (student.getTaxCardNumber().isEmpty() || student.getTaxCardNumber().isBlank()) {
      new FieldEmptyIllegalArgumentException("РНОКПП не може бути пустим").showAlert();
      return false;
    }
    if (student.getTckName().isEmpty() || student.getTckName().isBlank()) {
      new FieldEmptyIllegalArgumentException("Найменування РТЦК не може бути пустим").showAlert();
      return false;
    }
    if (student.getAddress().isEmpty() || student.getAddress().isBlank()) {
      new FieldEmptyIllegalArgumentException("Адреса не може бути пустою").showAlert();
      return false;
    }
    return true;
  }
}
