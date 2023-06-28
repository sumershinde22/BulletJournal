package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventImpl;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "day-name": "MONDAY",
 * "events": [...],
 * "tasks": [...]
 * }
 * </code>
 * </p>
 *
 * @param day    the name of this day
 * @param events the events for this day
 * @param tasks  the tasks for this day
 */
public record DayJson(
    @JsonProperty("day-name") DayEnum day,
    @JsonProperty("events") List<EventJson> events,
    @JsonProperty("tasks") List<TaskJson> tasks

) {

  /**
   * Makes a List of Events with days' EventJson info
   *
   * @return a List of Events
   */
  public List<Event> createEvents() {
    List<Event> eventsList = new ArrayList<>();

    for (EventJson ej : events) {
      Event newEvent = new EventImpl(ej.name(), ej.description(), day,
          ej.start(), ej.duration());
      eventsList.add(newEvent);
    }

    return eventsList;
  }

  /**
   * Makes a List of Tasks with days' TaskJson info
   *
   * @return a List of Tasks
   */
  public List<Task> createTasks() {
    List<Task> tasksList = new ArrayList<>();

    for (TaskJson tj : tasks) {
      Task newTask = new TaskImpl(tj.name(), tj.description(), day, tj.completed());
      tasksList.add(newTask);
    }
    return tasksList;
  }
}
