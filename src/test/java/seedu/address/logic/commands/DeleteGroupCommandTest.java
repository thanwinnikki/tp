package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.logic.commands.DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteGroupCommand}.
 */
public class DeleteGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredGroupList_success() {
        // ensures that the filtered group list has at least one element
        assertTrue(model.getFilteredGroupList().size() > 0);

        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteGroup(groupToDelete);

        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(outOfBoundIndex);

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showGroupAtIndex(model, INDEX_FIRST);

        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteGroup(groupToDelete);
        showNoGroup(expectedModel);

        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(outOfBoundIndex);

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        UndoableCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_FIRST);
        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, groupToDelete);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .goToHome()
                .build();
        assertUndoSuccess(deleteGroupCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        DeleteGroupCommand deleteFirstGroupCommand = new DeleteGroupCommand(INDEX_FIRST);
        DeleteGroupCommand deleteSecondGroupCommand = new DeleteGroupCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstGroupCommand.equals(deleteFirstGroupCommand));

        // same values -> returns true
        DeleteGroupCommand deleteFirstGroupCommandCopy = new DeleteGroupCommand(INDEX_FIRST);
        assertTrue(deleteFirstGroupCommand.equals(deleteFirstGroupCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstGroupCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstGroupCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstGroupCommand.equals(deleteSecondGroupCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(g -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}

