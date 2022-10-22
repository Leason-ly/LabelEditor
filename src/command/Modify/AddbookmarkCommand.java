package command.Modify;

import command.Command;
import command.ModifyCommand;

import dustbin.Label_dustbin;
import label.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AddbookmarkCommand extends ModifyCommand {

    private String inputStr;
    public AddbookmarkCommand(){
        this.commandName="add-bookmark";
        this.regPattern = "^(add-bookmark)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)(@)(\"[^\"\\n]+\"|[^\"\\n]+)\\s+(at)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)$(\\s*)";  //example
    }

    public AddbookmarkCommand(String inputStr){
        this.commandName="add-bookmark";
        this.regPattern = "^(add-bookmark)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)(@)(\"[^\"\\n]+\"|[^\"\\n]+)\\s+(at)\\s+(\"[^\"\\n]+\"|[^\"\\n]+)$(\\s*)";  //example
        this.inputStr = inputStr;
    }

    public String getInputStr() {
        return this.inputStr;
    }
    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }




    public void Execute(Label label,Label_dustbin dustbin){
        //具体执行
        //解析link
        String linkname = new String(), linkcontent = new String();
        String folderName = new String();
        int index = 0;
        String[] strs = inputStr.split("\\s+");
        if(strs[index].length() == 0){
            index ++;//跳过空的字符串
        }
        String[] validContent = strs[index].split("@");
        linkname = validContent[0].replace("\"","");
        linkcontent = validContent[1].replace("\"","");
        index+=2;
        folderName = strs[index].replace("\"","");
        /*//传统的使用指针的单线程模式
        while((charPointer = inputStr.charAt(index++)) != '@'){
            if(charPointer == '\"') continue;
            linkname.append(charPointer);
        }
        //读取linkcontent
        while((charPointer = inputStr.charAt(index++)) != ' '){
            if(charPointer == '\"') continue;
            linkcontent.append(charPointer);
        }
        if(inputStr.charAt(index) == '\"' ||inputStr.charAt(index) == ' ') index++;//查找at后面的目录名称
        while((charPointer = inputStr.charAt(index++)) != '\"'){
            folderName += charPointer;
        }*/

        folder belongTo = (folder) getElementByName(label.getLabellist(),folderName);
        //文件夹无法找到错误：
        if(belongTo == null ){
            System.out.println("Folder name not found, please confirm your folder name!");
            return;
        }
        Link res = new Link(linkname,linkcontent);
        belongTo.subordinates.add(0,res);
//        List<Element> elementList = belongTo.getSubordinates();
//        String path = belongTo.path + "-" + elementList.size();
//        Link res = new Link(linkname,linkcontent,path);
//        elementList.add(res);//由于这边已经更新过了，加上java的Map对象中存储的都是引用类型，所以后面不需要再变更。
//        //更新索引
//        Label.elementMap.put(res.getLinkname(),res);
//        Label.pathMap.put(path, res.getLinkname());
        //add永远是在第一个地方添加的，所以撤销操作永远只需要把第一个删除，后面的所有东西全都往前移一个位置。
        System.out.println("Add-bookmark successfully!");

       /* //通过索引查找该对象在label中的位置
       //这边假设的是Label对象中存储的对象并非是来自elementMap的引用
        String[] splits = belongTo.path.split("-");
        elementList = label.getLabellist();
        //根据路径进行迭代查询
        for(String s : splits){
            int i = Integer.parseInt(s);
            folder layerFolder = (folder) elementList.get(i);
            elementList = layerFolder.getSubordinates();
        }
        elementList.add(res);*/
        //原始的使用BFS的方法
       /* Iterator<Element> it = label.getLabellist().iterator();
        Queue<Element> queue = new LinkedList<>();
        while(it.hasNext()){
            queue.offer(it.next());
        }
        while(queue.size() != 0){
            Element e = queue.poll();
            //如果这个类型是文件夹类型的
            if(e.getType() == "folder"){
                folder folder = (folder) e;
                if(folder.getFoldname().equals(folderName)){

                }
            }
        }*/
    }

    @Override
    public void Execute(Label label) {

    }

    ;

    public void UnExecute(Label label,Label_dustbin dustbin){
        //逆向执行
        String linkname = new String(), linkcontent = new String();
        String folderName = new String();
        int index = 0;
        String[] strs = inputStr.split("\\s+");
        if(strs[index].length() == 0){
            index ++;//跳过空的字符串
        }
        String[] validContent = strs[index].split("@");
        linkname = validContent[0].replace("\"","");
        linkcontent = validContent[1].replace("\"","");
        index+=2;
        folderName = strs[index].replace("\"","");
        folder belongTo = (folder) getElementByName(label.getLabellist(),folderName);
        belongTo.getSubordinates().remove(0);
        System.out.println("Undo add-bookmark successfully!");
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
