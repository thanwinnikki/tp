package seedu.address.logic.state;

import java.util.Objects;

public abstract class StoredDataApplicationState<T> implements ApplicationState {

    private final T storedData;

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
