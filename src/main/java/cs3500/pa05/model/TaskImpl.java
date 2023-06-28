package cs3500.pa05.model;

/**
 * A Task marked in the user's BulletJournal week
 */
public class TaskImpl implements Task {
  private final String name;
  private final String description;
  private final DayEnum dayEnum;
  private boolean isComplete;

  /**
   * Constructs a new Task
   *
   * @param name        name of the task
   * @param description description of the task
   * @param dayEnum     day of the task
   */
  public TaskImpl(String name, String description,
                  DayEnum dayEnum) {
    this.name = name;
    this.description = description;
    this.dayEnum = dayEnum;
    isComplete = false;
  }

  /**
   * Constructs a new Task
   *
   * @param name        name of the task
   * @param description description of the task
   * @param dayEnum     day of the task
   * @param complete    true if this task has been completed
   */
  public TaskImpl(String name, String description,
                  DayEnum dayEnum, boolean complete) {
    this.name = name;
    this.description = description;
    this.dayEnum = dayEnum;
    isComplete = complete;
  }

  /**
   * Getter Method
   *
   * @return the name of the task
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Getter method
   *
   * @return the description of the task
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Getter method
   *
   * @return the day of the week of this task
   */
  @Override
  public DayEnum getDayOfWeek() {
    return dayEnum;
  }

  /**
   * Getter method
   *
   * @return boolean true if this task is complete
   */
  @Override
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Setter method that marks complete as true
   */
  @Override
  public void setAsComplete() {
    isComplete = true;
  }

  public void setAsIncomplete() {
    isComplete = false;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TaskImpl ti) {
      return this.name.equals(ti.name) && this.description.equals(ti.description)
          && this.dayEnum.equals(ti.dayEnum) && (this.isComplete == ti.isComplete);
    } else {
      return false;
    }
  }
}
