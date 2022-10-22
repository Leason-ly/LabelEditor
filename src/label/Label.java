package label;

import java.util.ArrayList;
import java.util.List;

public class Label{

    public static List<Element> labellist;

    public Label(){this.labellist=new ArrayList<Element>();}

    public Label(List<Element> newlabellist){this.labellist = newlabellist;}

    public static List<Element> getLabellist() {

        return labellist;
    }

    public void setLabellist(List<Element> labellist) {

        this.labellist = labellist;
    }
}
