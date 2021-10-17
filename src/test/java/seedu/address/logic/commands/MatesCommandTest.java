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
 * {@code MatesCommand}.
 */
public class MatesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate personPredicate = prepareGroupMemberPredicate(groupToDisplay);
        IsGroupPredicate groupPredicate = prepareGroupPredicate(groupToDisplay);
        MatesCommand command = new MatesCommand(INDEX_FIRST);

        String expectedMessage = MatesCommand.MESSAGE_SUCCESS;
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MatesCommand command = new MatesCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate personPredicate = prepareGroupMemberPredicate(groupToDisplay);
        IsGroupPredicate groupPredicate = prepareGroupPredicate(groupToDisplay);
        MatesCommand command = new MatesCommand(INDEX_FIRST);

        String expectedMessage = MatesCommand.MESSAGE_SUCCESS;
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredGroupList(groupPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        MatesCommand command = new MatesCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MatesCommand matesFirstCommand = new MatesCommand(INDEX_FIRST);
        MatesCommand matesSecondCommand = new MatesCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(matesFirstCommand.equals(matesFirstCommand));

        // same values -> returns true
        MatesCommand MatesFirstCommandCopy = new MatesCommand(INDEX_FIRST);
        assertTrue(matesFirstCommand.equals(MatesFirstCommandCopy));

        // different types -> returns false
        assertFalse(matesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(matesFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(matesFirstCommand.equals(matesSecondCommand));
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
