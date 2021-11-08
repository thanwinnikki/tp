package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Description;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description, @JsonProperty("isDone") boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     *
     * @param source The equivalent {@code Task} object.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription().toString();
        isDone = source.getDoneTask();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @return The equivalent {@code Task} to this storage representation.
     * @throws IllegalValueException If there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final Description modelDescription = createDescription();
        Task task = new Task(modelDescription);
        if (isDone) {
            task.setDoneTask();
        }
        return task;
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JsonAdaptedTask)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        JsonAdaptedTask o = (JsonAdaptedTask) other;
        boolean haveSameDescriptions = description.equals(o.description);
        boolean areBothMarkedAsDone = isDone == o.isDone;
        return haveSameDescriptions && areBothMarkedAsDone;
    }
}
