package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.testutil.TypicalGroups;

public class MarkAsDoneCommandTest {

    private Model model = new ModelManager(TypicalGroups.getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredList_success() {
        // identify group to have its person removed from
        Group group = getFirstGroup(model);
        Task taskToMarkAsDone = group.getTasks().getTask(INDEX_FIRST.getZeroBased());
        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(INDEX_FIRST, group);

        String expectedMessage = String.format(MarkAsDoneCommand.MESSAGE_SUCCESS, taskToMarkAsDone);
        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(group)
                .build();

        ModelManager expectedModel = new ModelManager(TypicalGroups.getTypicalAddressBookWithGroups(), new UserPrefs());
        getFirstGroup(expectedModel).getTasks().getTask(INDEX_FIRST.getZeroBased()).setDoneTask();

        assertCommandSuccess(markAsDoneCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_alreadyMarkedAsDone_throwsCommandException() {
        // identify group to mark task as done in
        Group group = getFirstGroup(model);

        // Task already marked as done
        group.getTasks().getTask(INDEX_FIRST.getZeroBased()).setDoneTask();
        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(INDEX_FIRST, group);

        assertThrows(CommandException.class, MarkAsDoneCommand.MESSAGE_TASK_ALREADY_DONE, ()
            -> markAsDoneCommand.execute(model));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Group group = getFirstGroup(model);

        Index outOfBoundIndex = Index.fromOneBased(group.getTasks().getSize() + 1);

        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(outOfBoundIndex, group);

        assertCommandFailure(markAsDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group group = getFirstGroup(model);
        Task taskToMarkAsDone = group.getTasks().getTask(INDEX_FIRST.getZeroBased());
        UndoableCommand markAsDoneCommand = new MarkAsDoneCommand(INDEX_FIRST, group);
        String expectedMessage = String.format(MarkAsDoneCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, taskToMarkAsDone);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(group)
                .build();
        assertUndoSuccess(markAsDoneCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        // identify group to have its person removed from
        Group group = getFirstGroup(model);
        MarkAsDoneCommand markAsDoneFirstCommand = new MarkAsDoneCommand(INDEX_FIRST, group);
        MarkAsDoneCommand markAsDoneSecondCommand = new MarkAsDoneCommand(INDEX_SECOND, group);

        // same object -> returns true
        assertTrue(markAsDoneFirstCommand.equals(markAsDoneFirstCommand));

        // same values -> returns true
        MarkAsDoneCommand markAsDoneFirstCommandCopy = new MarkAsDoneCommand(INDEX_FIRST, group);
        assertTrue(markAsDoneFirstCommand.equals(markAsDoneFirstCommandCopy));

        // different types -> returns false
        assertFalse(markAsDoneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markAsDoneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markAsDoneFirstCommand.equals(markAsDoneSecondCommand));
    }

    public Group getFirstGroup(Model model) {
        return model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
    }
}
