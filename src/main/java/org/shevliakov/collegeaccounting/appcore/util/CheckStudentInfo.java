package org.shevliakov.collegeaccounting.appcore.util;

import java.util.Calendar;
import org.shevliakov.collegeaccounting.entity.Student;
import org.shevliakov.collegeaccounting.exception.AddressCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.BirthDateCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.FullNameCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.GroupCanNotBeEmpty;

public class CheckStudentInfo {

  public boolean check(Student student) {
    if (student.getFullName().isEmpty()) {
      new FullNameCanNotBeEmpty("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    Calendar calendar = Calendar.getInstance();
    Integer currentYear = calendar.get(Calendar.YEAR);
    Calendar studentCalendar = Calendar.getInstance();
    studentCalendar.setTime(student.getBirthDate());
    Integer studentBirtYear = studentCalendar.get(Calendar.YEAR);
    if ((currentYear - studentBirtYear) < 16) {
      new BirthDateCanNotBeEmpty("Вік не може бути нижче 16").showAlert();
      return false;
    }
    if (student.getGroup() == null) {
      new GroupCanNotBeEmpty("Група не може бути пустою").showAlert();
      return false;
    }
    if (student.getAddress().isEmpty() || student.getAddress().isBlank()) {
      new AddressCanNotBeEmpty("Адреса не може бути пустою").showAlert();
      return false;
    }
    return true;
  }
}
