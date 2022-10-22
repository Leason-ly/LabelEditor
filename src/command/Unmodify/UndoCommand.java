package command.Unmodify;

import command.Command;
import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import history.History;
import label.Label;

public class UndoCommand extends UnmodifyCommand {

    public UndoCommand() {
        this.commandName = "undo";
        this.regPattern = "undo";
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
        if (history.canUndo()) {
            Command undoCommand = history.undo();
            undoCommand.UnExecute(label, dustbin);
        }
    }

    public String getCommandStr() {
        return this.commandName;
    }
}
