package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FAMILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_MOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_JAMIE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    // Manually added - Group's details found in {@code CommandTestUtil}
    public static final Group PROJECT = new GroupBuilder().withName(VALID_NAME_PROJECT)
            .withMember(VALID_MEMBER_JAMIE).build();
    public static final Group FAMILY = new GroupBuilder().withName(VALID_NAME_FAMILY)
            .withMember(VALID_MEMBER_MOM).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(PROJECT, FAMILY));
    }
}
