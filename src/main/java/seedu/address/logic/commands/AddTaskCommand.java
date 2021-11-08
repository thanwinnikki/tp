package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.ApplicationStateType;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Adds a person to the address book.
 */
public class AddTaskCommand implements UndoableCommand, StateDependentCommand {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the group. "
            + "Note that the task's description can only be displayed up to the first 70 characters.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "TASK_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Read book";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the group";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of addition of task: %1$s";

    private final Task toAdd;

    private Group groupAddedTo;
    private final Group groupToAddTo;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     *
     * @param task to be added to the {@Group}
     * @param group for the {@code Task} to be added to.
     */
    public AddTaskCommand(Task task, Group group) {
        requireNonNull(task);
        toAdd = task;
        groupToAddTo = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert groupToAddTo != null : "Group object should not be null";
        UniqueTaskList tasks = groupToAddTo.getTasks();

        // If task already exists in the task list
        if (tasks.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        tasks.add(toAdd);
        groupAddedTo = groupToAddTo;
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, toAdd))
                .displayGroupInformation(groupToAddTo)
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(groupAddedTo) : "The group of the added task must still exist to undo its addition.";
        assert groupAddedTo.hasTask(toAdd) : "The group must have the added task to undo its addition.";
        groupAddedTo.deleteTask(toAdd);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, toAdd))
                .displayGroupInformation(groupAddedTo)
                .build();
    }

    @Override
    public boolean isAbleToRunInApplicationState(ApplicationState applicationState) {
        ApplicationStateType applicationStateType = applicationState.getApplicationStateType();
        return applicationStateType == ApplicationStateType.GROUP_INFORMATION;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
