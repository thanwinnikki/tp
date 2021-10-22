package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTCommandParser implements Parser<DeleteTCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTCommand
     * and returns a DeleteTCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteTCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTCommand.MESSAGE_USAGE), pe);
        }
    }
}
