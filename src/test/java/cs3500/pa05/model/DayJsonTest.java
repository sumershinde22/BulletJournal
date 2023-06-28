package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing DayJson Class
 */
public class DayJsonTest {
  List<EventJson> jsonEvents = new ArrayList<>();
  List<Event> events = new ArrayList<>();
  List<TaskJson> jsonTasks = new ArrayList<>();
  List<Task> tasks = new ArrayList<>();
  DayJson dayjson;

  /**
   * Setup for my testing
   */
  @BeforeEach
  public void setup() {
    Event e1 = new EventImpl("walk dog", "walking my dog",
        DayEnum.MONDAY, "8:30", "30");
    events.add(e1);
    Task t1 = new TaskImpl("OOD HW", "Finish Testing",
        DayEnum.MONDAY, true);
    tasks.add(t1);

    EventJson ej1 = new EventJson("walk dog", "walking my dog",
        "8:30", "30");
    jsonEvents.add(ej1);

    TaskJson tj1 = new TaskJson("OOD HW", "Finish Testing", true);
    jsonTasks.add(tj1);

    dayjson = new DayJson(DayEnum.MONDAY, jsonEvents, jsonTasks);
  }

  /**
   * Testing createEvents method
   */
  @Test
  public void testCreateEvents() {
    assertEquals(events, dayjson.createEvents());
  }

  /**
   * Testing createTasks method
   */
  @Test
  public void testCreateTasks() {
    List<Task> newTask = dayjson.createTasks();
    assertEquals(tasks, newTask);
  }
}
