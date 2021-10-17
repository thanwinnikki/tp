package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.names.Name;

/**
 * Jackson-friendly version of {@link Group}. // TODO: change Tag to Task
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description) {
        this.description = description;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    // TODO: change Group to Task
    // public JsonAdaptedTask(Task source) {
    public JsonAdaptedTask(Group source) {
        // TODO: Task description
        // description = source.getDescription().value;
        description = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException If there were any data constraints violated in the adapted task.
     */
    // TODO: change Group to Task
    // public Task toModelType() throws IllegalValueException {
    public Group toModelType() throws IllegalValueException {
        // TODO: change Name to Description
//        if (description == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
//        }
//        if (!Description.isValidDescription(description)) {
//            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
//        }
//        final Description modelDescription = new Description(description);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(description)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(description);

        // TODO: change Group to Task
        // return new Task(modelDescription);
        return new Group(modelName);
    }

}
