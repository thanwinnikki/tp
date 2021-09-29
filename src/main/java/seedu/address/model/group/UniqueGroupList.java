package seedu.address.model.group;

import javafx.collections.ObservableList;

public class UniqueGroupList {

    private final ObservableList<Group> internalUnmodifiableList = null;

    public void remove(Group key) {
        System.out.println("hello");
    }

    public ObservableList<Group> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
}
