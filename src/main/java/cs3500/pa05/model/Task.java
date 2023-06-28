package cs3500.pa05.model;

/**
 * Task Interface
 */
public interface Task extends Action {
  /**
   * Gets whether or not the task is complete
   *
   * @return true if the task is complete, false if otherwise
   */
  boolean isComplete();

  /**
   * sets this task as completed or true
   */
  void setAsComplete();

  /**
   * sets the task as incomplete or false
   */
  void setAsIncomplete();
}
