package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
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

    private final Map<String, JsonAdaptedPerson> idToJsonAdaptedPersonMap;
    private final Map<String, JsonAdaptedGroup> idToJsonAdaptedGroupMap;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(
            @JsonProperty("persons") Map<String, JsonAdaptedPerson> idToJsonAdaptedPersonMap,
            @JsonProperty("groups") Map<String, JsonAdaptedGroup> idToJsonAdaptedGroupMap) {
        this.idToJsonAdaptedPersonMap = new HashMap<>(idToJsonAdaptedPersonMap);
        this.idToJsonAdaptedGroupMap = new HashMap<>(idToJsonAdaptedGroupMap);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        idToJsonAdaptedPersonMap = new HashMap<>();
        Iterable<Person> sourcePersons = source.getPersonList();
        Map<Person, Id> personToIdMap = createModelEntityToIdMap(sourcePersons);
        initialisePersonIdToJsonAdaptedPersonMap(sourcePersons, personToIdMap);
        idToJsonAdaptedGroupMap = new HashMap<>();
        Iterable<Group> sourceGroups = source.getGroupList();
        Map<Group, Id> groupToIdMap = createModelEntityToIdMap(sourceGroups);
        initialiseGroupIdToJsonAdaptedGroupMap(sourceGroups, groupToIdMap, personToIdMap);
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        Map<Id, Person> idToPersonMap = createPersonIdToPersonMap();
        Iterable<Person> persons = idToPersonMap.values();
        addPersons(addressBook, persons);
        Iterable<JsonAdaptedGroup> jsonAdaptedGroups = idToJsonAdaptedGroupMap.values();
        Iterable<Group> groups = createGroups(jsonAdaptedGroups, idToPersonMap);
        addGroups(addressBook, groups);
        return addressBook;
    }

    @JsonGetter("persons")
    public Map<String, JsonAdaptedPerson> getIdToJsonAdaptedPersonMap() {
        return idToJsonAdaptedPersonMap;
    }

    @JsonGetter("groups")
    public Map<String, JsonAdaptedGroup> getIdToJsonAdaptedGroupMap() {
        return idToJsonAdaptedGroupMap;
    }

    private <T> Map<T, Id> createModelEntityToIdMap(Iterable<T> modelEntities) {
        Set<Id> idSet = new HashSet<>();
        Map<T, Id> modelEntityToIdMap = new HashMap<>();
        for (T modelEntity : modelEntities) {
            Id id = Id.generateUniqueId(idSet);
            modelEntityToIdMap.put(modelEntity, id);
            idSet.add(id);
        }
        return modelEntityToIdMap;
    }

    private void initialisePersonIdToJsonAdaptedPersonMap(Iterable<Person> persons, Map<Person, Id> personToIdMap) {
        for (Person person : persons) {
            Id personId = personToIdMap.get(person);
            JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson.Builder(person)
                    .build();
            idToJsonAdaptedPersonMap.put(personId.toString(), jsonAdaptedPerson);
        }
    }

    private void initialiseGroupIdToJsonAdaptedGroupMap(
            Iterable<Group> groups, Map<Group, Id> groupToIdMap, Map<Person, Id> personToIdMap) {
        for (Group group : groups) {
            Id personId = groupToIdMap.get(group);
            JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup(group, personToIdMap);
            idToJsonAdaptedGroupMap.put(personId.toString(), jsonAdaptedGroup);
        }
    }

    private Map<Id, Person> createPersonIdToPersonMap() throws IllegalValueException {
        Map<Id, Person> jsonAdaptedPersonToPersonMap = new TreeMap<>();
        for (Map.Entry<String, JsonAdaptedPerson> idToJaPersonEntry : idToJsonAdaptedPersonMap.entrySet()) {
            String personIdString = idToJaPersonEntry.getKey();
            Id personId = Id.parse(personIdString);
            JsonAdaptedPerson jsonAdaptedPerson = idToJaPersonEntry.getValue();
            Person person = jsonAdaptedPerson.toModelType();
            jsonAdaptedPersonToPersonMap.put(personId, person);
        }
        return jsonAdaptedPersonToPersonMap;
    }

    private void addPersons(AddressBook addressBook, Iterable<Person> persons) throws IllegalValueException {
        for (Person person : persons) {
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
    }

    private List<Group> createGroups(
            Iterable<JsonAdaptedGroup> jsonAdaptedGroups,
            Map<Id, Person> idToPersonMap) throws IllegalValueException {
        List<Group> groups = new ArrayList<>();
        for (JsonAdaptedGroup jsonAdaptedGroup : jsonAdaptedGroups) {
            Group group = jsonAdaptedGroup.toModelType(idToPersonMap);
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
}
