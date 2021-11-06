package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;

public class AddGroupCommandTest {

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGroupAdded modelStub = new ModelStubAcceptingGroupAdded();
        Group validGroup = new GroupBuilder().build();

        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group validGroup = new GroupBuilder().build();
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);
        ModelStub modelStub = new ModelStubWithGroup(validGroup);

        assertThrows(CommandException.class, AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () ->
                addGroupCommand.execute(modelStub));
    }

    @Test
    public void undo_validPrecondition_successfulUndo() throws CommandException {
        UndoModelStub modelStub = new UndoModelStub();
        Group group = new GroupBuilder().build();
        AddGroupCommand addGroupCommand = new AddGroupCommand(group);
        addGroupCommand.execute(modelStub);
        assertTrue(modelStub.hasGroup(group));

        CommandResult actualUndoResult = addGroupCommand.undo(modelStub);
        String expectedMessage = String.format(AddGroupCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, group);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .build();
        assertEquals(expectedUndoResult, actualUndoResult);
        assertFalse(modelStub.hasGroup(group));
        assertEquals(new ArrayList<>(), modelStub.groupsAdded);
    }

    @Test
    public void undo_validPreconditionIntegration_successfulUndo() {
        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        Group group = new GroupBuilder().build();
        UndoableCommand addGroupCommand = new AddGroupCommand(group);
        String expectedMessage = String.format(AddGroupCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, group);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .build();
        assertUndoSuccess(addGroupCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        Group tennis = new GroupBuilder().withName("Tennis").build();
        Group swimming = new GroupBuilder().withName("Swimming").build();
        AddGroupCommand tennisAddGroupCommand = new AddGroupCommand(tennis);
        AddGroupCommand swimmingAddGroupCommand = new AddGroupCommand(swimming);

        // same object -> returns true
        assertTrue(tennisAddGroupCommand.equals(tennisAddGroupCommand));

        // same values -> returns true
        AddGroupCommand tennisAddGroupCommandCopy = new AddGroupCommand(tennis);
        assertTrue(tennisAddGroupCommand.equals(tennisAddGroupCommandCopy));

        // different types -> returns false
        assertFalse(tennisAddGroupCommand.equals(1));

        // null -> returns false
        assertFalse(tennisAddGroupCommand.equals(null));

        // different person -> returns false
        assertFalse(tennisAddGroupCommand.equals(swimmingAddGroupCommand));

    }


    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Person> getFilteredPersonListPredicate() {
            return null;
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<? super Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Group> getFilteredGroupListPredicate() {
            return null;
        }

        @Override
        public void addToGroup(Group target, Set<Person> persons) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithGroup extends AddGroupCommandTest.ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingGroupAdded extends AddGroupCommandTest.ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();
        final FilteredList<Group> filteredGroups = new FilteredList<>(getTypicalAddressBookWithGroups().getGroupList());

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public void updateFilteredGroupList(Predicate<? super Group> predicate) {
            requireNonNull(predicate);
            filteredGroups.setPredicate(predicate);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class UndoModelStub extends ModelStubAcceptingGroupAdded {

        @Override
        public void deleteGroup(Group target) {
            groupsAdded.remove(target);
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {}

        @Override
        public Predicate<? super Person> getFilteredPersonListPredicate() {
            return null;
        }

        @Override
        public void updateFilteredGroupList(Predicate<? super Group> predicate) {}

        @Override
        public Predicate<? super Group> getFilteredGroupListPredicate() {
            return null;
        }
    }
}
