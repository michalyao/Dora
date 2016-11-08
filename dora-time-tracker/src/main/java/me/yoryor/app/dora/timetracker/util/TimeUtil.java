package me.yoryor.app.dora.timetracker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static LocalDateTime parse(String date) {
    return LocalDateTime.parse(date, formatter);
  }

  public static String format(LocalDateTime time) {
    return time.format(formatter);
  }

  /**
   * test
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(TimeUtil.format(LocalDateTime.now()));
  }
}


