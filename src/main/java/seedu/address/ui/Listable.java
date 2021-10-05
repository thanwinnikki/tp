package seedu.address.ui;

import seedu.address.model.person.Person;

abstract class Listable {

    static Listable convertModelEntityToListable(Object modelEntity) throws IllegalArgumentException {
        String className = modelEntity.getClass()
                .getSimpleName();
        switch (className) {
        case "Person":
            return convertPersonToListable((Person) modelEntity);
        case "Group":
            return convertGroupToListable((Group) modelEntity);
        default:
            throw new IllegalArgumentException();
        }
    }

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
