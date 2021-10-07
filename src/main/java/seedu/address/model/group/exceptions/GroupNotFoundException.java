package seedu.address.model.group.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("The group that you are finding does not exist!");
    }
}
