package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "name": "Walk Dog",
 * "description": "Walk my dog Tessa",
 * "start-time": "1:30",
 * "duration": "30"
 * }
 * </code>
 * </p>
 *
 * @param name        the name of this task
 * @param description description of this task
 * @param start       the time this event starts at
 * @param duration    the length of time this event will be
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("start-time") String start,
    @JsonProperty("duration") String duration

) {
}
