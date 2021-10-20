package seedu.address.model.task.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("The task that you are looking for does not exist!");
    }
}
