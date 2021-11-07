package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VOLLEYBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_VOLLEYBALL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.EditGroupDescriptorBuilder;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.TypicalGroups;

public class EditGroupCommandTest {
    private Model model = new ModelManager(TypicalGroups.getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Group firstGroup = model.getFilteredGroupList().get(0);
        GroupBuilder groupInList = new GroupBuilder(firstGroup);
        Group editedGroup = groupInList.withName(VALID_NAME_TENNIS).withDescription(VALID_DESCRIPTION_SPORTS)
                .build();

        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(editedGroup).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(model.getFilteredGroupList().get(0), editedGroup);

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastGroup = Index.fromOneBased(model.getFilteredGroupList().size());
        Group lastGroup = model.getFilteredGroupList().get(indexLastGroup.getZeroBased());

        GroupBuilder groupInList = new GroupBuilder(lastGroup);
        Group editedGroup = groupInList.withName(VALID_NAME_TENNIS)
                .build();

        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withName(VALID_NAME_TENNIS)
                .build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(indexLastGroup, descriptor);

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(lastGroup, editedGroup);

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST,
                new EditGroupCommand.EditGroupDescriptor());
        Group editedGroup = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGroupAtIndex(model, INDEX_FIRST);

        Group groupInFilteredList = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        Group editedGroup = new GroupBuilder(groupInFilteredList).withName(VALID_NAME_TENNIS).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST,
                new EditGroupDescriptorBuilder().withName(VALID_NAME_TENNIS).build());

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(model.getFilteredGroupList().get(0), editedGroup);

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGroupUnfilteredList_failure() {
        Group firstGroup = model.getFilteredGroupList().get(INDEX_FIRST.getZeroBased());
        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(firstGroup).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editGroupCommand, model, EditGroupCommand.MESSAGE_DUPLICATE_GROUP);
    }

    @Test
    public void execute_duplicateGroupFilteredList_failure() {
        showGroupAtIndex(model, INDEX_FIRST);

        // edit group in filtered list into a duplicate in address book
        Group groupInList = model.getAddressBook().getGroupList().get(INDEX_SECOND.getZeroBased());
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST,
                new EditGroupDescriptorBuilder(groupInList).build());

        assertCommandFailure(editGroupCommand, model, EditGroupCommand.MESSAGE_DUPLICATE_GROUP);
    }

    @Test
    public void execute_invalidGroupIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withName(VALID_NAME_VOLLEYBALL).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showGroupAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        EditGroupCommand editGroupCommand = new EditGroupCommand(outOfBoundIndex,
                new EditGroupDescriptorBuilder().withName(VALID_NAME_TENNIS).build());

        assertCommandFailure(editGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void undo_validPrecondition_successfulUndo() {
        Group editedGroup = CS2103T_GROUP_BUILDER.build();
        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(editedGroup).build();
        UndoableCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(EditGroupCommand.MESSAGE_TEMPLATE_UNDO_SUCCESS, editedGroup);
        CommandResult expectedUndoResult = new CommandResult.Builder(expectedMessage)
                .goToHome()
                .build();
        assertUndoSuccess(editGroupCommand, model, expectedUndoResult);
    }

    @Test
    public void equals() {
        final EditGroupCommand standardCommand = new EditGroupCommand(INDEX_FIRST, DESC_TENNIS);

        // same values -> returns true
        EditGroupCommand.EditGroupDescriptor copyDescriptor = new EditGroupCommand.EditGroupDescriptor(DESC_TENNIS);
        EditGroupCommand commandWithSameValues = new EditGroupCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditGroupCommand(INDEX_SECOND, DESC_TENNIS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditGroupCommand(INDEX_FIRST, DESC_VOLLEYBALL)));
    }
}
