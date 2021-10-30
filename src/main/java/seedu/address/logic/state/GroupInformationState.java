package seedu.address.logic.state;

import seedu.address.model.group.Group;

public class GroupInformationState extends StoredDataApplicationState<Group> {

    private static final ApplicationStateType APPLICATION_STATE_TYPE = ApplicationStateType.GROUP_INFORMATION;

    public GroupInformationState(Group dataToStore) {
        super(dataToStore);
    }

    @Override
    public ApplicationStateType getApplicationStateType() {
        return APPLICATION_STATE_TYPE;
    }
}
