package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayEnum;
import java.util.List;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "week-name": "WEEK 1",
 * "number-of-tasks": 3,
 * "number-of-events": 5,
 * "start-day": "MONDAY",
 * "weekdays" : [...]
 * }
 * </code>
 * </p>
 *
 * @param name     the name of the weekday this refers to
 * @param not      max number of tasks
 * @param noe      max number of events
 * @param sd       day week starts at
 * @param notes    notes kept for this week
 * @param weekdays info from weekdays to track
 */
public record BujoJson(
    @JsonProperty("week-name") String name,
    @JsonProperty("number-of-tasks") int not,
    @JsonProperty("number-of-events") int noe,
    @JsonProperty("start-day") DayEnum sd,
    @JsonProperty("notes") List<String> notes,
    @JsonProperty("weekdays") List<DayJson> weekdays
) {
}
