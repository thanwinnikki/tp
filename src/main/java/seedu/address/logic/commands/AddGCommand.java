package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/** Adds a person to a group in the address book.
 *
 */
public class AddGCommand extends Command {

    public static final String COMMAND_WORD = "addG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds person to a group identified by the index number used in the displayed "
            + "person and group lists in the address book.\n "
            + "Parameters: PERSON_INDEX (must be a positive integer)\n"
            + "GROUP_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " p/1 g/2";

    public static final String MESSAGE_SUCCESS = "Person(s) added to group : %1$s";
    public static final String MESSAGE_INVALID_GROUP_INDEX = "Please enter a valid group number";
    public static final String MESSAGE_INVALID_PERSON_INDEX = "Please enter all valid person index";

    private final Index groupIndex;
    private final Index personIndex;

    /**
     * Creates an AddGCommand to add the specified {@code Person} objects to the specified {@code Group}.
     * @param groupIndex
     * @param personIndex
     */
    public AddGCommand(Index groupIndex, Index personIndex) {
        requireAllNonNull(groupIndex, personIndex);
        this.groupIndex = groupIndex;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownGroupList = model.getFilteredGroupList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (groupIndex.getZeroBased() >= lastShownGroupList.size()) {
            throw new CommandException(MESSAGE_INVALID_GROUP_INDEX);
        }

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_INDEX);
        }

        Group groupToChange = lastShownGroupList.get(groupIndex.getZeroBased());
        model.deleteGroup(groupToChange); //delete the old group
        groupToChange.add(lastShownPersonList.get(personIndex.getZeroBased()));
        model.addGroup(groupToChange);

        return new CommandResult(String.format(MESSAGE_SUCCESS, groupToChange));

    }
}
