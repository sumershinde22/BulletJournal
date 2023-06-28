package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An Event marked in the user's BulletJournal week
 */
public class EventImpl implements Event {

  private String name;
  private String description;
  private DayEnum dayEnum;
  private String startTime;
  private String duration;

  private final int maxNumEvents;

  /**
   * Constructs an Event
   *
   * @param name        name of the event
   * @param description description of the event
   * @param dayEnum     day of the event
   * @param startTime   starting time of the event
   * @param duration    duration of the event
   */

  public EventImpl(String name, String description,
                   DayEnum dayEnum, String startTime, String duration) {
    this.name = name;
    this.description = description;
    this.dayEnum = dayEnum;
    this.startTime = startTime;
    this.duration = duration;
    maxNumEvents = 5;
  }

  /**
   * Getter method
   *
   * @return the name of this event
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Getter method
   *
   * @return the description of this event
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Getter method
   *
   * @return the day of the week of this event
   */
  @Override
  public DayEnum getDayOfWeek() {
    return dayEnum;
  }

  /**
   * Getter method
   *
   * @return start time of this event
   */
  @Override
  public String getStartTime() {
    return startTime;
  }

  /**
   * Getter method
   *
   * @return the duration of this event
   */
  @Override
  public String getDuration() {
    return duration;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof EventImpl ei) {
      return this.name.equals(ei.name) && this.description.equals(ei.description)
          && this.dayEnum.equals(ei.dayEnum) && (this.startTime.equals(ei.startTime))
          && this.duration.equals(ei.duration);
    } else {
      return false;
    }
  }
}
