package test;

import command.Modify.AddtitleCommand;
import command.Modify.DeletetitleCommand;
import command.Unmodify.RedoCommand;
import command.Unmodify.UndoCommand;
import label.Element;
import label.Label;
import label.folder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RedoCommandTest extends LabelEditorTest{

    @Test
    public void test1() throws IOException{

        Label label = new Label();
        Element fold1 = new folder("个人收藏","#");
        label.labellist.add(fold1);

        AddtitleCommand addtitleCommand = new AddtitleCommand("课程 at 个人收藏"); //here may be wrong
        addtitleCommand.Execute(label,label_dustbin);
        assertEquals("课程",label.getLabellist().get(0).getSubordinates().get(0).getName());

        modifyHistory.add(addtitleCommand);

        UndoCommand undoCommand = new UndoCommand();
        undoCommand.Execute(modifyHistory,label,label_dustbin);
        assertEquals(0,label.getLabellist().get(0).getSubordinates().size());

        RedoCommand redoCommand = new RedoCommand();
        redoCommand.Execute(modifyHistory,label,label_dustbin);
        assertEquals("课程",label.getLabellist().get(0).getSubordinates().get(0).getName());

    }
    @Test
    public void test2() throws IOException{

        Label label = new Label();
        Element fold1 = new folder("个人收藏","#");
        label.labellist.add(fold1);

        AddtitleCommand addtitleCommand = new AddtitleCommand("课程 at 个人收藏"); //here may be wrong
        addtitleCommand.Execute(label,label_dustbin);
        assertEquals("课程",label.getLabellist().get(0).getSubordinates().get(0).getName());

        modifyHistory.add(addtitleCommand);

        DeletetitleCommand deletetitleCommand = new DeletetitleCommand("课程");
        deletetitleCommand.Execute(label,label_dustbin);
        assertEquals(0,label.getLabellist().get(0).getSubordinates().size());

        modifyHistory.add(deletetitleCommand);

        UndoCommand undoCommand = new UndoCommand();
        undoCommand.Execute(modifyHistory,label,label_dustbin);
        assertEquals("课程",label.getLabellist().get(0).getSubordinates().get(0).getName());

        RedoCommand redoCommand = new RedoCommand();
        redoCommand.Execute(modifyHistory,label,label_dustbin);
        assertEquals(0,label.getLabellist().get(0).getSubordinates().size());

    }

}
