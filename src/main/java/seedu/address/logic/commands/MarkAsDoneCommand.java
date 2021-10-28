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

public class MarkAsDoneCommand extends AlwaysRunnableCommand implements UndoableCommand {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as done "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task marked as done: %1$s";
    public static final String MESSAGE_TASK_ALREADY_DONE = "This task has already been marked as done!";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of marking as done for task: %1$s";

    private final Index targetIndex;

    private final Index firstIndex = Index.fromZeroBased(0);

    private Group groupOfTask;
    private Task taskMarkedDone;
    private final Group currentDataStored;

    public MarkAsDoneCommand(Index targetIndex, Object currentDataStored) {
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

        UniqueTaskList tasks = currentDataStored.getTasks();

        if (targetIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (currentDataStored == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Task taskToMarkAsDone = tasks.getTask(targetIndex.getZeroBased());
        if (taskToMarkAsDone.getDoneTask()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_DONE);
        } else {
            taskToMarkAsDone.setDoneTask();
            groupOfTask = currentDataStored;
            taskMarkedDone = taskToMarkAsDone;
        }
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, taskToMarkAsDone))
                .displayGroupInformation(currentDataStored)
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(groupOfTask) : "The group of the task marked done must still exist to undo the mark.";
        assert groupOfTask.hasTask(taskMarkedDone) : "The group must still have the task marked done to undo the mark.";
        assert taskMarkedDone.getDoneTask() : "The task marked done must still be marked done to undo the mark.";
        taskMarkedDone.setUndoneTask();
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, taskMarkedDone))
                .displayGroupInformation(groupOfTask)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAsDoneCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAsDoneCommand) other).targetIndex)); // state check
    }
}
