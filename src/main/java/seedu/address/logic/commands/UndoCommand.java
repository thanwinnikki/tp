package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the previous undoable command that modified the records.
 */
public class UndoCommand extends AlwaysRunnableCommand {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Command undone.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult.Builder(MESSAGE_SUCCESS)
                .goCauseUndo()
                .build();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UndoCommand)) {
            return false;
        }
        return other == this;
    }
}
