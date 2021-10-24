package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_A = new TaskBuilder().withDescription("Prepare demo").build();

    public static final Task TASK_B = new TaskBuilder().withDescription("Deliver v1.3").build();

    public static final Task TASK_C = new TaskBuilder().withDescription("Add testing").build();

    public static final Task TASK_D = new TaskBuilder().withDescription("Group meeting").build();

    public static final Task TASK_E = new TaskBuilder().withDescription("Delegate roles").build();

    // Manually added
    public static final Task TASK_F = new TaskBuilder().withDescription("Do documentation").build();

    public static final Task TASK_G = new TaskBuilder().withDescription("Clean up code").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task TASK_1 = new TaskBuilder().withDescription(VALID_DESCRIPTION_TASK_1).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

//    /**
//     * Returns an {@code AddressBook} with all the typical Tasks.
//     */
//    public static AddressBook getTypicalAddressBook() {
//        AddressBook ab = new AddressBook();
//        Group group = new Group();
//        for (Task task : getTypicalTasks()) {
//            group.addTask(task);
//        }
//        return ab;
//    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_A, TASK_B, TASK_C, TASK_D, TASK_E));
    }
}
