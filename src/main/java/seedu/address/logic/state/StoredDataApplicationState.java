package seedu.address.logic.state;

public abstract class StoredDataApplicationState<T> implements ApplicationState {

    private final T storedData;

    public StoredDataApplicationState(T dataToStore) {
        this.storedData = dataToStore;
    }

    public T getStoredData() {
        return storedData;
    }
}
