package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import cs3500.pa05.controller.ControllerImpl;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BujoFileReaderTest {
  @Test
  void testCreateSceneFromFileWithInvalidFileExtension() {
    // Create a mock controller
    ControllerImpl controller = mock(ControllerImpl.class);

    // Create a sample bujo file with an invalid extension
    String fileName = "test.txt";
    File bujoFile = new File(fileName);

    // Create an instance of BujoFileReader
    BujoFileReader reader = new BujoFileReader(fileName, controller);

    // Call createSceneFromFile() method and expect an exception to be thrown
    assertThrows(IllegalArgumentException.class, reader::createSceneFromFile);
  }

  @Test
  void testGetDays() {
    // Create sample data
    List<DayJson> dayJsons = new ArrayList<>();
    List<EventJson> eventJsons = new ArrayList<>();
    List<TaskJson> taskJsons = new ArrayList<>();
    dayJsons.add(new DayJson(DayEnum.MONDAY, eventJsons, taskJsons));
    // Create an instance of BujoFileReader
    ArrayList<Day> days = BujoFileReader.getDays(dayJsons);

    // Assert that the returned list is empty
    assertEquals(1, days.size());
  }
}
