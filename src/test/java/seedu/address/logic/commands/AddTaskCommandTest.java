//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.group.Group;
//import seedu.address.model.group.IsGroupPredicate;
//import seedu.address.model.person.IsGroupMemberPredicate;
//import seedu.address.model.task.Task;
//import seedu.address.testutil.TaskBuilder;
//
//public class AddTaskCommandTest {
//
//    @Test
//    public void constructor_nullTask_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
//    }
//
//    //   @Test
//    //    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
//    //        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
//    //
//    //        // identify group to add task to
//    //        Group group = getFirstGroup(model);
//    //        Task validTask = new TaskBuilder().build();
//    //        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
//    //
//    //        assertFalse(group.getTasks().contains(validTask));
//    //
//    //        // set the filtered list for both groups and person
//    //        setFilteredList(model, group);
//    //
//    //        CommandResult commandResult = new AddTaskCommand(validTask).execute(model);
//    //
//    //        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask),
//    commandResult.getFeedbackToUser());
//    //        assertTrue(group.getTasks().contains(validTask));
//    //    }
//
////    @Test
////    public void execute_duplicateTask_throwsCommandException() {
////        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
////
////        // identify group to add task to
////        Group group = getFirstGroup(model);
////
////        // duplicate first task already in the group
////        Task validTask = new TaskBuilder(group.getTasks().getTask(INDEX_FIRST.getZeroBased())).build();
////        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
////
////        // set the filtered list for both groups and person
////        setFilteredList(model, group);
////
////        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_DUPLICATE_TASK, ()
////            -> addTaskCommand.execute(model));
////    }
//
//    @Test
//    public void equals() {
//        Task taskA = new TaskBuilder().withDescription("Prepare pitch").build();
//        Task taskB = new TaskBuilder().withDescription("Prepare demo").build();
//        AddTaskCommand addTaskACommand = new AddTaskCommand(taskA);
//        AddTaskCommand addTaskBCommand = new AddTaskCommand(taskB);
//
//        // same object -> returns true
//        assertTrue(addTaskACommand.equals(addTaskACommand));
//
//        // same values -> returns true
//        AddTaskCommand addTaskACommandCopy = new AddTaskCommand(taskA);
//        assertTrue(addTaskACommand.equals(addTaskACommandCopy));
//
//        // different types -> returns false
//        assertFalse(addTaskACommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addTaskACommand.equals(null));
//
//        // different Task -> returns false
//        assertFalse(addTaskACommand.equals(addTaskBCommand));
//    }
//
//    public Group getFirstGroup(Model model) {
//        return model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
//    }
//
//    public void setFilteredList(Model model, Group group) {
//        model.updateFilteredPersonList(new IsGroupMemberPredicate(group));
//        model.updateFilteredGroupList(new IsGroupPredicate(group));
//    }
//}
