package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;

public class TaskUtil {
    /**
     * Returns an addTask command string for adding the {@code Task}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code Task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + task.getDescription().value + " ");
        return sb.toString();
    }

}
