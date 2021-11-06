package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        // identify group to have its person removed from
        Group group = getFirstGroup(model);
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, group));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {

        // identify group to add task to
        Group group = getFirstGroup(model);
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask, group);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask);
        CommandResult expectedCommandResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(group)
                .build();

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        getFirstGroup(expectedModel).addTask(validTask);

        assertCommandSuccess(addTaskCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        // identify group to add task to
        Group group = getFirstGroup(model);
        Task validTask = group.getTasks().getTask(INDEX_FIRST.getZeroBased());
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask, group);

        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_DUPLICATE_TASK, () ->
                addTaskCommand.execute(model));
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group group = CS2103T_GROUP_BUILDER.build();
        Task task = new TaskBuilder().build();
        UndoableCommand addTaskCommand = new AddTaskCommand(task, group);
        String expectedMessage = String.format(AddTaskCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, task);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .displayGroupInformation(group)
                .build();
        assertUndoSuccess(addTaskCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        // identify group to have its person removed from
        Group group = getFirstGroup(model);
        Task taskA = new TaskBuilder().withDescription("Prepare pitch").build();
        Task taskB = new TaskBuilder().withDescription("Prepare demo").build();
        AddTaskCommand addTaskACommand = new AddTaskCommand(taskA, group);
        AddTaskCommand addTaskBCommand = new AddTaskCommand(taskB, group);

        // same object -> returns true
        assertTrue(addTaskACommand.equals(addTaskACommand));

        // same values -> returns true
        AddTaskCommand addTaskACommandCopy = new AddTaskCommand(taskA, group);
        assertTrue(addTaskACommand.equals(addTaskACommandCopy));

        // different types -> returns false
        assertFalse(addTaskACommand.equals(1));

        // null -> returns false
        assertFalse(addTaskACommand.equals(null));

        // different Task -> returns false
        assertFalse(addTaskACommand.equals(addTaskBCommand));
    }

    public Group getFirstGroup(Model model) {
        return model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
    }

}
