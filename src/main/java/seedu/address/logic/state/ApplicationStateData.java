package seedu.address.logic.state;

/**
 * Represents a piece of data stored when the application is in a certain state.
 * Any implementing class should be a singleton.
 */
public abstract class ApplicationStateData<T> {

    /**
     * Returns true if the application state associated with this data store can store data.
     *
     * @return Whether the application state associated with this data store can store data.
     */
    public boolean isAbleToStoreData() {
        return false;
    }

    /**
     * Obtains the data stored in this data store.
     *
     * @return The data stored.
     */
    public abstract T getData();

    /**
     * Sets the data stored in this data store.
     *
     * @param data The data to be stored.
     */
    public abstract void setData(T data);
}
