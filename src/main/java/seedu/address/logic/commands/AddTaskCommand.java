package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
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
            + "Parameters: "
            + PREFIX_DESCRIPTION + "TASK_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Read book";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the group";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of addition of task: %1$s";

    private final Task toAdd;

    private Group groupAddedTo;
    private final Group currentDataStored;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task, Object currentDataStored) {
        requireNonNull(task);
        toAdd = task;
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
        if (tasks.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        if (currentDataStored == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        tasks.add(toAdd);
        groupAddedTo = currentDataStored;
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, toAdd))
                .displayGroupInformation(currentDataStored)
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
        if (applicationState == ApplicationState.GROUP_INFORMATION) {
            return true;
        } else {
            return false;
        }
    };

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
