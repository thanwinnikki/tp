package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.ApplicationStateType;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Remove a specified person from a specified group in the address book.
 * The person is specified by its displayed person index in the group.
 */
public class RemoveCommand implements UndoableCommand, StateDependentCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person identified by the index number used in the persons list in the group"
            + " from the group.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_PERSON_SUCCESS = "Removed Person: %1$s";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of removal of group mate: %1$s";

    private final Index targetIndex;

    private final Index firstIndex = Index.fromZeroBased(0);

    private Person personRemoved;
    private Group groupWithRemoval;
    private Group groupWithoutRemoval;
    private final Group groupToRemoveFrom;

    /**
     * Constructor for RemoveCommand
     * @param targetIndex of the person in the filtered list to be removed
     * @param group is the group where person will be removed from
     */
    public RemoveCommand(Index targetIndex, Group group) {
        this.targetIndex = targetIndex;
        groupToRemoveFrom = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= groupToRemoveFrom.getPersons().asUnmodifiableObservableList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }


        if (groupToRemoveFrom == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Person personToRemove = lastShownPersonList.get(targetIndex.getZeroBased());
        personRemoved = personToRemove;

        Group group = groupToRemoveFrom;
        groupWithoutRemoval = new Group(group);
        UniquePersonList persons = group.getPersons();
        persons.remove(personToRemove);
        groupWithRemoval = group;
        return new CommandResult.Builder(String.format(MESSAGE_REMOVE_PERSON_SUCCESS, personToRemove))
                .displayGroupInformation(group)
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(groupWithRemoval) : "The group removed from must still exist to undo the removal.";
        model.setGroup(groupWithRemoval, groupWithoutRemoval);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, personRemoved))
                .displayGroupInformation(groupWithoutRemoval)
                .build();
    }

    /**
     * Returns true if command can be run in current application state.
     * Command can run in GROUP_INFORMATION application state.
     * @param applicationState The given application state.
     */
    @Override
    public boolean isAbleToRunInApplicationState(ApplicationState applicationState) {
        ApplicationStateType applicationStateType = applicationState.getApplicationStateType();
        return applicationStateType == ApplicationStateType.GROUP_INFORMATION;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)
                && (groupToRemoveFrom).equals(((RemoveCommand) other).groupToRemoveFrom)); // state check
    }
}
