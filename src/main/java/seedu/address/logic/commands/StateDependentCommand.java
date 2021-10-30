package seedu.address.logic.commands;

import seedu.address.logic.state.ApplicationStateType;

/**
 * Represents a command that can only be executed when the application is in certain states.
 */
public interface StateDependentCommand extends Command {

    /**
     * Checks if the command can run while the application is currently in the given application state.
     *
     * @param applicationStateType The given application state.
     * @return True if the command can run while the application is currently in the given state, false otherwise.
     */
    boolean isAbleToRunInApplicationState(ApplicationStateType applicationStateType);
}
