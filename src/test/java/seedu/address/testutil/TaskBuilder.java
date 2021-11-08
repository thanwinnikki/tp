package seedu.address.testutil;

import seedu.address.model.common.Description;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_DESCRIPTION = "Read Book";

    private Description description;
    private boolean done = false;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_TASK_DESCRIPTION);
        done = false;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code TaskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        done = taskToCopy.getDoneTask();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code doneStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withDoneStatus(boolean doneStatus) {
        this.done = doneStatus;
        return this;
    }


    /**
     * Builds the {@code Task} as intended for testing.
     * @return The task that was built.
     */
    public Task build() {
        Task task = new Task(description);
        if (done) {
            task.setDoneTask();
        }
        return task;
    }
}
