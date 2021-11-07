package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.ApplicationStateType;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.UniqueTaskList;

public class EditGroupCommand implements UndoableCommand, StateDependentCommand {
    public static final String COMMAND_WORD = "editG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the group identified "
            + "by the index number used in the displayed group list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_DESCRIPTION + "This is a group for CS";

    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Edited Group: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book.";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of edit of group: %1$s";

    private final Index index;
    private final EditGroupCommand.EditGroupDescriptor editGroupDescriptor;

    private Group groupBeforeEdit;
    private Group groupAfterEdit;
    private Predicate<? super Person> personPredicate;
    private Predicate<? super Group> groupPredicate;

    /**
     * @param index of the group in the filtered group list to edit
     * @param editGroupDescriptor details to edit the group with
     */
    public EditGroupCommand(Index index, EditGroupDescriptor editGroupDescriptor) {
        requireNonNull(index);
        requireNonNull(editGroupDescriptor);

        this.index = index;
        this.editGroupDescriptor = new EditGroupCommand.EditGroupDescriptor(editGroupDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        personPredicate = model.getFilteredPersonListPredicate();
        groupPredicate = model.getFilteredGroupListPredicate();

        List<Group> lastShownList = model.getFilteredGroupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToEdit = lastShownList.get(index.getZeroBased());
        Group editedGroup = createEditedGroup(groupToEdit, editGroupDescriptor);

        if (!groupToEdit.isSameGroup(editedGroup) && model.hasGroup(editedGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.setGroup(groupToEdit, editedGroup);

        groupBeforeEdit = groupToEdit;
        groupAfterEdit = editedGroup;

        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_EDIT_GROUP_SUCCESS, editedGroup));
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(groupAfterEdit) : "The edited group must be in the records to undo its edit.";
        model.setGroup(groupAfterEdit, groupBeforeEdit);
        if (personPredicate == null) {
            personPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
        }
        model.updateFilteredPersonList(personPredicate);
        if (groupPredicate == null) {
            groupPredicate = Model.PREDICATE_SHOW_ALL_GROUPS;
        }
        model.updateFilteredGroupList(groupPredicate);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, groupBeforeEdit))
                .goToHome()
                .build();
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToEdit}
     * edited with {@code editGroupDescriptor}.
     */
    private static Group createEditedGroup(Group groupToEdit,
                                           EditGroupCommand.EditGroupDescriptor editGroupDescriptor) {
        assert groupToEdit != null;

        Name updatedName = editGroupDescriptor.getName().orElse(groupToEdit.getName());
        Description updatedDescription = editGroupDescriptor.getDescription().orElse(groupToEdit.getDescription());
        UniquePersonList persons = groupToEdit.getPersons();
        UniqueTaskList tasks = groupToEdit.getTasks();

        return new Group(updatedName, updatedDescription, persons, tasks);
    }

    @Override
    public boolean isAbleToRunInApplicationState(ApplicationState applicationState) {
        ApplicationStateType applicationStateType = applicationState.getApplicationStateType();
        return applicationStateType == ApplicationStateType.HOME;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGroupCommand)) {
            return false;
        }

        // state check
        EditGroupCommand e = (EditGroupCommand) other;
        return index.equals(e.index)
                && editGroupDescriptor.equals(e.editGroupDescriptor);
    }

    /**
     * Stores the details to edit the group with. Each non-empty field value will replace the
     * corresponding field value of the group.
     */
    public static class EditGroupDescriptor {
        private Name name;
        private Description description;

        public EditGroupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGroupDescriptor(EditGroupDescriptor toCopy) {
            setName(toCopy.name);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, description);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGroupCommand.EditGroupDescriptor)) {
                return false;
            }

            // state check
            EditGroupCommand.EditGroupDescriptor e = (EditGroupCommand.EditGroupDescriptor) other;

            return getName().equals(e.getName())
                    && getDescription().equals(e.getDescription());
        }
    }
}
