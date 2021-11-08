package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.ApplicationStateType;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Marks a specified task as done in the specified group as done.
 * The task is specified by its displayed task index in the specified group.
 */
public class MarkAsDoneCommand implements UndoableCommand, StateDependentCommand {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as done "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task marked as done: %1$s";
    public static final String MESSAGE_TASK_ALREADY_DONE = "This task has already been marked as done!";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of marking as done for task: %1$s";

    private final Index targetIndex;

    private Group groupOfTask;
    private Task taskMarkedDone;
    private final Group taskGroup;

    /**
     * Constructor for MarkAsDoneCommand
     * @param targetIndex of the task in the filtered list to be marked as done
     * @param group is the group where the task will be marked as done
     */
    public MarkAsDoneCommand(Index targetIndex, Group group) {
        this.targetIndex = targetIndex;
        taskGroup = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        UniqueTaskList tasks = taskGroup.getTasks();

        if (targetIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (taskGroup == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Task taskToMarkAsDone = tasks.getTask(targetIndex.getZeroBased());
        if (taskToMarkAsDone.getDoneTask()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_DONE);
        } else {
            taskToMarkAsDone.setDoneTask();
            groupOfTask = taskGroup;
            taskMarkedDone = taskToMarkAsDone;
        }
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, taskToMarkAsDone))
                .displayGroupInformation(taskGroup)
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
                || (other instanceof MarkAsDoneCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAsDoneCommand) other).targetIndex)); // state check
    }
}
