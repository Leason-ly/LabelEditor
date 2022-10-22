package factory;
import command.Command;
import command.Modify.*;
import command.Unmodify.*;

public class CommandFactory  extends Factory {
    public Command getInstance(String commandName) {
        switch (commandName) {
            case "add-title":
                return new AddtitleCommand();
            case "add-bookmark":
                return new AddbookmarkCommand();
            case "delete-title":
                return new DeletetitleCommand();
            case "delete-bookmark":
                return new DeletebookmarkCommand();
            case "open":
                return new OpenCommand();
            case "save":
                return new SaveCommand();
            case "undo":
                return new UndoCommand();
            case "redo":
                return new RedoCommand();
            case "show-tree":
                return new ShowTreeCommand();
            case "ls-tree":
                return new LsTreeCommand();
            case "read-bookmark":
                return new ReadBookmarkCommand();
            default :
                return null;
        }


    }

}
