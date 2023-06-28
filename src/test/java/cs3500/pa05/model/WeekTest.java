package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {

  private Week week;

  @BeforeEach
  void setUp() {
    week = new Week("Week 1", 1, 1);
  }

  @Test
  public void testGetDays() {
    Day[] days = week.getDays();

    // Test the length of the array
    assertEquals(7, days.length);

    // Test if each element corresponds to the correct day of the week
    assertTrue(days[0].isDay(DayEnum.MONDAY));
    assertTrue(days[1].isDay(DayEnum.TUESDAY));
    assertTrue(days[2].isDay(DayEnum.WEDNESDAY));
    assertTrue(days[3].isDay(DayEnum.THURSDAY));
    assertTrue(days[4].isDay(DayEnum.FRIDAY));
    assertTrue(days[5].isDay(DayEnum.SATURDAY));
    assertTrue(days[6].isDay(DayEnum.SUNDAY));
  }

  @Test
  public void testCheckIfValidNumTasksPerDay() {
    assertFalse(week.checkIfValidNumTasksPerDay(2, DayEnum.MONDAY));
    assertTrue(week.checkIfValidNumTasksPerDay(1, DayEnum.MONDAY));
  }

  @Test
  public void testCheckIfValidNumEventsPerDay() {
    assertFalse(week.checkIfValidNumEventsPerDay(2, DayEnum.MONDAY));
    assertTrue(week.checkIfValidNumEventsPerDay(1, DayEnum.MONDAY));
  }

  @Test
  public void testGetOverView() {
    assertEquals(0, week.getOverview().get(0));
    //assertTrue(week.checkIfValidNumEventsPerDay(1));
    week.getDays()[0].addTask(new TaskImpl("Task1", "", DayEnum.MONDAY));
    assertEquals(0, week.getOverview().get(0));
  }

  @Test
  public void testGetWeekName() {
    assertEquals("Week 1", week.getWeekName());
  }

  @Test
  public void testGetMaxEvents() {
    assertEquals(1, week.getMaxEvents());
  }

  @Test
  public void testGetMaxTasks() {
    assertEquals(1, week.getMaxTasks());
  }

  @Test
  void testCheckIfValidNumTasksPerDay_Valid() {
    assertFalse(week.checkIfValidNumTasksPerDay(2, DayEnum.MONDAY));
  }

  @Test
  void testCheckIfValidNumTasksPerDay_ExceedLimit() {
    assertFalse(week.checkIfValidNumTasksPerDay(4, DayEnum.TUESDAY));
  }

  @Test
  void testCheckIfValidNumEventsPerDay_Valid() {
    assertFalse(week.checkIfValidNumEventsPerDay(2, DayEnum.WEDNESDAY));
  }

  @Test
  void testCheckIfValidNumEventsPerDay_ExceedLimit() {
    assertFalse(week.checkIfValidNumEventsPerDay(4, DayEnum.THURSDAY));
  }

}