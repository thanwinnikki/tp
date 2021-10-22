package seedu.address.logic.state;

import seedu.address.model.group.Group;

/**
 * Represents the data store for {@code ApplicationState.GROUP_INFORMATION}.
 */
public class GroupInformationStateDataStore extends ApplicationStateDataStore<Group> {

    private static GroupInformationStateDataStore instance;

    private Group group;

    private GroupInformationStateDataStore() {}

    static GroupInformationStateDataStore getInstance() {
        if (instance == null) {
            instance = new GroupInformationStateDataStore();
        }
        return instance;
    }

    @Override
    public boolean isAbleToStoreData() {
        return true;
    }

    @Override
    public Group getData() {
        assert group != null : "There is no group stored.";
        return group;
    }

    @Override
    public void setData(Group data) {
        group = data;
    }
}
