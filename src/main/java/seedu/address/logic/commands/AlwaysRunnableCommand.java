package seedu.address.logic.commands;

import seedu.address.logic.state.ApplicationStateType;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class AlwaysRunnableCommand implements StateDependentCommand {

    /**
     * Checks if the command can run while the application is currently in the given application state.
     *
     * @param applicationStateType The given application state.
     * @return True if the command can run while the application is currently in the given state, false otherwise.
     */
    @Override
    public boolean isAbleToRunInApplicationState(ApplicationStateType applicationStateType) {
        return true;
    }
}
