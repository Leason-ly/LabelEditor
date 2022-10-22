package command.Modify;
import com.sun.javaws.IconUtil;
import command.ModifyCommand;
import dustbin.Label_dustbin;
import label.Element;
import label.Label;
import label.Link;
import label.folder;


import java.util.List;

public class ReadBookmarkCommand extends ModifyCommand {
    private String inputStr;
    public ReadBookmarkCommand(){
        //需要重写
        this.commandName="read-bookmark";
        this.regPattern = "[A-Za-z-]+\\s+\\\"+.+\\\"";
    }
    /*    public ReadBookmarkCommand(String inputStr){
            //需要重写
            this.commandName="read-bookmark";
            this.regPattern = "^a\\s+\".+\"$";  //example
            this.inputStr = inputStr;
        }*/
    public String getInputStr() {
        return this.inputStr;
    }
    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }


    public void Execute(Label label,Label_dustbin dustbin){
        //获取labelList
        List<Element> labelList = Label.getLabellist();
        //遍历labelList
        for (Element element : labelList) {
            visitElement(element);
        }
    }

    @Override
    public void Execute(Label label) {

    }

    ;

    public void UnExecute(Label label,Label_dustbin dustbin){
        //逆向执行
        for (Element element :  Label.getLabellist()) {
            unVisitElement(element);
        }
    }

    @Override
    public void UnExecute(Label label) {

    }

    ;

    public void visitElement(Element element) {
        //case 1: element为folder的处理
        if(element.type == "folder") {
            //获取该folder的subordinates
            folder folder1 = (folder) element;
            List<Element> subordinates = folder1.subordinates;

            //遍历访问该folder的子element
            for (Element element1: subordinates) {
                visitElement(element1);
            }

        } else if (element.type == "link"){
            //case 2: Link处理
            Link link = (Link) element;
            String trueLinkname;  //例如linkname为*elearning[3]则真正的linkname为elearning
            int visitTimes = 0; //例如linkname为*elearning[3]则visitTimes应为3
            //若该link曾被访问过，找到真正标签名及访问次数

            if(link.linkname.substring(0, 1).equals("*")) {
                int leftIndex = link.linkname.indexOf("[");
                int rightIndex = link.linkname.indexOf("]");
                trueLinkname = link.linkname.substring(1,leftIndex);
                try {
                    visitTimes = Integer.parseInt(link.linkname.substring(leftIndex+1,rightIndex));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                //若该link从未被访问过
                trueLinkname = link.linkname;
                visitTimes = 0;
            }

            int len = inputStr.length();
            String linknameInput = inputStr;
            //System.out.println("真实的linkname :" + trueLinkname+" vistTimes : " + visitTimes);
            //若link即为所找的link,产生新的link名称
            if (trueLinkname.equals(linknameInput)) {
                System.out.println("Name : " + link.linkname);
                System.out.println("Content : " + link.linkcontent);
                String newLinkName = "*" + trueLinkname + "[" + ++visitTimes + "]";
                link.setLinkname(newLinkName);
                link.setTimes(visitTimes);

            }
        }
    }

    //执行Visit的回退操作
    public void unVisitElement(Element element) {
        //case 1: element为folder的处理
        if(element.type == "folder") {
            //获取该folder的subordinates
            folder folder1 = (folder) element;
            List<Element> subordinates = folder1.subordinates;

            //遍历访问该folder的子element
            for (Element element1: subordinates) {
                unVisitElement(element1);
            }

        } else if (element.type == "link"){
            //case 2: Link处理
            Link link = (Link) element;
            String trueLinkname="";  //例如linkname为*elearning[3]则真正的linkname为elearning
            int visitTimes = 0; //例如linkname为*elearning[3]则visitTimes应为3
            //若该link曾被访问过，找到真正标签名及访问次数

            if(link.linkname.substring(0, 1).equals("*")) {
                int leftIndex = link.linkname.indexOf("[");
                int rightIndex = link.linkname.indexOf("]");
                trueLinkname = link.linkname.substring(1,leftIndex);
                try {
                    visitTimes = Integer.parseInt(link.linkname.substring(leftIndex+1,rightIndex));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            int len = inputStr.length();
            String linknameInput = inputStr;
            //System.out.println("真实的linkname :" + trueLinkname+" vistTimes : " + visitTimes);
            //若link即为所找的link,访问次数-1，，如果访问次数为0，*也需去掉，执行回退操作
            if (trueLinkname.equals(linknameInput)) {
                String newLinkName = "";
                --visitTimes;
                System.out.println("Visit times : " + visitTimes);
                if(visitTimes > 0) {
                    newLinkName = "*" + trueLinkname + "[" + visitTimes + "]";
                }else if(visitTimes == 0) {
                    newLinkName = trueLinkname;
                }

                link.setLinkname(newLinkName);
                link.setTimes(visitTimes);
                System.out.println("Undo successfully! ：");

            }
        }
    }

    public String getCommandStr(){ // 需要重写，modify的需要重写
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.commandName);
        stringBuilder.append(" ");
        stringBuilder.append("\"" + inputStr + "\"");
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}
