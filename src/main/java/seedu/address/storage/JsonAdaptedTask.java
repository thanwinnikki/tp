package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Description;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Group}. // TODO: change Tag to Task
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description) {
        this.description = description;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException If there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final Description modelDescription = createDescription();
        return new Task(modelDescription);
    }

    private Description createDescription() throws IllegalValueException {
        if (description == null) {
            String exceptionMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
            throw new IllegalValueException(exceptionMessage);
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }
}
