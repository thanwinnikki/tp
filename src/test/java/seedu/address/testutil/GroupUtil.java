package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.common.Description;
import seedu.address.model.group.Group;

public class GroupUtil {
    /**
     * Returns an group command string for adding the {@code group}.
     */
    public static String getGroupCommand(Group group) {
        return AddGroupCommand.COMMAND_WORD + " " + getGroupDetails(group);
    }

    /**
     * Returns the part of command string for the given {@code group}'s details.
     */
    public static String getGroupDetails(Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + group.getName().fullName + " ");
        Description description = group.getDescription();
        if (description != null) {
            sb.append(PREFIX_DESCRIPTION + description.value + " ");
        }
        return sb.toString();
    }
}
