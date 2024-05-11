package org.shevliakov.collegeaccounting.appcore.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class for converting a list of dates to a list of years.
 */
public class ConvertDatesToYears {

  /**
   * Private constructor to prevent instantiation.
   */
  private ConvertDatesToYears() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Converts a list of dates to a list of years.
   *
   * @param dates the list of dates to convert
   * @return the list of years
   */
  public static List<Integer> convert(List<Date> dates) {
    List<Integer> years = new ArrayList<>();
    for (Date date : dates) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      if (!years.contains(calendar.get(Calendar.YEAR))) {
        years.add(calendar.get(Calendar.YEAR));
      }
    }

    years.sort(Comparator.comparingInt(year -> year));
    return years;
  }

}

