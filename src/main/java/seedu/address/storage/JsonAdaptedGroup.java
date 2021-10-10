package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.names.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Jackson-friendly version of {@link Group}.
 */
@JsonDeserialize(builder = JsonAdaptedGroup.Builder.class)
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_GROUP_MATE = "Duplicate group mates(s) found in storage.";
    public static final String MESSAGE_NO_SUCH_PERSON = "There is no such person that has the ID of this group mate.";

    private final String name;
    private final List<String> groupMateIds;

    /**
     * Builder class for {@code JsonAdaptedGroup}.
     */
    public static class Builder {

        private JsonAdaptedGroup groupToBuild;

        /**
         * Constructs a {@code JsonAdaptedGroup.Builder} for a {@code JsonAdaptedPerson} with the given group details.
         *
         * @param name The group's name.
         * @param groupMateIds The list of the person IDs of all the group mates that belong to the group.
         */
        @JsonCreator
        public Builder(@JsonProperty("name") String name, @JsonProperty("groupMateIds") List<String> groupMateIds) {
            groupToBuild = new JsonAdaptedGroup(name, groupMateIds);
        }

        /**
         * Converts the given {@code Group} to a {@code JsonAdaptedGroup} using a {@code JsonAdaptedGroup.Builder}.
         *
         * @param source The {@code Group} object to be converted.
         * @param personToIdMap The mapping from each {@code Person} object to its respective stored person ID.
         */
        public Builder(Group source, Map<Person, String> personToIdMap) {
            groupToBuild = new JsonAdaptedGroup(source, personToIdMap);
        }

        /**
         * Completes the {@code JsonAdaptedGroup} being built by this {@code JsonAdaptedGroup.Builder}.
         *
         * @return The completed {@code JsonAdaptedGroup} object.
         */
        public JsonAdaptedGroup build() {
            return groupToBuild;
        }
    }

    private JsonAdaptedGroup(String name, List<String> groupMateIds) {
        this.name = name;
        this.groupMateIds = new ArrayList<>(groupMateIds);
    }

    private JsonAdaptedGroup(Group source, Map<Person, String> personToIdMap) {
        name = source.getName().fullName;
        groupMateIds = new ArrayList<>();
        UniquePersonList persons = source.getPersons();
        for (Person person : persons) {
            String personId = personToIdMap.get(person);
            groupMateIds.add(personId);
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Group} object.
     * The mapping from each stored person ID to its respective {@code Person} object is required in order to add the
     * correct group mates that belong to this {@code Group} object.
     *
     * @param idToPersonMap The mapping from each stored person ID to its respective {@code Person} object.
     * @return The {@code Group} object.
     * @throws IllegalValueException If there were any data constraints violated in the adapted group.
     */
    public Group toModelType(Map<Id, Person> idToPersonMap) throws IllegalValueException {
        final Name modelName = createName();
        Group group = new Group(modelName);
        addGroupMates(group, idToPersonMap);
        return group;
    }

    private Name createName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private void addGroupMates(Group group, Map<Id, Person> idToPersonMap) throws IllegalValueException {
        UniquePersonList persons = group.getPersons();
        for (String personIdString : groupMateIds) {
            Id personId = Id.parse(personIdString);
            Person person = idToPersonMap.get(personId);
            if (person == null) {
                throw new IllegalValueException(MESSAGE_NO_SUCH_PERSON);
            }
            try {
                persons.add(person);
            } catch (DuplicatePersonException e) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP_MATE);
            }
        }
    }
}
