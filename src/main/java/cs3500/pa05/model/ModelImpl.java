package cs3500.pa05.model;

import java.util.List;

/**
 * Implementation of Model Interface
 */
public class ModelImpl implements Model {

  private final Week currentWeek;
  private final List<Day> currentDays;
  private final DayEnum startDay;
  private final List<String> notes;

  /**
   * Constructor
   *
   * @param weekName    name of this week
   * @param maxTasks    maximum number of tasks for the week
   * @param maxEvents   maximum number of events for the week
   * @param currentDays current day info for the week
   * @param startDay    day week will start on (Sunday or Monday)
   * @param notes       notes that were added to the week
   */
  public ModelImpl(String weekName, int maxTasks, int maxEvents,
                   List<Day> currentDays, DayEnum startDay, List<String> notes) {
    currentWeek = new Week(weekName, maxTasks, maxEvents);
    this.currentDays = currentDays;
    this.startDay = startDay;
    this.notes = notes;
  }

  @Override
  public Week getCurrentWeek() {
    return currentWeek;
  }


  @Override
  public void saveFile(String fileName) {
    BujoFileWriter bfw = new BujoFileWriter(fileName, currentWeek.getWeekName(),
        currentWeek.getMaxTasks(), currentWeek.getMaxEvents(), startDay, currentDays, notes);
    bfw.saveFile();
  }
}

