package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cs3500.pa05.model.json.BujoJson;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;

/**
 * Writes the info of a BulletJournal week to the given file path in JSON format
 */
public class BujoFileWriter {
  private File file;
  private String weekName;
  private int not;
  private int noe;
  private DayEnum startDay;
  private List<Day> days;
  private List<String> notes;

  /**
   * Constructor
   *
   * @param fileName file path in String format
   * @param weekName name of this week
   * @param not max number of tasks
   * @param noe max number of events
   * @param startDay day week starts at in this format
   * @param days List of the info for each day
   * @param notes List of notes for this BulletJournal week
   */
  public BujoFileWriter(String fileName, String weekName, int not, int noe,
                        DayEnum startDay, List<Day> days, List<String> notes) {
    Path p = Path.of(fileName);
    this.file = p.toFile();
    this.weekName = weekName;
    this.not = not;
    this.noe = noe;
    this.startDay = startDay;
    this.days = days;
    this.notes = notes;
  }

  /**
   * Saves a BulletJournals info to the given filepath
   */
  public void saveFile() {
    List<DayJson> jsonDays = new ArrayList<>();

    for (Day d : days) {
      List<TaskJson> tasks = new ArrayList<>();
      List<EventJson> events = new ArrayList<>();
      for (Task t : d.getTasks()) {
        TaskJson newTaskJson = new TaskJson(t.getName(), t.getDescription(), t.isComplete());
        tasks.add(newTaskJson);
      }
      for (Event e : d.getEvents()) {
        EventJson newEventJson = new EventJson(e.getName(), e.getDescription(),
            e.getStartTime(), e.getDuration());
        events.add(newEventJson);
      }
      DayJson newDayJson = new DayJson(d.getDay(), events, tasks);
      jsonDays.add(newDayJson);
    }

    BujoJson bj = new BujoJson(weekName, not, noe, startDay, notes, jsonDays);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = "";
    try {
      json = ow.writeValueAsString(bj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    writeToFile(json);
  }

  /**
   * Writes the given Objects String to its filepath.
   *
   * @param contents what needs to be written to the file
   */
  private void writeToFile(String contents) {

    Path path = file.toPath();

    // Convert String to data for writing ("raw" byte data)
    byte[] data = contents.getBytes();

    // The path may not exist, or we may not have permissions to write to it,
    // in which case we need to handle that error (hence try-catch)
    try {
      // clear contents of file
      Writer writer = Files.newBufferedWriter(path);
      writer.write("");
      writer.flush();
      // write new json to file
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
