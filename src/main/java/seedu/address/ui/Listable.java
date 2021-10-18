package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A model entity that is intended to be displayed on the list panel, like a {@code Group}, or a {@code Person}.
 */
abstract class Listable {

    private static final Map<Class<?>, Function<Object, Listable>> modelEntityToListableMap = new HashMap<>();

    static {
        modelEntityToListableMap.put(Person.class, person -> convertPersonToListable((Person) person));
        modelEntityToListableMap.put(Group.class, group -> convertGroupToListable((Group) group));
    }

    /**
     * Converts a model entity to a {@code Listable} object.
     *
     * @param modelEntity The model entity.
     * @param <T> The type of the model entity.
     * @return The listable model entity.
     */
    static <T> Listable convertModelEntityToListable(T modelEntity) {
        Class<?> modelEntityClass = modelEntity.getClass();
        assert modelEntityToListableMap.containsKey(modelEntityClass);
        return modelEntityToListableMap.get(modelEntityClass).apply(modelEntity);
    }

    /**
     * Gets the {@code Card} used to display the information of this {@code Listable} object.
     *
     * @param displayedIndex The index to display on the card.
     * @return The {@code Card} object of this {@code Listable} object.
     */
    abstract Card getCard(int displayedIndex);

    private static Listable convertPersonToListable(Person person) {
        return new Listable() {
            @Override
            Card getCard(int displayedIndex) {
                return new PersonCard(person, displayedIndex);
            }
        };
    }

    private static Listable convertGroupToListable(Group group) {
        return new Listable() {
            @Override
            Card getCard(int displayedIndex) {
                return new GroupCard(group, displayedIndex);
            }
        };
    }
}
