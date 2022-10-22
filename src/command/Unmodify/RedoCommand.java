package command.Unmodify;

import command.Command;
import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import history.History;
import label.Label;

public class RedoCommand extends UnmodifyCommand {

    public RedoCommand() {
        this.commandName = "redo";
        this.regPattern = "redo";
    }

    public void Execute(Label label) {
    }

    @Override
    public void Execute(Label label, Label_dustbin dustbin) {
        
    }

    ;

    public void UnExecute(Label label) {
    }

    @Override
    public void UnExecute(Label label, Label_dustbin dustbin) {

    }

    ;

    public void Execute(History history, Label label, Label_dustbin dustbin) {
        if (history.canRedo()) {
            Command redoCommand = history.redo();
            redoCommand.Execute(label, dustbin);
        }
    }

    public String getCommandStr() {
        return this.commandName;
    }
}
