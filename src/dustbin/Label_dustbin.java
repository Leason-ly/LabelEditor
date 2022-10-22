package dustbin;

import java.util.ArrayList;
import java.util.List;

public class Label_dustbin {

    public List<folder_dust> dustbinlist;

    public Label_dustbin() {
        this.dustbinlist = new ArrayList<folder_dust>();
    }

    public Label_dustbin(List<folder_dust> newdustbinlist) {
        this.dustbinlist = newdustbinlist;
    }

    public List<folder_dust> getDustbinlist() {

        return dustbinlist;
    }

    public void setDustbinlist(List<folder_dust> dustbinlist) {

        this.dustbinlist = dustbinlist;
    }
}