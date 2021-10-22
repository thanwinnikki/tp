package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.model.common.Description;

/**
 * Represents a Task in the address book.
 * Guarantees: Details are present and not null. There are no restrictions to what is allowed as a description.
 */
public class Task {

    private static final String DONE_TASK = "[DONE] ";
    private static final String UNDONE_TASK = "[    ] ";

    private final Description description;
    private boolean done = false;

    /**
     * description must be present and not null.
     */
    public Task(Description description) {
        requireNonNull(description);
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public void setDoneTask() {
        done = true;
    }

    public void setUndoneTask() {
        done = false;
    }

    public boolean getDoneTask() {
        return done;
    }

    /**
     * Returns true if both {@code Task} have the same {@code description}.
     * Does not check whether the tasks are done or not.
     * This defines a weaker notion of equality between the two {@code Tasks}.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public String toString() {
        if (done) {
            return DONE_TASK + description.toString();
        } else {
            return UNDONE_TASK + description.toString();
        }
    }

    @Override
    public boolean equals (Object other) {
        return other == this
                || (other instanceof Task
                && description.equals(((Task) other).getDescription())
                && (done == ((Task) other).getDoneTask()));
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
