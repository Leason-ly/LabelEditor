package label;

import java.util.ArrayList;
import java.util.List;

public class Trashbin {
    public static List<Trashbin> trashbinList = new ArrayList<>();
    private Element element;
    private int index;//在父类下的路径
    private folder parent;//父节点


    public Trashbin(Element element, int index, folder parent) {
        this.element = element;
        this.index = index;
        this.parent = parent;
    }

    public Trashbin() {
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public folder getParent() {
        return parent;
    }

    public void setParent(folder parent) {
        this.parent = parent;
    }

}
