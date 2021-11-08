package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.ApplicationStateType;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

public class DeleteTaskCommand implements UndoableCommand, StateDependentCommand {

    public static final String COMMAND_WORD = "deleteT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the task identified by the index number used in the task list in the group"
            + " from the group.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_TASK_SUCCESS = "Successfully Removed Task: %1$s";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of deletion of task: %1$s";

    private static final Index firstIndex = Index.fromZeroBased(0);

    private final Index targetIndex;

    private ReadOnlyAddressBook oldReadOnlyAddressBook;
    private Group groupDeletedFrom;
    private Task deletedTask;
    private final Group group;

    /**
     * Constructor for DeleteTaskCommand
     * @param targetIndex of the person in the filtered list to be removed
     * @param group is the group where task will be deleted from
     */
    public DeleteTaskCommand(Index targetIndex, Group group) {
        this.targetIndex = targetIndex;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        oldReadOnlyAddressBook = new AddressBook(model.getAddressBook());

        oldReadOnlyAddressBook.getGroupList().forEach(group -> {
            if (group.equals(this.group)) {
                groupDeletedFrom = group;
            }
        });

        UniqueTaskList targetTaskList = group.getTasks();
        if (targetTaskList.getSize() < targetIndex.getOneBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task targetTask = targetTaskList.getTask(targetIndex.getZeroBased());

        group.deleteTask(targetTask);
        deletedTask = targetTask;
        return new CommandResult.Builder(String.format(MESSAGE_REMOVE_TASK_SUCCESS, targetTask))
                .displayGroupInformation(group)
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        // Probably not the best to save the whole address book but this is the easiest way to undo
        model.setAddressBook(oldReadOnlyAddressBook);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, deletedTask))
                .displayGroupInformation(groupDeletedFrom)
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
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)
                && group.equals(((DeleteTaskCommand) other).group)); // state check
    }

}
