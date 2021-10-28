package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.IsGroupMemberPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class RemoveCommand extends AlwaysRunnableCommand implements UndoableCommand {

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
    private final Group currentDataStored;

    /**
     * Constructor for RemoveCommand
     * @param targetIndex of the person in the filtered list to be removed
     * @param currentDataStored is the group where person will be removed from
     */
    public RemoveCommand(Index targetIndex, Object currentDataStored) {
        this.targetIndex = targetIndex;
        if (currentDataStored instanceof Group) {
            this.currentDataStored = (Group) currentDataStored;
        } else {
            this.currentDataStored = null;
        }

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<Group> lastShownGroupList = model.getFilteredGroupList();
        if (currentDataStored == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Person personToRemove = lastShownPersonList.get(targetIndex.getZeroBased());
        personRemoved = personToRemove;
        Group group = (Group) currentDataStored;
        groupWithoutRemoval = new Group(group);
        UniquePersonList persons = group.getPersons();
        persons.remove(personToRemove);
        groupWithRemoval = group;
        model.updateFilteredPersonList(new IsGroupMemberPredicate(group));
        return new CommandResult.Builder(String.format(MESSAGE_REMOVE_PERSON_SUCCESS, personToRemove))
                .displayGroupInformation(group)
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(groupWithRemoval) : "The group removed from must still exist to undo the removal.";
        model.setGroup(groupWithRemoval, groupWithoutRemoval);
        model.updateFilteredPersonList(new IsGroupMemberPredicate(groupWithoutRemoval));
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, personRemoved))
                .displayGroupInformation(groupWithoutRemoval)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)
                && (currentDataStored).equals(((RemoveCommand) other).currentDataStored)); // state check
    }
}
