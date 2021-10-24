package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.IsGroupPredicate;
import seedu.address.model.person.IsGroupMemberPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code groupCommand}.
 */
public class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate personPredicate = prepareGroupMemberPredicate(groupToDisplay);
        IsGroupPredicate groupPredicate = prepareGroupPredicate(groupToDisplay);
        GroupCommand command = new GroupCommand(INDEX_FIRST);

        String expectedMessage = GroupCommand.MESSAGE_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(groupToDisplay)
                .build();
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GroupCommand command = new GroupCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate personPredicate = prepareGroupMemberPredicate(groupToDisplay);
        IsGroupPredicate groupPredicate = prepareGroupPredicate(groupToDisplay);
        GroupCommand command = new GroupCommand(INDEX_FIRST);

        String expectedMessage = GroupCommand.MESSAGE_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(groupToDisplay)
                .build();
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        GroupCommand command = new GroupCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
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

    /**
     * Parses {@code userInput} into a {@code IsGroupMemberPredicate}.
     */
    private IsGroupMemberPredicate prepareGroupMemberPredicate(Group userInput) {
        return new IsGroupMemberPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code IsGroupPredicate}.
     */
    private IsGroupPredicate prepareGroupPredicate(Group userInput) {
        return new IsGroupPredicate(userInput);
    }
}
