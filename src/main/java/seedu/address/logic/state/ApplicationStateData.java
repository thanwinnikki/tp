package seedu.address.logic.state;

/**
 * Represents a piece of data stored when the application is in a certain state.
 * Any implementing class should be a singleton.
 */
public abstract class ApplicationStateData<T> {

    public boolean isAbleToStoreData() {
        return false;
    }

    public abstract T getData();

    public abstract void setData(T data);
}
