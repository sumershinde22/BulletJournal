package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelImplTest {
  private Model model;

  @BeforeEach
  public void setUp() {
    model = new ModelImpl("Week 1", 1,
        1, new ArrayList<>(), DayEnum.MONDAY, new ArrayList<>());
  }

  @Test
  public void testGetCurrentWeek() {
    Week week = new Week("Week 1", 1, 1);
    Week returnWeek = model.getCurrentWeek();
    assertEquals(week.getOverview(), returnWeek.getOverview());
  }
}