package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group CS2103T = new GroupBuilder().withName("CS2103T Project Group")
            .withMembers(ALICE, BENSON, CARL).build();

    public static final Group CS2101 = new GroupBuilder().withName("CS2101 Project Group")
            .withMembers(ELLE, FIONA, GEORGE).build();

    // Manually added
    public static final Group FAMILY = new GroupBuilder().withName("Family")
            .withMembers(DANIEL, ELLE, ALICE).build();
    public static final Group FRIENDS = new GroupBuilder().withName("Friends")
            .withMembers(HOON, IDA).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Group TENNIS = new GroupBuilder().withName(VALID_NAME_TENNIS)
            .withMembers(AMY, BOB).build();
    public static final Group SWIMMING = new GroupBuilder().withName(VALID_NAME_SWIMMING)
            .withMembers(BOB, AMY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGroups() {} // prevents instantiation

    public static List<Group> getTypicalGroup() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101));
    }
}
