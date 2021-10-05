package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * A model entity that is intended to be displayed on the list panel, like a {@code Group}, or a {@code Person}.
 */
abstract class Listable {

    /**
     * Converts a model entity to a {@code Listable} object.
     *
     * @param modelEntity The model entity.
     * @param <T> The type of the model entity.
     * @return The listable model entity.
     * @throws IllegalArgumentException If a non-listable model entity is passed.
     */
    static <T> Listable convertModelEntityToListable(T modelEntity) throws IllegalArgumentException {
        String className = modelEntity.getClass()
                .getSimpleName();
        switch (className) {
        case Person.class.getSimpleName():
            return convertPersonToListable((Person) modelEntity);
        case Group.class.getSimpleName():
            return convertGroupToListable((Group) modelEntity);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Gets the {@code Card} used to display the information of this {@code Listable} object.
     *
     * @param displayedIndex The index to display on the card.
     * @return The {@code Card} object of this {@code Listable} object.
     */
    abstract Card getCard(int displayedIndex);

    private static Listable convertPersonToListable(Person person) {
        return new Listable(){
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
