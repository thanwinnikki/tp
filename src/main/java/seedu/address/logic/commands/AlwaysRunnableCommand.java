package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class AlwaysRunnableCommand implements StateDependentCommand {

    /**
     * Checks if the command can run while the application is currently in the given application state.
     *
     * @param applicationState The given application state.
     * @return True if the command can run while the application is currently in the given state, false otherwise.
     */
    @Override
    public boolean isAbleToRunInApplicationState(ApplicationState applicationState) {
        return true;
    }
}
