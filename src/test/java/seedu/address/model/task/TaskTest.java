package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_A_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_B_BUILDER;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    private Task taskA = TASK_A_BUILDER.build();
    private Task taskB = TASK_B_BUILDER.build();
    private final Task task1 = TASK_1_BUILDER.build(); // a task with a valid description.

    @Test
    public void isSameTask() {
        //same object -> returns true
        assertTrue(taskA.isSameTask(taskA));

        // null -> returns false
        assertFalse(taskA.isSameTask(null));

        // same task description, different done fields
        Task taskADone = TASK_A_BUILDER.withDoneStatus(true).build();
        assertTrue(taskA.isSameTask(taskADone));

        // different task description, done attribute is the same -> returns false
        assertFalse(taskA.isSameTask(taskB));

        // task name differs in case, done attribute remains the same -> returns false
        Task editedTask1 = new TaskBuilder(task1).withDescription(VALID_DESCRIPTION_TASK_1.toLowerCase(Locale.ROOT))
                .build();
        assertFalse(task1.isSameTask(editedTask1));

        // task description has trailing spaces, done attribute is the same -> returns false
        String taskDescriptionWithTrailingSpaces = VALID_DESCRIPTION_TASK_1 + " ";
        editedTask1 = new TaskBuilder(task1).withDescription(taskDescriptionWithTrailingSpaces).build();
        assertFalse(task1.isSameTask(editedTask1));
    }

    @Test
    public void equals() {

        // same object
        assertTrue(taskA.equals(taskA));

        // same values -> returns true
        Task copyTask1 = TASK_A_BUILDER.withDescription(VALID_DESCRIPTION_TASK_1).build();
        assertTrue(copyTask1.equals(task1));

        // null -> returns false
        assertFalse(taskA.equals(null));

        // different type -> returns false
        assertFalse(taskA.equals(6));

        // different task -> returns false
        assertFalse(taskA.equals(task1));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(task1).withDescription("Hello this is different").build();
        assertFalse(task1.equals(editedTask1));

        // different done attribute -> returns false
        Task editedTaskA = TASK_A_BUILDER.withDoneStatus(true).build();
        assertFalse(taskA.equals(editedTaskA));
    }
}
