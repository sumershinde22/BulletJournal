package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskImplTest {

  private Task task;
  private Task task2;

  @BeforeEach
  public void setUp() {
    task = new TaskImpl("Task 1", "", DayEnum.MONDAY);
    task2 = new TaskImpl("Task 2", "", DayEnum.MONDAY, false);
  }

  @Test
  public void testGetName() {
    assertEquals("Task 1", task.getName());
  }

  @Test
  public void testGetDescription() {
    assertEquals("", task.getDescription());
  }

  @Test
  public void testGetDayOfWeek() {
    assertEquals(DayEnum.MONDAY, task.getDayOfWeek());
  }

  @Test
  public void testSetAsIncomplete() {
    task.setAsIncomplete();
  }

  @Test
  public void testEquals() {
    String s = "";
    assertFalse(task.equals(task2));
    assertFalse(task.equals(s));
  }

  @Test
  void testEqualsDifferentEvents() {
    TaskImpl task1 = new TaskImpl("Task 1", "Description", DayEnum.MONDAY);
    TaskImpl task2 = new TaskImpl("Task 2", "Description", DayEnum.MONDAY);

    assertFalse(task1.equals(task2));
  }

  @Test
  void testEqualsDifferentEvents2() {
    TaskImpl task1 = new TaskImpl("Task 1", "Description", DayEnum.MONDAY);
    TaskImpl task2 = new TaskImpl("Task 1", "Description2", DayEnum.MONDAY);

    assertFalse(task1.equals(task2));
  }

  @Test
  void testEqualsDifferentEvents3() {
    TaskImpl task1 = new TaskImpl("Task 1", "Description", DayEnum.TUESDAY);
    TaskImpl task2 = new TaskImpl("Task 1", "Description", DayEnum.MONDAY);

    assertFalse(task1.equals(task2));
  }

  @Test
  void testEqualsDifferentEvents4() {
    TaskImpl task1 = new TaskImpl("Task 1", "Description", DayEnum.TUESDAY, true);
    TaskImpl task2 = new TaskImpl("Task 1", "Description", DayEnum.TUESDAY, false);

    assertFalse(task1.equals(task2));
  }

}