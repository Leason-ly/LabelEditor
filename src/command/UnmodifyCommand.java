package command;

import label.Element;
import label.Label;

public abstract class UnmodifyCommand extends Command {

    public Boolean IsReversible() {
        return false;
    }


}
