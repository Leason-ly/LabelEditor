package history;

import command.Command;

import java.util.ArrayList;

public abstract class History {

    protected ArrayList<Command> historyList;
    protected Integer now; // now表示下一个Command应该放置的位置

    public History() {
        this.historyList = new ArrayList<Command>();
        this.now = 0;
    }
    public abstract ArrayList<Command> getLastK(Integer k);

    public abstract void add(Command command);

    public abstract Boolean canUndo();

    public abstract Boolean canRedo();

    public abstract Command undo();

    public abstract Command redo();
}
