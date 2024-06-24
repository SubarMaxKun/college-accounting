package org.shevliakov.collegeaccounting.appcore.util;

import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;

public class CheckLecturerInfo {

  public boolean check(Lecturer lecturer) {
    if (lecturer.getFullName().isEmpty() || lecturer.getFullName().isBlank()) {
      new FieldEmptyIllegalArgumentException("ПІБ не можуть бути пустими").showAlert();
      return false;
    }
    if (lecturer.getPosition().isEmpty() || lecturer.getPosition().isBlank()) {
      new FieldEmptyIllegalArgumentException("Посада не може бути пустою").showAlert();
      return false;
    }
    if (lecturer.getExperience().isEmpty() || lecturer.getExperience().isBlank()) {
      new FieldEmptyIllegalArgumentException("Стаж не може бути пустим").showAlert();
      return false;
    }
    if (lecturer.getCategory() == null) {
      new FieldEmptyIllegalArgumentException(
          "Кваліфікаційна кетегорія не може бути пустою").showAlert();
      return false;
    }
    if (lecturer.getTitle() == null) {
      new FieldEmptyIllegalArgumentException("Педагогічне звання не може бути пустим").showAlert();
      return false;
    }
    if (lecturer.getPreviousCertificationResult().isEmpty() || lecturer.getPreviousCertificationResult().isBlank()) {
      new FieldEmptyIllegalArgumentException("Результат попередньої атестації не може бути пустим").showAlert();
      return false;
    }
    if (lecturer.getHours().isEmpty() || lecturer.getHours().isBlank()) {
      new FieldEmptyIllegalArgumentException(
          "Рік підвищення кваліфікації, кількість годин не можуть бути пустими").showAlert();
      return false;
    }
    return true;
  }
}
