package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
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
import seedu.address.model.group.IsGroupPredicate;
import seedu.address.model.person.IsGroupMemberPredicate;
import seedu.address.model.person.Person;

public class RemoveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {

        // identify group to have its person removed from
        Group group = getFirstGroup(model);
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST);

        // set the filtered list for both groups and person
        setFilteredList(model, group);

        // set person to remove as the first person
        Person personToRemove = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_PERSON_SUCCESS, personToRemove);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        setFilteredList(expectedModel, getFirstGroup(expectedModel));

        getFirstGroup(expectedModel).getPersons().remove(personToRemove);
        setFilteredList(expectedModel, getFirstGroup(expectedModel));

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Group group = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());

        // set the filtered list for both groups and person
        setFilteredList(model, group);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemoveCommand removeFirstCommand = new RemoveCommand(INDEX_FIRST);
        RemoveCommand removeSecondCommand = new RemoveCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveCommand removeFirstCommandCopy = new RemoveCommand(INDEX_FIRST);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }

    public Group getFirstGroup(Model model) {
        return model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
    }

    public void setFilteredList(Model model, Group group) {
        model.updateFilteredPersonList(new IsGroupMemberPredicate(group));
        model.updateFilteredGroupList(new IsGroupPredicate(group));
    }

}
