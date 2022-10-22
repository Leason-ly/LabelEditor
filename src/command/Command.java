package command;

import dustbin.Label_dustbin;
import label.Label;

public abstract class Command {


    protected String commandName;
    protected String regPattern; //用于检查是否合法

    public Command() {
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String CommandName) {
        this.commandName = CommandName;
    }

    public String getRegPattern() {
        return regPattern;
    }

    public void setRegPattern(String regPattern) {
        this.regPattern = regPattern;
    }

    public abstract void Execute(Label label);

    public abstract void Execute(Label label, Label_dustbin dustbin);

    public abstract void UnExecute(Label label);

    public abstract void UnExecute(Label label, Label_dustbin dustbin);

    public abstract Boolean IsReversible();

    public abstract String getCommandStr();


}
