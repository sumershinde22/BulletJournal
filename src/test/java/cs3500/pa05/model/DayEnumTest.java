package cs3500.pa05.model;

import static cs3500.pa05.model.DayEnum.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayEnumTest {

  private DayEnum dayEnumMon;
  private DayEnum dayEnumTue;
  private DayEnum dayEnumWed;
  private DayEnum dayEnumThu;
  private DayEnum dayEnumFri;
  private DayEnum dayEnumSat;
  private DayEnum dayEnumSun;


  @BeforeEach
  public void setUp() {
    dayEnumMon = DayEnum.MONDAY;
    dayEnumTue = DayEnum.TUESDAY;
    dayEnumWed = DayEnum.WEDNESDAY;
    dayEnumThu = DayEnum.THURSDAY;
    dayEnumFri = DayEnum.FRIDAY;
    dayEnumSat = DayEnum.SATURDAY;
    dayEnumSun = DayEnum.SUNDAY;
  }

  @Test
  public void dayToString() {
    assertEquals("Monday", dayEnumMon.toString());
    assertEquals("Tuesday", dayEnumTue.toString());
    assertEquals("Wednesday", dayEnumWed.toString());
    assertEquals("Thursday", dayEnumThu.toString());
    assertEquals("Friday", dayEnumFri.toString());
    assertEquals("Saturday", dayEnumSat.toString());
    assertEquals("Sunday", dayEnumSun.toString());
  }

  @Test
  public void testFromString() {
    assertEquals(fromString("Monday"), dayEnumMon);
    assertEquals(fromString("Tuesday"), dayEnumTue);
    assertEquals(fromString("Wednesday"), dayEnumWed);
    assertEquals(fromString("Thursday"), dayEnumThu);
    assertEquals(fromString("Friday"), dayEnumFri);
    assertEquals(fromString("Saturday"), dayEnumSat);
    assertEquals(fromString("Sunday"), dayEnumSun);
  }

}