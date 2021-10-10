package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person identified by the index number used in the persons list in the group"
            + " from the group.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Removed Person: %1$s";

    private final Index targetIndex;

    private final Index firstIndex = Index.fromZeroBased(0);

    public RemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<Group> lastShownGroupList = model.getFilteredGroupList();
        if (lastShownGroupList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Person personToRemove = lastShownPersonList.get(targetIndex.getZeroBased());
        Group group = lastShownGroupList.get(firstIndex.getZeroBased());
        UniquePersonList persons = group.getPersons();
        persons.remove(personToRemove);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToRemove));
    }
}
