package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the task identified by the index number used in the task list in the group"
            + " from the group.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_TASK_SUCCESS = "Successfully Removed Task: %1$s";

    private static final Index firstIndex = Index.fromZeroBased(0);

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownGroupList = model.getFilteredGroupList();
        if (lastShownGroupList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group targetGroup = lastShownGroupList.get(firstIndex.getZeroBased());
        UniqueTaskList targetTaskList = targetGroup.getTasks();
        if (targetTaskList.size() < targetIndex.getOneBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task targetTask = targetTaskList.getTask(targetIndex.getZeroBased());

        targetGroup.deleteTask(targetTask);
        return new CommandResult(String.format(MESSAGE_REMOVE_TASK_SUCCESS, targetTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }

}
