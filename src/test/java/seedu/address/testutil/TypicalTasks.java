package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final TaskBuilder TASK_A_BUILDER = new TaskBuilder().withDescription("Prepare demo");

    public static final TaskBuilder TASK_B_BUILDER = new TaskBuilder().withDescription("Deliver v1.3");

    public static final TaskBuilder TASK_C_BUILDER = new TaskBuilder().withDescription("Add testing");

    public static final TaskBuilder TASK_D_BUILDER = new TaskBuilder().withDescription("Group meeting");

    public static final TaskBuilder TASK_E_BUILDER = new TaskBuilder().withDescription("Delegate roles");

    // Manually added
    public static final TaskBuilder TASK_F_BUILDER = new TaskBuilder().withDescription("Do documentation");

    public static final TaskBuilder TASK_G_BUILDER = new TaskBuilder().withDescription("Clean up code");

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final TaskBuilder TASK_1_BUILDER = new TaskBuilder().withDescription(VALID_DESCRIPTION_TASK_1);

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

}
