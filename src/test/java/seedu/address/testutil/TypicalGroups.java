package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CSMODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FAMILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_VOLLEYBALL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.CAROL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.DONALD;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.TASK_1_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_A_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_B_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_C_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_D_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_E_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_F_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_G_BUILDER;

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
            .withMembers(ALICE, BENSON, CARL).withTasks(TASK_A_BUILDER.build(), TASK_B_BUILDER.build(),
                    TASK_E_BUILDER.build());

    public static final GroupBuilder CS2101_GROUP_BUILDER = new GroupBuilder().withName("CS2101 Project Group")
            .withDescription(VALID_DESCRIPTION_CSMODULE)
            .withMembers(ELLE, FIONA, GEORGE).withTasks(TASK_C_BUILDER.build(), TASK_D_BUILDER.build());

    public static final GroupBuilder VOLLEYBALL_GROUP_BUILDER = new GroupBuilder().withName(VALID_NAME_VOLLEYBALL)
            .withDescription(VALID_DESCRIPTION_SPORTS)
            .withMembers(ELLE, FIONA, GEORGE).withTasks(TASK_E_BUILDER.build());

    // Manually added
    public static final GroupBuilder FAMILY = new GroupBuilder().withName("Family").withDescription("casual group")
            .withDescription(VALID_DESCRIPTION_FAMILY)
            .withMembers(DANIEL, ELLE, ALICE).withTasks(TASK_G_BUILDER.build());
    public static final GroupBuilder FRIENDS = new GroupBuilder().withName("Friends").withDescription("casual group")
            .withDescription(VALID_DESCRIPTION_FRIENDS)
            .withMembers(HOON, IDA).withTasks(TASK_1_BUILDER.build());

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final GroupBuilder TENNIS = new GroupBuilder().withName(VALID_NAME_TENNIS)
            .withDescription(VALID_DESCRIPTION_SPORTS).withMembers(AMY, BOB, CAROL, DONALD)
            .withTasks(TASK_F_BUILDER.build(), TASK_G_BUILDER.build());
    public static final GroupBuilder SWIMMING = new GroupBuilder().withName(VALID_NAME_SWIMMING)
            .withDescription(VALID_DESCRIPTION_SPORTS).withMembers(DONALD, BOB);
    public static final GroupBuilder VOLLEYBALL = new GroupBuilder().withName(VALID_NAME_VOLLEYBALL)
            .withDescription(VALID_DESCRIPTION_SPORTS);
    public static final GroupBuilder BASKETBALL = new GroupBuilder().withName(VALID_NAME_BASKETBALL);


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
