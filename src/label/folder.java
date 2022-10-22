package label;

import java.util.List;
import java.util.ArrayList;
public class folder extends Element {
    public String foldname;
    public String depth;
    public List<Element> subordinates;

    public folder (String labelname,String depth) {
        this.type="folder";
        this.foldname = labelname;
        this.depth = depth;
        subordinates = new ArrayList<Element>();
    }

    @Override
    public String getType(){
        return this.type;
    }

    public String getFoldname() {
        return foldname;
    }

    public void setFoldname(String foldname) {
        this.foldname = foldname;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public List<Element> getSubordinates() {
        return subordinates;
    }
    public String getName(){
        return foldname;
    }
}
