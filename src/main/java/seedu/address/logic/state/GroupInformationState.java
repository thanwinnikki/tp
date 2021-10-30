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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GroupInformationState)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GroupInformationState otherGroupInformationState = (GroupInformationState) obj;
        return getStoredData().equals(otherGroupInformationState.getStoredData());
    }
}
