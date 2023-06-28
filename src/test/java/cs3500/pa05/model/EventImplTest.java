package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventImplTest {

  private Event event;

  @BeforeEach
  public void setUp() {
    event = new EventImpl("Event 1", "", DayEnum.MONDAY, "12:00", "120");
  }

  @Test
  public void testGetName() {
    assertEquals("Event 1", event.getName());
  }

  @Test
  public void testGetDescription() {
    assertEquals("", event.getDescription());
  }

  @Test
  public void testGetDayOfWeek() {
    assertEquals(DayEnum.MONDAY, event.getDayOfWeek());
  }

  @Test
  public void testGetStartTime() {
    assertEquals("12:00", event.getStartTime());
  }

  @Test
  public void testGetDuration() {
    assertEquals("120", event.getDuration());
  }

  @Test
  void testEqualsSameObject() {
    EventImpl event = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");

    assertTrue(event.equals(event));
  }

  @Test
  void testEqualsEqualEvents() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");

    assertTrue(event1.equals(event2));
    assertTrue(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentEvents() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 2", "Description", DayEnum.MONDAY, "12:00", "120");

    assertFalse(event1.equals(event2));
    assertFalse(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentEvents2() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 1", "Description2", DayEnum.MONDAY, "12:00", "120");

    assertFalse(event1.equals(event2));
    assertFalse(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentEvents3() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 1", "Description", DayEnum.TUESDAY, "12:00", "120");

    assertFalse(event1.equals(event2));
    assertFalse(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentEvents4() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:01", "120");

    assertFalse(event1.equals(event2));
    assertFalse(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentEvents5() {
    EventImpl event1 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    EventImpl event2 = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "121");

    assertFalse(event1.equals(event2));
    assertFalse(event2.equals(event1));
  }

  @Test
  void testEqualsDifferentObject() {
    EventImpl event = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");
    String differentObject = "Not an Event object";

    assertFalse(event.equals(differentObject));
  }

  @Test
  void testEqualsNullObject() {
    EventImpl event = new EventImpl("Event 1", "Description", DayEnum.MONDAY, "12:00", "120");

    assertFalse(event.equals(null));
  }
}