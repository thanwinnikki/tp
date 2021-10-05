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
     */
    static <T> Listable convertModelEntityToListable(T modelEntity) {
        String className = modelEntity.getClass()
                .getSimpleName();
        Listable listable = null;
        switch (className) {
        case Person.class.getSimpleName():
            listable = convertPersonToListable((Person) modelEntity);
            break;
        case Group.class.getSimpleName():
            listable = convertGroupToListable((Group) modelEntity);
            break;
        default:
            assert false : "A non-listable model entity was passed into Listable::convertModelEntityToListable.";
        }
        return listable;
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
