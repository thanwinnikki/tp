package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
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
import seedu.address.model.person.IsGroupMemberPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MatesCommand}.
 */
public class MatesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate predicate = preparePredicate(groupToDisplay);
        MatesCommand command = new MatesCommand(INDEX_FIRST);

        String expectedMessage = MatesCommand.MESSAGE_SUCCESS;

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MatesCommand MatesCommand = new MatesCommand(outOfBoundIndex);

        assertCommandFailure(MatesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Group groupToDisplay = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        IsGroupMemberPredicate predicate = preparePredicate(groupToDisplay);
        MatesCommand command = new MatesCommand(INDEX_FIRST);
        String expectedMessage = MatesCommand.MESSAGE_SUCCESS;
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        MatesCommand MatesCommand = new MatesCommand(outOfBoundIndex);

        assertCommandFailure(MatesCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MatesCommand MatesFirstCommand = new MatesCommand(INDEX_FIRST);
        MatesCommand MatesSecondCommand = new MatesCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(MatesFirstCommand.equals(MatesFirstCommand));

        // same values -> returns true
        MatesCommand MatesFirstCommandCopy = new MatesCommand(INDEX_FIRST);
        assertTrue(MatesFirstCommand.equals(MatesFirstCommandCopy));

        // different types -> returns false
        assertFalse(MatesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(MatesFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(MatesFirstCommand.equals(MatesSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code IsGroupMemberPredicate}.
     */
    private IsGroupMemberPredicate preparePredicate(Group userInput) {
        return new IsGroupMemberPredicate(userInput);
    }
}
