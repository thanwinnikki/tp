package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
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
 * {@code GroupCommand}.
 */
public class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Group group = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        assertCommandSuccess(new GroupCommand(INDEX_FIRST), model,
                new CommandResult.Builder(GroupCommand.MESSAGE_SUCCESS)
                        .displayGroupInformation(group)
                        .build(),
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        GroupCommand groupCommand = new GroupCommand(outOfBoundIndex);

        assertCommandFailure(groupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        GroupCommand groupCommand = new GroupCommand(outOfBoundIndex);

        assertCommandFailure(groupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GroupCommand groupFirstCommand = new GroupCommand(INDEX_FIRST);
        GroupCommand groupSecondCommand = new GroupCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(groupFirstCommand.equals(groupFirstCommand));

        // same values -> returns true
        GroupCommand groupFirstCommandCopy = new GroupCommand(INDEX_FIRST);
        assertTrue(groupFirstCommand.equals(groupFirstCommandCopy));

        // different types -> returns false
        assertFalse(groupFirstCommand.equals(1));

        // null -> returns false
        assertFalse(groupFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(groupFirstCommand.equals(groupSecondCommand));
    }
}
