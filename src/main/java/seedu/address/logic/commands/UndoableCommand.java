package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a {@code Command} that can be undone.
 */
public interface UndoableCommand extends Command {

    /**
     * Undoes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of undoing the command including the feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult undo(Model model) throws CommandException;
}
