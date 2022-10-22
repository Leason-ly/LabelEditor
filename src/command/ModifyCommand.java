package command;

import label.Element;
import label.folder;

import java.util.List;

public abstract class ModifyCommand extends Command {
    public Boolean IsReversible() {
        return true;
    }


    public Element getElementByName(List<Element> list, String name){
        if(list == null || list.size() == 0)return null;
        Element ans = null;
        for(Element e : list){
            if(e == null) continue;
            else if(e.getName().equals(name)){
                ans = e;
                break;
            }
            else if(e.getType().equals("folder")){
                folder folder = (folder) e;
                Element subAns = getElementByName(folder.getSubordinates(), name);
                if(subAns != null){
                    //如果下面的子序列能够返回非空的结果，就直接退出递归过程
                    //这是因为项目中要求的name都具有唯一性。
                    ans = subAns;
                    break;
                }
            }
        }
        return ans;
    }

}
