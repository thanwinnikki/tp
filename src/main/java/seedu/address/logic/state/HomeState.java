package seedu.address.logic.state;

import java.util.Objects;

public class HomeState implements ApplicationState {

    private static final ApplicationStateType APPLICATION_STATE_TYPE = ApplicationStateType.HOME;

    @Override
    public ApplicationStateType getApplicationStateType() {
        return APPLICATION_STATE_TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(APPLICATION_STATE_TYPE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof HomeState;
    }
}
