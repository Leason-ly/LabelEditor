package label;

import java.util.List;

public abstract class Element {
    public String type;

    public String getType() {
        return type;
    }

    public abstract String getName();

    public List<Element> getSubordinates() {
        return null;
    };
}
