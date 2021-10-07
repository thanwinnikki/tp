package seedu.address.model.group.exceptions;

public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Group with that name already exists in the list!");
    }
}
