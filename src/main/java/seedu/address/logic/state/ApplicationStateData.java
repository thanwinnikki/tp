package seedu.address.logic.state;

/**
 * Represents a piece of data stored when the application is in a certain state.
 * Any implementing class should be a singleton.
 */
public interface ApplicationStateData<T> {

    T getData();

    void setData(T data);
}
