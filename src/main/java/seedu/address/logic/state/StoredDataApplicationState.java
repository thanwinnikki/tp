package seedu.address.logic.state;

import java.util.Objects;

/**
 * ApplicationState with data stored.
 */
public abstract class StoredDataApplicationState<T> implements ApplicationState {

    private final T storedData;
    /**
     * Constructor for StoredDataApplication.
     *
     * @param dataToStore Data to be stored.
     */
    public StoredDataApplicationState(T dataToStore) {
        this.storedData = dataToStore;
    }

    public T getStoredData() {
        return storedData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplicationStateType(), storedData);
    }
}
