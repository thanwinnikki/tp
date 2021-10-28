package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CSMODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FAMILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_VOLLEYBALL;
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
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_C;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.TASK_E;
import static seedu.address.testutil.TypicalTasks.TASK_F;
import static seedu.address.testutil.TypicalTasks.TASK_G;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final GroupBuilder CS2103T_GROUP_BUILDER = new GroupBuilder().withName("CS2103T Project Group")
            .withDescription(VALID_DESCRIPTION_CSMODULE)
            .withMembers(ALICE, BENSON, CARL).withTasks(TASK_A, TASK_B);

    public static final GroupBuilder CS2101_GROUP_BUILDER = new GroupBuilder().withName("CS2101 Project Group")
            .withDescription(VALID_DESCRIPTION_CSMODULE)
            .withMembers(ELLE, FIONA, GEORGE).withTasks(TASK_C, TASK_D);

    public static final GroupBuilder VOLLEYBALL_GROUP_BUILDER = new GroupBuilder().withName(VALID_NAME_VOLLEYBALL)
            .withDescription(VALID_DESCRIPTION_SPORTS)
            .withMembers(ELLE, FIONA, GEORGE).withTasks(TASK_E);

    // Manually added
    public static final GroupBuilder FAMILY = new GroupBuilder().withName("Family").withDescription("casual group")
            .withDescription(VALID_DESCRIPTION_FAMILY)
            .withMembers(DANIEL, ELLE, ALICE).withTasks(TASK_G);
    public static final GroupBuilder FRIENDS = new GroupBuilder().withName("Friends").withDescription("casual group")
            .withDescription(VALID_DESCRIPTION_FRIENDS)
            .withMembers(HOON, IDA).withTasks(TASK_1);

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final GroupBuilder TENNIS = new GroupBuilder().withName(VALID_NAME_TENNIS)
            .withDescription(VALID_DESCRIPTION_SPORTS).withMembers(AMY, BOB).withTasks(TASK_F);
    public static final GroupBuilder SWIMMING = new GroupBuilder().withName(VALID_NAME_SWIMMING)
            .withDescription(VALID_DESCRIPTION_SPORTS).withMembers(BOB, AMY);
    public static final GroupBuilder VOLLEYBALL = new GroupBuilder().withName(VALID_NAME_VOLLEYBALL)
            .withDescription(VALID_DESCRIPTION_SPORTS);


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBookWithGroups() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (GroupBuilder groupBuilder : getTypicalGroupBuilder()) {
            ab.addGroup(groupBuilder.build());
        }
        return ab;
    }

    public static List<GroupBuilder> getTypicalGroupBuilder() {
        return new ArrayList<>(Arrays.asList(CS2103T_GROUP_BUILDER, CS2101_GROUP_BUILDER, VOLLEYBALL_GROUP_BUILDER));
    }
}
