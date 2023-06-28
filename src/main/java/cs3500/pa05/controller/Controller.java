package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import java.util.List;
import javafx.scene.control.Hyperlink;

/**
 * Interface of Controller to handle user input
 */
public interface Controller {
  /**
   * Running the initial setup of stage
   */
  void run();

  /**
   * Starts Bujo Week GUI
   *
   * @param weekName     name of the week
   * @param maxEvents    maximum events a user can add
   * @param maxTasks     maximum tasks a user can add
   * @param days         A list of Days objects
   * @param startDay     the day object to start on (either monday or sunday)
   * @param notesDisplay notes for this BulletJournal week
   */
  void startBujoWeek(String weekName, int maxEvents, int maxTasks,
                     List<Day> days, String startDay, List<String> notesDisplay);

  /**
   * handles when the create new week button is pressed
   */
  void handleCreateNew();

  /**
   * Handles opening an existing bujo file when the user clicks the corresponding button
   */
  void handleOpenExisting();

  /**
   * Add a task (prompted when user clicks add task button)
   */
  void addTask();

  /**
   * Adds the event to the GUI
   */
  void addEvent();

  /**
   * Helper method that gets the links from the description
   *
   * @param description description that the user gave
   * @return a list of the links as strings
   */
  List<String> getLinks(String description);

  /**
   * Sets the given linkText as a hyperlink to be clicked in the GUI
   *
   * @param linkText text of the link
   * @return HyperLink object to be added to the GUI
   */
  Hyperlink setHyperlink(String linkText);

  /**
   * Adds notes to the notes box
   */
  void addToNotes();

  /**
   * displays the weekly overview
   */
  void displayWeekOverview();

  /**
   * Handles saving the week to a bujo file
   */
  void handleSaveFile();
}
