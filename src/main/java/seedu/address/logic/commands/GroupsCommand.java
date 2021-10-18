package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import seedu.address.logic.AppState;
import seedu.address.model.Model;

/**
 * Lists all groups in the address book to the user.
 */
public class GroupsCommand extends Command {

    public static final String COMMAND_WORD = "groups";

    public static final String MESSAGE_SUCCESS = "Listed all groups";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult.Builder(MESSAGE_SUCCESS)
                .setNextAppState(AppState.GROUP_INFORMATION)
                .build();
    }
}
