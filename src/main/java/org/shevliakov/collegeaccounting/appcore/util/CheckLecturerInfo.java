package org.shevliakov.collegeaccounting.appcore.util;

import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.exception.CategoryCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.FullNameCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.HoursCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.PositionCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.QualificationTitleCanNotBeEmpty;

public class CheckLecturerInfo {

  public boolean check(Lecturer lecturer) {
    if (lecturer.getFullName().isEmpty() || lecturer.getFullName().isBlank()) {
      new FullNameCanNotBeEmpty("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    if (lecturer.getPosition().isEmpty() || lecturer.getPosition().isBlank()) {
      new PositionCanNotBeEmpty("Посада не може бути пустою").showAlert();
      return false;
    }
    if (lecturer.getCategory() == null) {
      new CategoryCanNotBeEmpty("Кваліфікаційна кетегорія не може бути пустою").showAlert();
      return false;
    }
    if (lecturer.getTitle() == null) {
      new QualificationTitleCanNotBeEmpty("Педагогічне звання не може бути пустим").showAlert();
      return false;
    }
    if (lecturer.getHours().isEmpty() || lecturer.getHours().isBlank()) {
      new HoursCanNotBeEmpty(
          "Рік підвищення кваліфікації, кількість годин не можуть бути пустими").showAlert();
      return false;
    }
    return true;
  }
}
