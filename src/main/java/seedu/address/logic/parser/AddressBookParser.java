package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.commands.GroupsCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.JoinGroupCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkAsDoneCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.GroupInformationState;
import seedu.address.model.group.Group;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput Full user input string.
     * @param currentApplicationState The current application state.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput, ApplicationState currentApplicationState) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        GroupInformationState groupInformationState;
        Group group;
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case AddGroupCommand.COMMAND_WORD:
            return new AddGroupCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case GroupsCommand.COMMAND_WORD:
            return new GroupsCommand();

        case GroupCommand.COMMAND_WORD:
            return new GroupCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case FindGroupCommand.COMMAND_WORD:
            return new FindGroupCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(currentApplicationState);

        case RemoveCommand.COMMAND_WORD:
            groupInformationState = (GroupInformationState) currentApplicationState;
            group = groupInformationState.getStoredData();
            return new RemoveCommandParser(group).parse(arguments);

        case JoinGroupCommand.COMMAND_WORD:
            return new JoinGroupCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            groupInformationState = (GroupInformationState) currentApplicationState;
            group = groupInformationState.getStoredData();
            return new DeleteTaskCommandParser(group).parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            groupInformationState = (GroupInformationState) currentApplicationState;
            group = groupInformationState.getStoredData();
            return new AddTaskCommandParser(group).parse(arguments);

        case MarkAsDoneCommand.COMMAND_WORD:
            groupInformationState = (GroupInformationState) currentApplicationState;
            group = groupInformationState.getStoredData();
            return new MarkAsDoneCommandParser(group).parse(arguments);

        case EditGroupCommand.COMMAND_WORD:
            return new EditGroupCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
