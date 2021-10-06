package seedu.address.model.group.exceptions;

import seedu.address.model.person.exceptions.DuplicatePersonException;

public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Group with that name already exists in the list!");
    }
}
