package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteGroupCommand extends AlwaysRunnableCommand implements UndoableCommand {

    public static final String COMMAND_WORD = "deleteG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of deletion of group: %1$s";

    private final Index targetIndex;

    private ReadOnlyAddressBook oldReadOnlyAddressBook;
    private Group deletedGroup;

    public DeleteGroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        oldReadOnlyAddressBook = new AddressBook(model.getAddressBook());

        List<Group> lastShownList = model.getFilteredGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteGroup(groupToDelete);
        deletedGroup = groupToDelete;
        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete));
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        // Probably not the best to save the whole address book but this is the easiest way to undo
        model.setAddressBook(oldReadOnlyAddressBook);
        model.updateFilteredGroupList(Model.PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, deletedGroup))
                .goToHome()
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGroupCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteGroupCommand) other).targetIndex)); // state check
    }
}
