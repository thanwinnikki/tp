package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VOLLEYBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FAMILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditGroupCommand.EditGroupDescriptor;
import seedu.address.testutil.EditGroupDescriptorBuilder;


public class EditGroupDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditGroupDescriptor descriptorWithSameValues = new EditGroupDescriptor(DESC_VOLLEYBALL);
        assertTrue(DESC_VOLLEYBALL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_VOLLEYBALL.equals(DESC_VOLLEYBALL));

        // null -> returns false
        assertFalse(DESC_VOLLEYBALL.equals(null));

        // different types -> returns false
        assertFalse(DESC_VOLLEYBALL.equals(5));

        // different values -> returns false
        assertFalse(DESC_VOLLEYBALL.equals(DESC_TENNIS));

        // different name -> returns false
        EditGroupDescriptor editedVolleyball = new EditGroupDescriptorBuilder(DESC_VOLLEYBALL)
                .withName(VALID_NAME_TENNIS).build();
        assertFalse(DESC_VOLLEYBALL.equals(editedVolleyball));

        // different description -> returns false
        editedVolleyball = new EditGroupDescriptorBuilder(DESC_VOLLEYBALL)
                .withDescription(VALID_DESCRIPTION_FAMILY).build();
        assertFalse(DESC_VOLLEYBALL.equals(editedVolleyball));
    }
}
