package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Duplicate person(s) found in storage.";
    public static final String MESSAGE_DUPLICATE_GROUP = "Duplicate group(s) found in storage.";

    private final List<JsonAdaptedPerson> persons;
    private final List<JsonAdaptedGroup> groups;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.persons = new ArrayList<>(persons);
        this.groups = new ArrayList<>(groups);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        Map<Person, Id> groupMateToIdMap = createGroupMateToIdMap(source);
        List<Person> sourcePersons = source.getPersonList();
        persons = new ArrayList<>();
        if (!sourcePersons.isEmpty()) {
            initialiseJsonAdaptedPersonList(sourcePersons, groupMateToIdMap);
        }
        List<Group> sourceGroups = source.getGroupList();
        groups = new ArrayList<>();
        if (!sourceGroups.isEmpty()) {
            initialiseJsonAdaptedGroupList(sourceGroups, groupMateToIdMap);
        }
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        Map<Id, JsonAdaptedPerson> idToJsonAdaptedGroupMateMap = createIdToJsonAdaptedGroupMateMap();
        Map<JsonAdaptedPerson, Person> personJsonToModelMap = createPersonJsonToModelMap();
        Map<Id, Person> idToGroupMateMap = createIdToGroupMateMap(idToJsonAdaptedGroupMateMap, personJsonToModelMap);
        Iterable<Person> persons = createPersons();
        addPersons(addressBook, persons);
        Iterable<Group> groups = createGroups(idToGroupMateMap);
        addGroups(addressBook, groups);
        return addressBook;
    }

    private Map<Person, Id> createGroupMateToIdMap(ReadOnlyAddressBook source) {
        Map<Person, Id> groupMateToIdMap = new HashMap<>();
        Iterable<Group> sourceGroups = source.getGroupList();
        Set<Id> idSet = new HashSet<>();
        for (Group sourceGroup : sourceGroups) {
            assignIdToGroupMatesIfNoId(sourceGroup, groupMateToIdMap, idSet);
        }
        return groupMateToIdMap;
    }

    private void initialiseJsonAdaptedPersonList(List<Person> sourcePersons, Map<Person, Id> groupMateToIdMap) {
        List<JsonAdaptedPerson> personsToAdd = sourcePersons.stream()
                .map(person -> convertPersonToJsonAdaptedPerson(person, groupMateToIdMap))
                .collect(Collectors.toList());
        assert persons != null : "The JSON adapted persons list should not be null.";
        persons.addAll(personsToAdd);
    }

    private void initialiseJsonAdaptedGroupList(List<Group> sourceGroups, Map<Person, Id> groupMateToIdMap) {
        List<JsonAdaptedGroup> groupsToAdd = sourceGroups.stream()
                .map(group -> convertGroupToJsonAdaptedGroup(group, groupMateToIdMap))
                .collect(Collectors.toList());
        assert groups != null : "The JSON adapted groups list should not be null.";
        groups.addAll(groupsToAdd);
    }

    private Map<Id, JsonAdaptedPerson> createIdToJsonAdaptedGroupMateMap() throws IllegalValueException {
        Map<Id, JsonAdaptedPerson> idToJsonAdaptedPersonMap = new HashMap<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            if (!jsonAdaptedPerson.hasId()) {
                continue;
            }
            Id id = jsonAdaptedPerson.getId();
            idToJsonAdaptedPersonMap.put(id, jsonAdaptedPerson);
        }
        return idToJsonAdaptedPersonMap;
    }

    private Map<JsonAdaptedPerson, Person> createPersonJsonToModelMap() throws IllegalValueException {
        Map<JsonAdaptedPerson, Person> personJsonToModelMap = new HashMap<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            personJsonToModelMap.put(jsonAdaptedPerson, person);
        }
        return personJsonToModelMap;
    }

    private Map<Id, Person> createIdToGroupMateMap(Map<Id, JsonAdaptedPerson> idToJsonAdaptedPersonMap,
                                                   Map<JsonAdaptedPerson, Person> personJsonToModelMap) {
        Map<Id, Person> idToPersonMap = new HashMap<>();
        for (Map.Entry<Id, JsonAdaptedPerson> idJsonAdaptedPersonEntry : idToJsonAdaptedPersonMap.entrySet()) {
            Id id = idJsonAdaptedPersonEntry.getKey();
            JsonAdaptedPerson jsonAdaptedPerson = idJsonAdaptedPersonEntry.getValue();
            Person person = personJsonToModelMap.get(jsonAdaptedPerson);
            idToPersonMap.put(id, person);
        }
        return idToPersonMap;
    }

    private Iterable<Person> createPersons() throws IllegalValueException {
        List<Person> persons = new ArrayList<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : this.persons) {
            Person person = jsonAdaptedPerson.toModelType();
            persons.add(person);
        }
        return persons;
    }

    private void addPersons(AddressBook addressBook, Iterable<Person> persons) throws IllegalValueException {
        for (Person person : persons) {
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
    }

    private Iterable<Group> createGroups(Map<Id, Person> idToGroupMateMap) throws IllegalValueException {
        List<Group> groups = new ArrayList<>();
        for (JsonAdaptedGroup jsonAdaptedGroup : this.groups) {
            Group group = jsonAdaptedGroup.toModelType(idToGroupMateMap);
            groups.add(group);
        }
        return groups;
    }

    private void addGroups(AddressBook addressBook, Iterable<Group> groups) throws IllegalValueException {
        for (Group group : groups) {
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            addressBook.addGroup(group);
        }
    }

    private void assignIdToGroupMatesIfNoId(Group group, Map<Person, Id> groupMateToIdMap, Set<Id> idSet) {
        group.doForEachGroupMate(groupMate -> {
            if (groupMateToIdMap.containsKey(groupMate)) {
                return;
            }
            Id id = Id.generateUniqueId(idSet);
            groupMateToIdMap.put(groupMate, id);
            idSet.add(id);
        });
    }

    private JsonAdaptedPerson convertPersonToJsonAdaptedPerson(Person person, Map<Person, Id> groupMateToIdMap) {
        JsonAdaptedPerson.Builder builder = new JsonAdaptedPerson.Builder(person);
        if (groupMateToIdMap.containsKey(person)) {
            Id id = groupMateToIdMap.get(person);
            builder.withId(id);
        }
        return builder.build();
    }

    private JsonAdaptedGroup convertGroupToJsonAdaptedGroup(Group group, Map<Person, Id> groupMateToIdMap) {
        return new JsonAdaptedGroup.Builder(group, groupMateToIdMap)
                .build();
    }
}
