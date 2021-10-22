package seedu.address.logic.commands;

import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Checks if the command can run while the application is currently in the given application state.
     *
     * @param applicationState The given application state.
     * @return True if the command can run while the application is currently in the given state, false otherwise.
     */
    public boolean isAbleToRunInAppState(ApplicationState applicationState) {
        return true;
    }
}
