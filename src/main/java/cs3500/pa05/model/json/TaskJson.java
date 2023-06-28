package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 *{
 * "name": "Walk Dog",
 * "description": "Walk my dog Tessa"
 *}
 * </code>
 * </p>
 *
 * @param name the name of this task
 * @param description description of this task
 * @param completed true if this task has been completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("completed") boolean completed
) {
}
