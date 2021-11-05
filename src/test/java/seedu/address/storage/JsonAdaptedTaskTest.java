package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_BUILDER;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class JsonAdaptedTaskTest {

    @Test
    public void constructor_differentConstructors_sameTask() {
        JsonAdaptedTask jsonAdaptedTask1 = new JsonAdaptedTask(VALID_DESCRIPTION_TASK_1, false);
        JsonAdaptedTask jsonAdaptedTask2 = new JsonAdaptedTask(TASK_1_BUILDER.build());
        assertEquals(jsonAdaptedTask1, jsonAdaptedTask2);

        jsonAdaptedTask1 = new JsonAdaptedTask(VALID_DESCRIPTION_TASK_1, true);
        Task task2 = new TaskBuilder(TASK_1_BUILDER.build())
                .withDoneStatus(true)
                .build();
        jsonAdaptedTask2 = new JsonAdaptedTask(task2);
        assertEquals(jsonAdaptedTask1, jsonAdaptedTask2);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        // Equivalence Partition {type}: Different type
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(TASK_1_BUILDER.build());
        assertNotEquals(null, jsonAdaptedTask);
        assertNotEquals(new JsonAdaptedTaskTest(), jsonAdaptedTask);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        // Equivalence Partition {reference}: Same object
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(TASK_1_BUILDER.build());
        assertEquals(jsonAdaptedTask, jsonAdaptedTask);
    }

    @Test
    public void equals_sameAttributeValues_returnsTrue() {
        // Equivalence Partition {description, isDone}: All same
        Task task1 = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_TASK_1)
                .build();
        JsonAdaptedTask jsonAdaptedTask1 = new JsonAdaptedTask(task1);
        Task task2 = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_TASK_1)
                .build();
        JsonAdaptedTask jsonAdaptedTask2 = new JsonAdaptedTask(task2);
        assertEquals(jsonAdaptedTask1, jsonAdaptedTask2);
    }

    @Test
    public void equals_differentDescriptions_returnsFalse() {
        // Equivalence Partition {description}: Different descriptions
        Task task1 = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_TASK_1)
                .build();
        JsonAdaptedTask jsonAdaptedTask1 = new JsonAdaptedTask(task1);
        Task task2 = new TaskBuilder()
                .withDescription("Prepare demo")
                .build();
        JsonAdaptedTask jsonAdaptedTask2 = new JsonAdaptedTask(task2);
        assertNotEquals(jsonAdaptedTask1, jsonAdaptedTask2);
    }

    @Test
    public void equals_differentlyMarkedDone_returnsFalse() {
        // Equivalence Partition {isDone}: Differently marked done
        Task task1 = new TaskBuilder(TASK_1_BUILDER.build())
                .withDoneStatus(false)
                .build();
        JsonAdaptedTask jsonAdaptedTask1 = new JsonAdaptedTask(task1);
        Task task2 = new TaskBuilder(TASK_1_BUILDER.build())
                .withDoneStatus(true)
                .build();
        JsonAdaptedTask jsonAdaptedTask2 = new JsonAdaptedTask(task2);
        assertNotEquals(jsonAdaptedTask1, jsonAdaptedTask2);
    }
}
