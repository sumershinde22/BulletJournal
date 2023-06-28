package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {
  private Day day;
  private Day day2;
  private Day day3;

  @BeforeEach
  public void setup() {

    List<Event> events = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    day = new Day(DayEnum.MONDAY, events, tasks);
    day2 = new Day(DayEnum.TUESDAY, events, tasks);
    day3 = new Day(DayEnum.MONDAY, events, tasks);
  }

  @Test
  public void testTask() {
    Task task = new TaskImpl("TODO", "TODO", DayEnum.MONDAY);
    day.addTask(task);
    assertEquals(1, day.getTotalTasks());
    assertEquals(0, day.getTotalCompletedTasks());
    task.setAsComplete();
    assertEquals(1, day.getTotalCompletedTasks());

    assertEquals(task, day.getTasks().get(0));
  }

  @Test
  public void testAddEvent() {
    Event event = new EventImpl("TODO", "TODO", DayEnum.MONDAY, "12:00", "120");
    day.addEvent(event);
    assertEquals(1, day.getTotalEvents());

    assertEquals(day.getEvents().get(0), event);
  }

  @Test
  public void testIsDay() {
    assertTrue(day.isDay(DayEnum.MONDAY));
    assertFalse(day.isDay(DayEnum.FRIDAY));

  }

  @Test
  public void testDayString() {
    assertEquals("Monday", day.dayString());
  }

  @Test
  public void testGetDay() {
    assertEquals(day.getDay(), DayEnum.MONDAY);
  }

  @Test
  public void testEquals() {
    String s = "";
    assertFalse(day.equals(day2));
    assertFalse(day.equals(s));
    assertTrue(day.equals(day3));
    List<Event> events = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    tasks.add(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    Day newDay = new Day(DayEnum.MONDAY, events, tasks);
    assertFalse(day.equals(newDay));
    List<Event> events2 = new ArrayList<>();
    events2.add(new EventImpl("Name", "Description", DayEnum.MONDAY, "12", "120"));
    Day newDay2 = new Day(DayEnum.MONDAY, events2, tasks);
    assertFalse(day.equals(newDay2));
  }

  @Test
  public void testEquals2() {
    day.addTask(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    day3.addTask(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    assertTrue(day.equals(day3));
    day.addTask(new TaskImpl("Task 2", "", DayEnum.TUESDAY));
    day3.addTask(new TaskImpl("Task 3", "Description", DayEnum.MONDAY));
    assertTrue(day.equals(day3));
  }

  @Test
  public void testEquals3() {
    // Create a Day object with the same attributes as 'day'
    List<Event> events = new ArrayList<>(day.getEvents());
    List<Task> tasks = new ArrayList<>(day.getTasks());
    Day newDay = new Day(day.getDay(), events, tasks);

    // Test the equals() method
    assertTrue(day.equals(newDay));

    // Modify the events list of 'newDay'
    events.add(new EventImpl("Name", "Description", DayEnum.MONDAY, "12", "120"));
    assertFalse(day.equals(newDay));

    // Modify the tasks list of 'newDay'
    tasks.add(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    assertFalse(day.equals(newDay));

    // Modify the day attribute of 'newDay'
    DayEnum differentDay = DayEnum.TUESDAY;
    Day newDay2 = new Day(differentDay, events, tasks);
    assertFalse(day.equals(newDay2));
  }

  @Test
  public void testEquals4() {
    // Create a Day object with the same attributes as 'day'
    List<Event> events = new ArrayList<>(day.getEvents());
    List<Task> tasks = new ArrayList<>(day.getTasks());
    Day newDay = new Day(day.getDay(), events, tasks);

    // Test the equals() method
    assertTrue(day.equals(newDay));

    // Modify the events list of 'newDay'
    events.add(new EventImpl("Name", "Description", DayEnum.MONDAY, "12", "120"));
    assertFalse(day.equals(newDay));

    // Restore 'newDay' to its original state
    events.remove(0);
    assertTrue(day.equals(newDay));

    // Modify the tasks list of 'newDay'
    tasks.add(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    assertFalse(day.equals(newDay));

    // Restore 'newDay' to its original state
    tasks.remove(0);
    assertTrue(day.equals(newDay));

    // Modify the day attribute of 'newDay'
    DayEnum differentDay = DayEnum.TUESDAY;
    Day newDay2 = new Day(differentDay, events, tasks);
    assertFalse(day.equals(newDay2));

    // Restore 'newDay2' to its original state
    newDay2 = new Day(day.getDay(), events, tasks);
    assertTrue(day.equals(newDay2));

    // Test equality with a different object type
    assertFalse(day.equals("not a Day object"));

    // Test equality with null
    assertFalse(day.equals(null));
  }

  @Test
  public void testEquals5() {
    // Create a Day object with the same attributes as 'day'
    List<Event> events = new ArrayList<>(day.getEvents());
    List<Task> tasks = new ArrayList<>(day.getTasks());
    Day newDay = new Day(day.getDay(), events, tasks);

    // Test the equals() method
    assertTrue(day.equals(newDay));

    // Modify the events list of 'newDay'
    events.add(new EventImpl("Name", "Description", DayEnum.MONDAY, "12", "120"));
    assertFalse(day.equals(newDay));

    // Restore 'newDay' to its original state
    events.remove(0);
    assertTrue(day.equals(newDay));

    // Modify the tasks list of 'newDay'
    tasks.add(new TaskImpl("Task 1", "Description", DayEnum.MONDAY));
    assertFalse(day.equals(newDay));

    // Restore 'newDay' to its original state
    tasks.remove(0);
    assertTrue(day.equals(newDay));

    // Modify the day attribute of 'newDay'
    DayEnum differentDay = DayEnum.TUESDAY;
    Day newDay2 = new Day(differentDay, events, tasks);
    assertFalse(day.equals(newDay2));

    // Restore 'newDay2' to its original state
    newDay2 = new Day(day.getDay(), events, tasks);
    assertTrue(day.equals(newDay2));

    // Test equality with a different object type
    assertFalse(day.equals("not a Day object"));

    // Test equality with null
    assertFalse(day.equals(null));
  }

  @Test
  public void testEquals_sameInstance_returnsTrue() {
    Day day = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    assertTrue(day.equals(day));
  }

  @Test
  public void testEquals_equalDayObjects_returnsTrue() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Day day2 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    assertTrue(day1.equals(day2));
    assertTrue(day2.equals(day1));
  }

  @Test
  public void testEquals_differentDays_returnsFalse() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Day day2 = new Day(DayEnum.TUESDAY, new ArrayList<>(), new ArrayList<>());
    assertFalse(day1.equals(day2));
    assertFalse(day2.equals(day1));
  }

  @Test
  public void testEquals_differentTasks_returnsFalse() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Day day2 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Task task1 = new TaskImpl("Task 1", "Description 1", DayEnum.MONDAY);
    Task task2 = new TaskImpl("Task 2", "Description 2", DayEnum.MONDAY);
    day1.addTask(task1);
    day2.addTask(task2);
    assertFalse(day1.equals(day2));
    assertFalse(day2.equals(day1));
  }

  @Test
  public void testEquals_differentEvents_returnsFalse() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Day day2 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Event event1 = new EventImpl("Event 1", "Description 1", DayEnum.MONDAY, "10:00 AM", "1 hour");
    Event event2 = new EventImpl("Event 2", "Description 2", DayEnum.MONDAY, "11:00 AM", "2 hours");
    day1.addEvent(event1);
    day2.addEvent(event2);
    assertFalse(day1.equals(day2));
    assertFalse(day2.equals(day1));
  }

  @Test
  public void testEquals_null_returnsFalse() {
    Day day = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    assertFalse(day.equals(null));
  }

  @Test
  public void testEquals_differentObjectType_returnsFalse() {
    Day day = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    assertFalse(day.equals("not a Day object"));
  }
}