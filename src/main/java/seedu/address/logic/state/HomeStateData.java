package seedu.address.logic.state;

public class HomeStateData implements ApplicationStateData<Void> {

    private static HomeStateData instance;

    private HomeStateData() {}

    static HomeStateData getInstance() {
        if (instance == null) {
            instance = new HomeStateData();
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
