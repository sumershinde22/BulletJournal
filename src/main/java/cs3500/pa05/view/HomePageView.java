package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Home page implementation from View
 */
public class HomePageView implements View {
  private final FXMLLoader loader;

  /**
   * Constructs homepage
   *
   * @param controller controller for this loader
   */
  public HomePageView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("HomeView.fxml"));
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
