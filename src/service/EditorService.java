package service;

import label.Label;

public abstract class EditorService {
    public Label nowlabel;
    public EditorService(){this.nowlabel=new Label();}

    public Label getnowLabel(){
        return this.nowlabel;
    }
    public void setNowlabel(Label newlabel) {
        this.nowlabel = newlabel;
    }

    public abstract void doCommand(String commandStr);



}
