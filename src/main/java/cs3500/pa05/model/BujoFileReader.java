package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.controller.ControllerImpl;
import cs3500.pa05.model.json.BujoJson;
import cs3500.pa05.model.json.DayJson;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a BujoFile to Create a Scene
 */
public class BujoFileReader {
  private final File file;
  private final ControllerImpl controller;

  /**
   * Constructor
   *
   * @param fileName name of file to read
   * @param controller the controller for this BujoFileReader
   */
  public BujoFileReader(String fileName, ControllerImpl controller) {
    Path p = Paths.get(fileName);
    this.file = p.toFile();
    this.controller = controller;
  }

  /**
   * Creates a Scene from the Given File
   */
  public void createSceneFromFile() {

    String filename = file.getPath();
    if (!filename.endsWith(".bujo")) {
      throw new IllegalArgumentException("Must give a bujo file.");
    }
    try {
      //System.out.println(filename);
      // create a reader
      Reader reader = Files.newBufferedReader(Paths.get(filename));

      //create ObjectMapper instance
      ObjectMapper objectMapper = new ObjectMapper();

      //read bujo file into a tree model to get contents of file
      JsonParser parser = objectMapper.getFactory().createParser(reader);

      BujoJson bujo = parser.readValueAs(BujoJson.class);
      String weekName = bujo.name();
      int not = bujo.not();
      int noe = bujo.noe();
      String startDay = bujo.sd().toString();

      List<String> notes = bujo.notes();

      List<DayJson> weekdaysJson = bujo.weekdays();

      // get the days in a structured list
      ArrayList<Day> days = getDays(weekdaysJson);

      //create bujo week view from this
      controller.startBujoWeek(weekName, noe, not, days, startDay, notes);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get an ArrayList representing every weekday and the info given from this bujo
   * file as Day objects
   *
   * @param weekdays the DayJsons for this bujo file
   * @return ArrayList of Day objects
   */
  public static ArrayList<Day> getDays(List<DayJson> weekdays) {
    ArrayList<Day> days = new ArrayList<>();
    // adding days this bujo file has info on
    // days for this week with tasks and events
    for (DayJson dj : weekdays) {
      List<Task> tasks = dj.createTasks(); // makes List<TaskJson> -> List<Task>
      List<Event> events = dj.createEvents(); // makes List<EventJson> -> List<Event>
      Day newDay = new Day(dj.day(), events, tasks);
      days.add(newDay);
    }
    return days;
  }
}