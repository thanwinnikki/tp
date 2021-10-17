package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Person ALICE = TypicalPersons.ALICE;
    public static final Person BENSON = TypicalPersons.BENSON;

    // Manually added
    public static final Group CS2103T = new GroupBuilder().withName("CS2103T")
            .withMembers(ALICE, BENSON)
            .build();

    // Manually added - Group and person's details found in {@code CommandTestUtil}
    public static final Group ES2660 = new GroupBuilder().withName(VALID_GROUP_NAME_CS2101)
            .withMembers(TypicalPersons.AMY, TypicalPersons.BOB)
            .build();

    private TypicalGroups() {} //prevents instantiation

    /**
     * Returns an {@code AddressBook} with typical persons and typical groups.
     */
    public static AddressBook getTypicalAddressBookWithGroups() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(CS2103T, ES2660));
    }
}
