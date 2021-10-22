package seedu.address.logic.state;

import seedu.address.model.group.Group;

public class GroupInformationStateData extends ApplicationStateData<Group> {

    private static GroupInformationStateData instance;

    private Group group;

    private GroupInformationStateData() {}

    static GroupInformationStateData getInstance() {
        if (instance == null) {
            instance = new GroupInformationStateData();
        }
        return instance;
    }

    @Override
    public boolean isAbleToStoreData() {
        return true;
    }

    @Override
    public Group getData() {
        assert group != null : "There is no group stored." ;
        return group;
    }

    @Override
    public void setData(Group data) {
        group = data;
    }
}
