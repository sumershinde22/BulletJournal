package cs3500.pa05.model;

import java.util.List;

/**
 * Represents a Day in the BulletJournal
 */
public class Day {
  private final List<Task> tasks;
  private final List<Event> events;
  private final DayEnum day;

  /**
   * Day constructor
   *
   * @param day    DayEnum of day representation
   * @param events list of event objects
   * @param tasks  list of task objects
   */
  public Day(DayEnum day, List<Event> events, List<Task> tasks) {
    this.day = day;
    this.tasks = tasks;
    this.events = events;
  }

  /**
   * Adds task
   *
   * @param task task object to be added
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * Adds event
   *
   * @param event event object to be added
   */
  public void addEvent(Event event) {
    events.add(event);
  }

  /**
   * gets the total number of tasks
   *
   * @return the total number of tasks
   */
  public int getTotalTasks() {
    return tasks.size();
  }

  /**
   * gets the total number of completed tasks
   *
   * @return the total number of completed tasks
   */
  public int getTotalCompletedTasks() {
    int numCompleted = 0;
    for (Task task : tasks) {
      if (task.isComplete()) {
        numCompleted++;
      }
    }
    return numCompleted;
  }


  /**
   * gets the total number of events
   *
   * @return the total number of events
   */
  public int getTotalEvents() {
    return events.size();
  }

  /**
   * Determines if the given weekday is equivalent to the one this Day Object has
   *
   * @param dayEnum the day given
   * @return true if it is the day given
   */
  public boolean isDay(DayEnum dayEnum) {
    return day.equals(dayEnum);
  }

  /**
   * Getter method
   *
   * @return tasks for this Day object
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Getter method
   *
   * @return events for this Day object
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Getter method
   *
   * @return string representation of this day
   */
  public String dayString() {
    return this.day.toString();
  }

  /**
   * Getter Method
   *
   * @return this day's enumeration
   */
  public DayEnum getDay() {
    return this.day;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Day d) {
      // checking all tasks are equal
      if (this.tasks.size() != d.tasks.size()) {
        return false;
      } else {
        for (int i = 0; i < tasks.size(); i++) {
          if (!this.tasks.get(i).equals(d.tasks.get(i))) {
            return false;
          }
        }
      }
      // checking all events are equal
      if (this.events.size() != d.events.size()) {
        return false;
      } else {
        for (int i = 0; i < events.size(); i++) {
          if (!this.events.get(i).equals(d.events.get(i))) {
            return false;
          }
        }
      }
      return this.day.equals(d.day);
    } else {
      return false;
    }
  }
}



