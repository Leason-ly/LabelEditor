package command.Modify;

import command.ModifyCommand;
import dustbin.Label_dustbin;
import label.Element;
import label.Label;
import label.folder;

public class AddtitleCommand extends ModifyCommand {

    private String inputStr;

    public AddtitleCommand() {
        this.commandName = "add-title";
        this.regPattern = "^add-title\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'[\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+)(\\s+at\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+){1})?\\s*$";
    }

    public void Execute(Label label) {

    }

    public void UnExecute(Label label) {

    }

    public AddtitleCommand(String inputStr) {
        this.commandName = "add-title";
        this.regPattern = "^add-title\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'[\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+)(\\s+at\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+){1})?\\s*$";
        this.inputStr=inputStr;

    }

    public String getInputStr() {
        return this.inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }


    public void Execute(Label label, Label_dustbin label_dustbin) {
        String[] folders = inputStr.split(" ");
        int res = -1;
        if (folders.length == 1) {
            folder newfolder = new folder(inputStr, "#");
            label.labellist.add(newfolder);
            res = 0;
        } else if (folders.length == 3) {
            String new_folder_name = folders[0];
            String old_folder_name = folders[2];
            for (Element i : label.labellist) {
                if (i.getType().equals("folder")) {
                    folder old_folder1 = (folder) i;
                    if (old_folder1.getFoldname().equals(old_folder_name)) {
                        folder new_folder = new folder(new_folder_name, "##");
                        old_folder1.subordinates.add(new_folder);
                        res = 1;
                    }
                    for (Element j : old_folder1.subordinates) {
                        if (j.getType().equals("folder")) {
                            folder old_folder2 = (folder) j;
                            if (old_folder2.getFoldname().equals(old_folder_name)) {
                                String depth = old_folder2.depth;
                                folder new_folder = new folder(new_folder_name, "###");
                                old_folder2.subordinates.add(new_folder);
                                res = 2;
                            }
                        }
                    }
                }
            }
        }
        if (res >= 0) {
            System.out.println("add-title successfully");
        } else {
            System.out.println("add-title unsuccessfully");
        }


        //具体执行

    }

    ;

    public void UnExecute(Label label, Label_dustbin label_dustbin) {
        int res = -1;
        String folder_name = inputStr.split(" ")[0];
        for (int i = 0; i < label.labellist.size(); i++) {
            if (label.labellist.get(i).getType() == "folder") {
                folder folder_temp0 = (folder) label.labellist.get(i);
                if (folder_temp0.getFoldname().equals(folder_name)) {
                    label.labellist.remove(i);
                    res = 0;
                }
                for (int j = 0; j < folder_temp0.subordinates.size(); j++) {
                    if (folder_temp0.subordinates.get(j).getType() == "folder") {
                        folder folder_temp1 = (folder) folder_temp0.subordinates.get(j);
                        if (folder_temp1.getFoldname().equals(folder_name)) {
                            folder_temp0.subordinates.remove(j);
                            res = 1;
                        }
                        for (int k = 0; k < folder_temp1.subordinates.size(); k++) {
                            if (folder_temp1.subordinates.get(k).getType() == "folder") {
                                folder folder_temp2 = (folder) folder_temp1.subordinates.get(k);
                                if (folder_temp2.getFoldname().equals(folder_name)) {
                                    folder_temp1.subordinates.remove(k);
                                    res = 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (res >= 0) {
            System.out.println("undo add-title successfully");
        } else {
            System.out.println("undo add-title unsuccessfully");
        }
        //逆向执行

    }

    public String getCommandStr() { // example
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.commandName);
        stringBuilder.append(" ");
        stringBuilder.append(inputStr);
        return stringBuilder.toString();
    }


}
