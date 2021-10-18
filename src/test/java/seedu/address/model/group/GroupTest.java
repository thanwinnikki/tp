package seedu.address.model.group;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2101;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2101;
import static seedu.address.testutil.TypicalGroups.CS2103T;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getPersons()
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(CS2103T.isSameGroup(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameGroup(null));

        // same group name, group members different -> returns true
        Group editedGroupCS2103T = new GroupBuilder(CS2101).withName("CS2103T Project Group").build();
        assertTrue(CS2103T.isSameGroup(editedGroupCS2103T));

        // different group name, all other attributes same -> returns false
        editedGroupCS2103T = new GroupBuilder(CS2103T).withName("CS2101").build();
        assertFalse(CS2103T.isSameGroup(editedGroupCS2103T));

        // group name differs in case, all other attributes same -> returns false
        Group editedCS2101 = new GroupBuilder(CS2101).withName(VALID_GROUP_NAME_CS2101.toLowerCase(Locale.ROOT))
                .build();
        assertFalse(CS2101.isSameGroup(editedCS2101));

        // group name has trailing spaces, all other attributes same -> returns false
        String groupNameWithTrailingSpaces = VALID_GROUP_NAME_CS2101 + " ";
        editedCS2101 = new GroupBuilder(CS2101).withName(groupNameWithTrailingSpaces).build();
        assertFalse(CS2101.isSameGroup(editedCS2101));
    }

    @Test
    public void equals() {

        Group test = CS2103T;

        //same object
        assertTrue(CS2103T.equals(CS2103T));

        // same values -> returns true
        Group copyCS2103T = new GroupBuilder(CS2103T).build();
        assertTrue(CS2103T.equals(copyCS2103T));

        // null -> returns false
        assertFalse(CS2103T.equals(null));

        // different type -> returns false
        assertFalse(CS2103T.equals(6));

        // different group -> returns false
        assertFalse(CS2103T.equals(CS2101));

        // different name -> returns false
        Group editedCS2103T = new GroupBuilder(CS2103T).withName("ES2660").build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different group members -> returns false
        editedCS2103T = new GroupBuilder(CS2103T).withMembers(CARL).build();
        assertFalse(CS2103T.equals(editedCS2103T));
    }
}
