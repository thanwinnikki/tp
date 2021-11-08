package seedu.address.model.task.exceptions;

/**
 * Signals that the operation is unable to find the specified task.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("The task that you are looking for does not exist!");
    }
}
