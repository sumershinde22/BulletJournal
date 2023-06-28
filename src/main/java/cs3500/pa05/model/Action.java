package cs3500.pa05.model;

/**
 * Action Interface
 */
public interface Action {

  /**
   * Gets the name of the action object
   *
   * @return String name of the action object
   */
  String getName();

  /**
   * Gets the description of the action object
   *
   * @return String description of the action object
   */
  String getDescription();

  /**
   * Gets the day of the week for the action object
   *
   * @return the day of the week that is associated with this action object
   */
  DayEnum getDayOfWeek();
}
