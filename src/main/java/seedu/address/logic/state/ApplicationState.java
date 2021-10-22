package seedu.address.logic.state;

import seedu.address.model.group.Group;

/**
 * An enum representing an application state.
 */
public enum ApplicationState {

    GROUP_INFORMATION(GroupInformationStateData.getInstance()),
    HOME(HomeStateData.getInstance());

    private final ApplicationStateData applicationStateData;

    ApplicationState(ApplicationStateData applicationStateData) {
        this.applicationStateData = applicationStateData;
    }

    public <T> T getData() {
        assert applicationStateData.isAbleToStoreData() : "This state does not store data.";
        Object data = applicationStateData.getData();
        return (T) data;
    }

    public <T> void setData(T data) {
        checkData(data);
        applicationStateData.setData(data);
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
