package seedu.address.logic.state;

/**
 * An enum representing an application state.
 */
public enum ApplicationState {
    HOME(HomeStateData.getInstance()),
    GROUP_INFORMATION(GroupInformationStateData.getInstance());

    private final ApplicationStateData applicationStateData;

    ApplicationState(ApplicationStateData applicationStateData) {
        this.applicationStateData = applicationStateData;
    }

    public <T> ApplicationStateData<T> getApplicationStateData() {
        return applicationStateData;
    }
}
