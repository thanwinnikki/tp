package seedu.address.logic.state;

import seedu.address.model.group.Group;

/**
 * An enum representing an application state.
 */
public enum ApplicationState {

    GROUP_INFORMATION(GroupInformationStateDataStore.getInstance()),
    HOME(HomeStateDataStore.getInstance());

    private final ApplicationStateDataStore applicationStateDataStore;

    ApplicationState(ApplicationStateDataStore applicationStateDataStore) {
        this.applicationStateDataStore = applicationStateDataStore;
    }

    public <T> T getData() {
        assert applicationStateDataStore.isAbleToStoreData() : "This state does not store data.";
        Object data = applicationStateDataStore.getData();
        return (T) data;
    }

    public <T> void setData(T data) {
        checkData(data);
        applicationStateDataStore.setData(data);
    }

    private <T> void checkData(T data) {
        switch (this) {
        case GROUP_INFORMATION:
            assert data instanceof Group : String.format("Data should be of %s type.", Group.class.getSimpleName());
            break;
        case HOME:
            // explicit fall-through
        default:
            assert false : "This state does not store data.";
        }
    }
}
