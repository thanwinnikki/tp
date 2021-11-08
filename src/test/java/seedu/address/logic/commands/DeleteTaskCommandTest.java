package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.TypicalGroups;

public class DeleteTaskCommandTest {

    private Model model = new ModelManager(TypicalGroups.getTypicalAddressBookWithGroups(), new UserPrefs());

    public Group getFirstGroup(Model model) {
        return model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
    }

    @Test
    public void execute_validTaskIndex_success() {
        Group groupToDeleteFrom = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        Task taskToDelete = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased())
                .getTasks().getTask(INDEX_FIRST.getZeroBased());
        int initialSizeOfTaskList = groupToDeleteFrom.getTasks().getSize();

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, groupToDeleteFrom);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_REMOVE_TASK_SUCCESS, taskToDelete);
        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(groupToDeleteFrom)
                .build();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        getFirstGroup(expectedModel).deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedCommandResult, expectedModel);

        int currentSizeOfTaskList = groupToDeleteFrom.getTasks().getSize();
        // ensures that the group's task list size decreases
        assertEquals(initialSizeOfTaskList - 1, currentSizeOfTaskList);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(getFirstGroup(model).getTasks().getSize() + 1);
        Group groupToDeleteFrom = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex, groupToDeleteFrom);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group groupToDeleteFrom = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        Task taskToDelete = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased())
                .getTasks().getTask(INDEX_FIRST.getZeroBased());
        UndoableCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, groupToDeleteFrom);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, taskToDelete);
        Group expectedGroup = new GroupBuilder(groupToDeleteFrom).build();
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(expectedGroup)
                .build();
        assertUndoSuccess(deleteTaskCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        Group groupToDeleteFrom = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        Group differentGroupToDeleteFrom = model.getFilteredGroupList().get(INDEX_FIRST.getOneBased());
        DeleteTaskCommand deleteFirstTaskCommand = new DeleteTaskCommand(INDEX_FIRST, groupToDeleteFrom);
        DeleteTaskCommand deleteSecondTaskCommand = new DeleteTaskCommand(INDEX_SECOND, groupToDeleteFrom);
        DeleteTaskCommand deleteTaskDifferentGroup = new DeleteTaskCommand(INDEX_FIRST, differentGroupToDeleteFrom);

        // same object -> returns true
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstTaskCommandCopy = new DeleteTaskCommand(INDEX_FIRST, groupToDeleteFrom);
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstTaskCommand.equals(deleteSecondTaskCommand));

        // different group -> returns false
        assertFalse(deleteFirstTaskCommand.equals(deleteTaskDifferentGroup));
    }

}
