package cs3500.pa05.model;

/**
 * Model Interface
 */
public interface Model {
  /**
   * saves the file to a bujo file
   *
   * @param fileName name of file
   */
  void saveFile(String fileName);

  /**
   * Gets the current week being displayed in the GUI
   *
   * @return the current week
   */
  Week getCurrentWeek();

}
