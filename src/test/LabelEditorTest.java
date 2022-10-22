package test;

import dustbin.Label_dustbin;
import factory.CommandFactory;
import history.History;
import history.ModifyHistoryEntity;

public abstract class LabelEditorTest {

    protected History modifyHistory = new ModifyHistoryEntity();
    protected CommandFactory commandFactory = new CommandFactory();
    protected Label_dustbin label_dustbin = new Label_dustbin();

}
