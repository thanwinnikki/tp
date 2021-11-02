package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends AlwaysRunnableCommand implements UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ThunderCat has been cleared!";
    public static final String MESSAGE_UNDO_SUCCESS = "Successful undo of clear.";

    private ReadOnlyAddressBook oldReadOnlyAddressBook;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        oldReadOnlyAddressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.setAddressBook(oldReadOnlyAddressBook);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredGroupList(Model.PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult.Builder(MESSAGE_UNDO_SUCCESS)
                .goToHome()
                .build();
    }
}
