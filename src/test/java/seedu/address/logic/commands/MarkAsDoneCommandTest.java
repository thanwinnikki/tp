package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
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
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class MarkAsDoneCommandTest {

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
    //
    //        // identify group to have its person removed from
    //        Group group = getFirstGroup(model);
    //        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(INDEX_FIRST);
    //
    //        // set the filtered list for both groups and person
    //        setFilteredList(model, group);
    //
    //        // set person to remove as the first person
    //        Task taskToMarkAsDone = new TaskBuilder(group.getTasks().getTask(INDEX_FIRST.getZeroBased())).build();
    //        String expectedMessage = String.format(MarkAsDoneCommand.MESSAGE_SUCCESS, taskToMarkAsDone);
    //
    //        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
    //                .displayGroupInformation(group)
    //                .build();
    //
    //        Model expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
    //        setFilteredList(expectedModel, getFirstGroup(expectedModel));
    //        getFirstGroup(expectedModel).getTasks().getTask(INDEX_FIRST.getZeroBased()).setDoneTask();
    //
    //        assertCommandSuccess(markAsDoneCommand, model, expectedCommandResult, expectedModel);
    //    }

    @Test
    public void execute_alreadyMarkedAsDone_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

        // identify group to mark task as done in
        Group group = getFirstGroup(model);

        // Task already marked as done
        group.getTasks().getTask(INDEX_FIRST.getZeroBased()).setDoneTask();
        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(INDEX_FIRST);

        // set the filtered list for both groups and person
        setFilteredList(model, group);

        assertThrows(CommandException.class, MarkAsDoneCommand.MESSAGE_TASK_ALREADY_DONE, ()
            -> markAsDoneCommand.execute(model));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

        Group group = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());

        // set the filtered list for both groups and person
        setFilteredList(model, group);

        Index outOfBoundIndex = Index.fromOneBased(group.getTasks().size() + 1);

        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(outOfBoundIndex);

        assertCommandFailure(markAsDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAsDoneCommand markAsDoneFirstCommand = new MarkAsDoneCommand(INDEX_FIRST);
        MarkAsDoneCommand markAsDoneSecondCommand = new MarkAsDoneCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markAsDoneFirstCommand.equals(markAsDoneFirstCommand));

        // same values -> returns true
        MarkAsDoneCommand markAsDoneFirstCommandCopy = new MarkAsDoneCommand(INDEX_FIRST);
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

    public void setFilteredList(Model model, Group group) {
        model.updateFilteredPersonList(new IsGroupMemberPredicate(group));
        model.updateFilteredGroupList(new IsGroupPredicate(group));
    }

}
