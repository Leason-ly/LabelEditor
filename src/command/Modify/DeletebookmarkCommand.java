package command.Modify;

import command.Command;
import command.ModifyCommand;
import dustbin.Label_dustbin;
import label.*;

import java.util.List;
import java.util.Map;

public class DeletebookmarkCommand extends ModifyCommand {
    private String inputStr;
    public DeletebookmarkCommand(){
        this.commandName="delete-bookmark";
        this.regPattern = "^(delete-bookmark)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)$\\s*";  //example
    }

    public DeletebookmarkCommand(String inputStr){
        this.commandName="delete-bookmark";
        this.regPattern = "^(delete-bookmark)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)$\\s*";  //example
        this.inputStr = inputStr;
    }
    public String getInputStr() {
        return this.inputStr;
    }
    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }


    public folder getParentByName(folder folder,String name){
        if(folder == null ||
                folder.getSubordinates() == null ||
                folder.getSubordinates().size() == 0){
            return null;
        }
        folder ans = null;
        for(Element e : folder.getSubordinates()){
            if(e.getName().equals(name) 
               && e.getType().equals("link")){
                ans = folder;
                break;
            }
            else if(e.getType().equals("folder")){
                folder subAns = getParentByName((folder) e, name);
                if(subAns != null) {
                    ans = subAns;
                    break;
                }
            }
        }
        return ans;
    }

    public void Execute(Label label,Label_dustbin dustbin){
        //去掉多余的空格和引号
        String formattedStr = inputStr.replace("\"","");
        Link res = (Link) getElementByName(label.getLabellist(), formattedStr);
        //删除该字符对应的元素
        if(res == null){
            System.out.println("The name of bookmark to be deleted is not exist, please check your name!");
            return;
        }
//        String path = Label.elementMap.get(formattedStr).path;
//        Label.pathMap.remove(path);
//        String[] splits = path.split("-");
//        List<Element> elementList = label.getLabellist();
//
//        for(int i = 0;i<splits.length - 1;i++){
//            folder folder = (folder) elementList.get(Integer.parseInt(splits[i]));
//            elementList = folder.getSubordinates();
//        }
//        elementList.set(Integer.parseInt(splits[splits.length-1]),null);//把这个对象置位空，尽量不去动底层的数据结构
        folder parent = null;
        for(Element element : label.getLabellist()){
            if(element.getType().equals("folder")) {
                folder temp = getParentByName((folder) element, formattedStr);
                if (temp != null) {
                    parent = temp;
                    break;
                }
            }
        }
        int index = 0;
        for(int i = 0;i<parent.getSubordinates().size();i++){
            if(parent.getSubordinates().get(i).getName().equals(formattedStr)){
                index = i;
                break;
            }
        }
        //维护垃圾桶的数据结构
        Trashbin.trashbinList.add(new Trashbin(res,index,parent));
        parent.getSubordinates().remove(index);
//        Label.elementMap.remove(formattedStr);//删除对应的元素
        //具体执行
        System.out.println("Delete-bookmark successfully");

    }

    @Override
    public void Execute(Label label) {

    }

    public void UnExecute(Label label,Label_dustbin dustbin){
        //逆向执行
        String str = inputStr.replace("\"","");
        for(Trashbin trash : Trashbin.trashbinList){
            if(trash.getElement().getName().equals(str)){
                folder f = trash.getParent();
                f.getSubordinates().add(trash.getIndex(),trash.getElement());
                break;
            }
        }
    }

    @Override
    public void UnExecute(Label label) {

    }

    ;


    public String getCommandStr(){ // example
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.commandName);
        stringBuilder.append(" ");
        stringBuilder.append(inputStr);
        return stringBuilder.toString();
    }
}
