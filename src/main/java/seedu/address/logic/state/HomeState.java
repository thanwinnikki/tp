package seedu.address.logic.state;

public class HomeState implements ApplicationState {

    private static final ApplicationStateType APPLICATION_STATE_TYPE = ApplicationStateType.HOME;

    @Override
    public ApplicationStateType getApplicationStateType() {
        return APPLICATION_STATE_TYPE;
    }
}
