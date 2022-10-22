package command.Unmodify;
import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import label.Label;
import label.Element;
import label.Link;
import label.folder;

import java.util.Iterator;
import java.util.List;

public class LsTreeCommand extends UnmodifyCommand {

    public LsTreeCommand(){
        this.commandName="ls-tree";
        this.regPattern="ls-tree";
    }

    public LsTreeCommand(Label label){
        this.commandName="ls-tree";
        this.regPattern="ls-tree";
    }


    public void Execute(Label label){
        List<Element> elementList = label.getLabellist();
        for(Element e : elementList){
            printTreeCatalog(e,1);
        }
    }

    @Override
    public void Execute(Label label, Label_dustbin dustbin) {

    }

    ;
    private void printTreeCatalog(Element e,int layer){
        //如果是null类型的对象，则不打印
        if(e == null) return;
        StringBuilder sb = new StringBuilder("|-");
        for(int i = 0;i<layer-1;i++){
            sb.insert(0,"  ");
        }
        sb.replace(sb.length()-1,sb.length(),"-");
        if(e.getType().equals("link")){
            Link link = (Link) e;
            //读过
            if(link.getFlag() == 1){
                sb.append('*');
            }
            sb.append(link.getName());
            sb.append("["+link.getTimes()+"]");
            System.out.println(sb);
        }
        else if(e.getType().equals("folder")){
            folder temp = (folder) e;
            sb.append(temp.getName());
            System.out.println(sb);
            List<Element> subordinates = temp.getSubordinates();
            for(Element elem : subordinates){
                printTreeCatalog(elem,layer+1);
            }
        }
    }

    public void UnExecute(Label label){}

    @Override
    public void UnExecute(Label label, Label_dustbin dustbin) {

    }

    ;

    public String getCommandStr(){
        return this.commandName;
    }
}
