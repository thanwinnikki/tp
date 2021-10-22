package seedu.address.logic.state;

/**
 * Represents the data store for {@code ApplicationState.HOME}.
 */
public class HomeStateDataStore extends ApplicationStateDataStore<Void> {

    private static HomeStateDataStore instance;

    private HomeStateDataStore() {}

    static HomeStateDataStore getInstance() {
        if (instance == null) {
            instance = new HomeStateDataStore();
        }
        return instance;
    }

    @Override
    public Void getData() {
        assert false : "The Home state does not store data.";
        return null;
    }

    @Override
    public void setData(Void data) {
        assert false : "The Home state cannot store data.";
    }
}
