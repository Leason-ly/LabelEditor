package command.Modify;

import command.ModifyCommand;
import dustbin.Label_dustbin;
import dustbin.folder_dust;
import label.Element;
import label.Label;
import label.folder;

public class DeletetitleCommand extends ModifyCommand {
    private String inputStr;

    public DeletetitleCommand() {
        this.commandName = "delete-title";
        this.regPattern = "^delete-title\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'[\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+)\\s*$";
    }

    public DeletetitleCommand(String inputStr) {
        this.commandName = "delete-title";
        this.regPattern = "^delete-title\\s+(\\\"[\\u4e00-\\u9fa5\\d\\w-_]+\\\"|'[\\u4e00-\\u9fa5\\d\\w-_]+'|[\\u4e00-\\u9fa5\\d\\w-_]+)\\s*$";
        this.inputStr = inputStr;
    }

    public String getInputStr() {
        return this.inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public void Execute(Label label) {

    }

    @Override
    public void UnExecute(Label label) {

    }

    public void Execute(Label label, Label_dustbin label_dustbin) {

        int res = -1;
        String folder_name = inputStr;
        for (int i = 0; i < label.labellist.size(); i++) {
            if (label.labellist.get(i).getType().equals("folder")) {
                folder folder_temp0 = (folder) label.labellist.get(i);
                if (folder_temp0.getFoldname().equals(folder_name)) {
                    folder_dust gar0 = new folder_dust("", i, folder_temp0);
                    label.labellist.remove(i);
                    label_dustbin.dustbinlist.add(gar0);
                    res = 0;
                }
                for (int j = 0; j < folder_temp0.subordinates.size(); j++) {
                    if (folder_temp0.subordinates.get(j).getType().equals("folder")) {
                        folder folder_temp1 = (folder) folder_temp0.subordinates.get(j);
                        if (folder_temp1.getFoldname().equals(folder_name)) {
                            folder_dust gar1 = new folder_dust(folder_temp0.getFoldname(), j, folder_temp1);
                            folder_temp0.subordinates.remove(j);
                            label_dustbin.dustbinlist.add(gar1);
                            res = 1;
                        }
                        for (int k = 0; k < folder_temp1.subordinates.size(); k++) {
                            if (folder_temp1.subordinates.get(k).getType().equals("folder")) {
                                folder folder_temp2 = (folder) folder_temp1.subordinates.get(k);
                                if (folder_temp2.getFoldname().equals(folder_name)) {
                                    folder_dust gar2 = new folder_dust(folder_temp1.getFoldname(), j, folder_temp2);
                                    folder_temp1.subordinates.remove(k);
                                    label_dustbin.dustbinlist.add(gar2);
                                    res = 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (res >= 0) {
            System.out.println("Delete-title successfully!");
        } else {
            System.out.println("Delete-title unsuccessfully!");
        }
        //具体执行

    }

    ;

    public void UnExecute(Label label, Label_dustbin label_dustbin) {
        int res = -1;
        int len = label_dustbin.dustbinlist.size();
        folder_dust gar = label_dustbin.dustbinlist.get(len - 1);
        String father_name = gar.getFather_folder_name();
        int number = gar.getFoldernumber();
        Element element = (folder) gar.getElement();
        if (father_name.equals("")) {
            label.labellist.add(number, element);
            res = 0;
        } else {
            for (int i = 0; i < label.labellist.size(); i++) {
                if (label.labellist.get(0).getType().equals("folder")) {
                    folder folder_temp0 = (folder) label.labellist.get(i);
                    if (folder_temp0.getFoldname().equals(father_name)) {
                        folder_temp0.subordinates.add(number, element);
                        res = 1;
                    }
                    for (int j = 0; j < folder_temp0.subordinates.size(); j++) {
                        if (folder_temp0.subordinates.get(j).getType().equals("folder")) {
                            folder folder_temp1 = (folder) folder_temp0.subordinates.get(j);
                            if (folder_temp1.getFoldname().equals(father_name)) {
                                folder_temp1.subordinates.add(number, element);
                                res = 2;
                            }
                        }
                    }
                }
            }
        }
        if (res >= 0) {
            label_dustbin.dustbinlist.remove(len - 1);
            System.out.println("Undo delete-title successfully!");
        } else {
            System.out.println("Undo delete-title unsuccessfully!");
        }

    }

    public String getCommandStr() { // example
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.commandName);
        stringBuilder.append(" ");
        stringBuilder.append(inputStr);
        return stringBuilder.toString();
    }
}
