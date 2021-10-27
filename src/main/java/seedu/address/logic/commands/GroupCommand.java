package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.IsGroupPredicate;
import seedu.address.model.person.IsGroupMemberPredicate;

/**
 * Lists all persons and tasks in the given group.
 */
public class GroupCommand extends AlwaysRunnableCommand {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists members and tasks in group index indicated."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all members and tasks in group.";

    private final Index index;

    public GroupCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredGroupList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        Group group = lastShownList.get(index.getZeroBased());
        model.updateFilteredPersonList(new IsGroupMemberPredicate(group));
        model.updateFilteredGroupList(new IsGroupPredicate(group));
        return new CommandResult.Builder(MESSAGE_SUCCESS).displayGroupInformation(group)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCommand // instanceof handles nulls
                && index.equals(((GroupCommand) other).index)); // state check
    }
}
