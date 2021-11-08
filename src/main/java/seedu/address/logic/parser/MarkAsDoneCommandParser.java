package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAsDoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new MarkAsDoneCommand object
 */
public class MarkAsDoneCommandParser implements Parser<MarkAsDoneCommand> {

    private final Group group;

    public MarkAsDoneCommandParser(Group group) {
        this.group = group;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAsDoneCommand
     * and returns a MarkAsDoneCommand object for execution.
     *
     * @param args Arguments of the user input to be parsed by MarkAsDoneCommandParser.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public MarkAsDoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkAsDoneCommand(index, group);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAsDoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
