package seedu.address.model.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;

public class UniqueGroupList {
    // todo
    private final ObservableList<Group> internalList = FXCollections.observableArrayList();

    // todo
    public ObservableList<Group> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }
}
