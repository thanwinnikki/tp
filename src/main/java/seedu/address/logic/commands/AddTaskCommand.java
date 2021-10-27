package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Adds a person to the address book.
 */
public class AddTaskCommand extends AlwaysRunnableCommand implements UndoableCommand {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the group. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "TASK_DESCRIPTION"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Read book";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the group";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of addition of task: %1$s";

    private final Task toAdd;
    private final Index firstIndex = Index.fromZeroBased(0);

    private Group groupAddedTo;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
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
        if (tasks.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        tasks.add(toAdd);
        groupAddedTo = group;
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, toAdd))
                .displayGroupInformation(group)
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
