package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.ControllerImpl;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * WeekView implementation of View
 */
public class BujoWeekView implements View {
  private final FXMLLoader loader;

  /**
   * Constructing WeekView
   *
   * @param controller controller for this loader
   * @param startDay   day to start week on
   */
  public BujoWeekView(Controller controller, String startDay) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    if (Objects.equals(startDay, "Monday")) {
      this.loader.setLocation(getClass().getClassLoader().getResource("WeekView.fxml"));
    } else if (Objects.equals(startDay, "Sunday")) {
      this.loader.setLocation(getClass().getClassLoader().getResource("WeekViewSun.fxml"));
    }
  }

  @Override
  public Scene load() {
    try {
      Parent root = this.loader.load();
      return new Scene(root);
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
