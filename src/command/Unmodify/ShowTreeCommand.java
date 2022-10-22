package command.Unmodify;
import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import label.Label;
import label.Element;
import label.Link;
import label.folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class ShowTreeCommand extends UnmodifyCommand {

    public ShowTreeCommand(){
        this.commandName="show-tree";
        this.regPattern="show-tree";
    }

    public ShowTreeCommand(Label label){
        this.commandName="show-tree";
        this.regPattern="show-tree";
    }


    public void Execute(Label label){
        List<Element> A = label.labellist;
        //参考 https://www.dounaite.com/article/627cd61bac359fc9132dc33b.html
        Stack<Element> stack = new Stack<>();
        for (Element i : A) {
            stack.push(i);
            while (!stack.empty()){
                Element t = stack.pop();
                folder fd = (folder)t;
                System.out.println(fd.depth+" "+fd.getFoldname());
                if (!fd.getSubordinates().isEmpty()){
                    List<Element> sub = fd.getSubordinates();
                    for (Element j1 : sub){  //先打印link
                        if (j1.getType() == "link"){
                            Link link = (Link)j1;
                            System.out.println("["+link.getLinkname()+"]"+"("+link.getLinkcontent()+")");
                        }
                    }
                    Collections.reverse(sub);
                    for (Element j2 : sub){  //逆置list以保证原顺序
                        if (j2.getType() == "folder"){
                            stack.push(j2);
                        }
                    }
                    Collections.reverse(sub);
                }
            }
        }
    }

    @Override
    public void Execute(Label label, Label_dustbin dustbin) {

    }

    ;

    public void UnExecute(Label label){

    }

    @Override
    public void UnExecute(Label label, Label_dustbin dustbin) {

    }

    ;

    public String getCommandStr(){
        return this.commandName;
    }


}
