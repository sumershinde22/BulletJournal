package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BujoFileWriterTest {
  private static final String TEST_FILE_PATH = "test.json";

  @BeforeEach
  void setUp() {
    // Delete the test file if it exists
    File testFile = new File(TEST_FILE_PATH);
    if (testFile.exists()) {
      testFile.delete();
    }
  }

  @Test
  void testSaveFile() {
    // Create sample data
    String weekName = "Week 1";
    int maxTasks = 5;
    int maxEvents = 3;
    DayEnum startDay = DayEnum.MONDAY;
    List<Day> days = new ArrayList<>();
    List<String> notes = new ArrayList<>();

    // Create an instance of BujoFileWriter
    BujoFileWriter writer = new BujoFileWriter(TEST_FILE_PATH, weekName, maxTasks, maxEvents,
        startDay, days, notes);

    // Call saveFile() method
    writer.saveFile();

    // Assert that the file has been created
    assertTrue(Files.exists(Path.of(TEST_FILE_PATH)));
  }

  @Test
  void testSaveFile2() {
    // Create sample data
    List<Day> days = new ArrayList<>();
    List<Event> events = new ArrayList<>();
    events.add(new EventImpl("Event 1", "", DayEnum.MONDAY, "", ""));
    List<Task> tasks = new ArrayList<>();
    tasks.add(new TaskImpl("Task 1", "", DayEnum.MONDAY));
    days.add(new Day(DayEnum.MONDAY, events, tasks));
    List<String> notes = new ArrayList<>();
    String weekName = "Week 1";
    int maxTasks = 5;
    int maxEvents = 3;
    DayEnum startDay = DayEnum.MONDAY;

    // Create an instance of BujoFileWriter
    BujoFileWriter writer = new BujoFileWriter(TEST_FILE_PATH, weekName, maxTasks, maxEvents,
        startDay, days, notes);

    // Call saveFile() method
    writer.saveFile();

    // Assert that the file has been created
    assertTrue(Files.exists(Path.of(TEST_FILE_PATH)));
  }
}
