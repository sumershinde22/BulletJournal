package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Week Class which is used to store days
 */
public class Week {
  private final Day[] days;
  private final String weekName;
  private final int maxTasks;
  private final int maxEvents;

  /**
   * Constructs Week object
   *
   * @param weekName  name of week
   * @param maxTasks  maximum number of tasks in a day
   * @param maxEvents maximum number of events in a day
   */
  public Week(String weekName, int maxTasks, int maxEvents) {
    this.weekName = weekName;
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.days = new Day[7];
    instantiateDayArray(days);
  }

  /**
   * instantiates the array with a set number of days 7
   *
   * @param arr array of days
   */
  private void instantiateDayArray(Day[] arr) {
    arr[0] = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    arr[1] = new Day(DayEnum.TUESDAY, new ArrayList<>(), new ArrayList<>());
    arr[2] = new Day(DayEnum.WEDNESDAY, new ArrayList<>(), new ArrayList<>());
    arr[3] = new Day(DayEnum.THURSDAY, new ArrayList<>(), new ArrayList<>());
    arr[4] = new Day(DayEnum.FRIDAY, new ArrayList<>(), new ArrayList<>());
    arr[5] = new Day(DayEnum.SATURDAY, new ArrayList<>(), new ArrayList<>());
    arr[6] = new Day(DayEnum.SUNDAY, new ArrayList<>(), new ArrayList<>());
  }

  /**
   * returns an array of day objects in this week object
   *
   * @return array of day objects
   */
  public Day[] getDays() {
    return days;
  }

  /**
   * checks if valid number of tasks would be exceeded by adding numTasksToBeAdded amount of tasks
   *
   * @param numTasksToBeAdded number of prospective tasks that are going to be added
   * @param dayEnum           the day that the task should be added
   * @return true if valid, false if not
   */
  public boolean checkIfValidNumTasksPerDay(int numTasksToBeAdded, DayEnum dayEnum) {
    for (Day day : days) {
      if (day.getTotalTasks() + numTasksToBeAdded > maxEvents && day.getDay().equals(dayEnum)) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if valid number of events would be exceeded by adding numEventsToBeAdded amount of
   * events
   *
   * @param numEventsToBeAdded number of prospective events that are going to be added
   * @param dayEnum            the day the event should be added
   * @return true if valid, false if not
   */
  public boolean checkIfValidNumEventsPerDay(int numEventsToBeAdded, DayEnum dayEnum) {
    for (Day day : days) {
      if (day.getTotalEvents() + numEventsToBeAdded > maxTasks && day.getDay().equals(dayEnum)) {
        return false;
      }
    }
    return true;
  }


  /**
   * Gets the overview of the week and returns the values needed as an ArrayList of integers
   *
   * @return arraylist of totalEvents [0] totalTasks [1] percentageCompletedTasks [2] and
   *         totalCompletedTasks[3]
   */
  public ArrayList<Integer> getOverview() {
    int totalEvents = 0;
    int totalTasks = 0;
    int totalCompletedTasks = 0;
    double percentageCompletedTasks = 0;
    for (Day day : days) {
      totalEvents += day.getTotalEvents();
      totalTasks += day.getTotalTasks();
      totalCompletedTasks += day.getTotalCompletedTasks();
    }

    if (totalTasks != 0) {
      percentageCompletedTasks = (double) totalCompletedTasks / (double) totalTasks * 100;
    }
    ArrayList<Integer> overview = new ArrayList<>();
    overview.add(totalEvents);
    overview.add(totalTasks);
    overview.add((int) Math.round(percentageCompletedTasks));
    overview.add(totalCompletedTasks);
    return overview;
  }

  /**
   * Getter Method
   *
   * @return the maximum number of tasks
   */
  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * Getter Method
   *
   * @return the maximum number of events
   */
  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * Getter method
   *
   * @return the name of this week
   */
  public String getWeekName() {
    return weekName;
  }
}
