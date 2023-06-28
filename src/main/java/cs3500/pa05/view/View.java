package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * View interface
 */
public interface View {

  /**
   * loads the GUI view
   *
   * @return Scene to be loaded in driver
   * @throws IllegalStateException throws if not loadable
   */
  Scene load() throws IllegalStateException;
}
