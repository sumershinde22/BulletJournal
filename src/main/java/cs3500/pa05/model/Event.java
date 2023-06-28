package cs3500.pa05.model;

/**
 * Event Interface
 */
public interface Event extends Action {
  /**
   * gets the starting time of the event
   *
   * @return the starting time of the event
   */
  String getStartTime();

  /**
   * gets the duration of the event
   *
   * @return the duration of the event
   */
  String getDuration();
}
