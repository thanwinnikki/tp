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

public class MarkAsDoneCommand extends AlwaysRunnableCommand {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as done "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task marked as done: %1$s";
    public static final String MESSAGE_TASK_ALREADY_DONE = "This task has already been marked as done!";

    private final Index targetIndex;

    private final Index firstIndex = Index.fromZeroBased(0);

    public MarkAsDoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownGroupList = model.getFilteredGroupList();
        if (lastShownGroupList.size() != 1) {
            //todo
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        Group group = lastShownGroupList.get(firstIndex.getZeroBased());
        UniqueTaskList tasks = group.getTasks();

        if (targetIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkAsDone = tasks.getTask(targetIndex.getZeroBased());
        if (taskToMarkAsDone.getDoneTask()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_DONE);
        } else {
            taskToMarkAsDone.setDoneTask();
        }
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, taskToMarkAsDone))
                .displayGroupInformation(group)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAsDoneCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAsDoneCommand) other).targetIndex)); // state check
    }
}
