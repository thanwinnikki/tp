package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

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
}
