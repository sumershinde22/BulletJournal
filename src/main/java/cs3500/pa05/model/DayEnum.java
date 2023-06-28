package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Represents the Days of the Week in a BulletJournal
 */
public enum DayEnum {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY;

  private ArrayList<EventImpl> events;
  private ArrayList<TaskImpl> tasks;

  /**
   * Constructor for creating a new Day enumeration
   */
  DayEnum() {
    events = new ArrayList<>();
    tasks = new ArrayList<>();
  }

  @Override
  public String toString() {
    if (this == DayEnum.MONDAY) {
      return "Monday";
    } else if (this == DayEnum.TUESDAY) {
      return "Tuesday";
    } else if (this == DayEnum.WEDNESDAY) {
      return "Wednesday";
    } else if (this == DayEnum.THURSDAY) {
      return "Thursday";
    } else if (this == DayEnum.FRIDAY) {
      return "Friday";
    } else if (this == DayEnum.SATURDAY) {
      return "Saturday";
    } else {
      return "Sunday";
    }
  }

  /**
   * Makes String of a DayEnum into the DayEnum version
   *
   * @param s String, will always be a validly formatted weekday
   * @return the DayEnum version of that String
   */
  public static DayEnum fromString(String s) {
    return switch (s) {
      case "Monday" -> DayEnum.MONDAY;
      case "Tuesday" -> DayEnum.TUESDAY;
      case "Wednesday" -> DayEnum.WEDNESDAY;
      case "Thursday" -> DayEnum.THURSDAY;
      case "Friday" -> DayEnum.FRIDAY;
      case "Saturday" -> DayEnum.SATURDAY;
      default -> DayEnum.SUNDAY;
    };
  }
}
