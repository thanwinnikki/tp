package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group CS2103 = new GroupBuilder().withName("CS2103 Project Group")
            .withMember(ALICE, BENSON, CARL).build();

    public static final Group CS2101 = new GroupBuilder().withName("CS2101 Project Group")
            .withMember(ELLE, FIONA, GEORGE).build();

    // Manually added
    public static final Group FAMILY = new GroupBuilder().withName("Family")
            .withMember(DANIEL, ELLE, ALICE).build();
    public static final Group FRIENDS = new GroupBuilder().withName("Friends")
            .withMember(HOON, IDA).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Group TENNIS = new GroupBuilder().withName(VALID_NAME_TENNIS)
            .withMember(AMY, BOB).build();
    public static final Group SWIMMING = new GroupBuilder().withName(VALID_NAME_SWIMMING)
            .withMember(BOB, AMY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroup()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroup() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2101));
    }
}
