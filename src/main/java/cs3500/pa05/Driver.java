package cs3500.pa05;

import cs3500.pa05.controller.ControllerImpl;
import cs3500.pa05.view.HomePageView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents Java Bullet Journal Application
 */
public class Driver extends Application {
  /**
   * Starts the GUI for bullet journaling
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    HostServices hostServices = getHostServices();
    ControllerImpl controlla = new ControllerImpl(stage, hostServices);
    View homePageView = new HomePageView(controlla);

    try {
      Scene scene = homePageView.load();
      stage.setScene(scene);
      stage.setTitle("Bullet Journal");
      controlla.run();
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }


  /**
   * Entry point for bullet journaling
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}