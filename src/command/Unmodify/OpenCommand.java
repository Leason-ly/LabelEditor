package command.Unmodify;

import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import label.Element;
import label.Label;
import label.Link;
import label.folder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class OpenCommand extends UnmodifyCommand {
    private String inputStr;

    public OpenCommand() {
        this.commandName = "open";
        this.regPattern = "open"; //example

    }

    public OpenCommand(String inputstr) {
        this.commandName = "open";
        this.regPattern = "open";
        this.inputStr = inputstr;

    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }


    public void Execute(Label label) {
        try {
            label.labellist.clear();
            String fileName = inputStr;
            File myFile = new File(fileName);
            if (myFile.isFile() && myFile.exists()) { //判断文件是否存在

                InputStreamReader Reader = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(Reader);
                String lineTxt = null;
                folder lastFirstFolder = null;
                folder lastSecondFolder = null;
                folder latstFolder = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
//                     buffereReader.readLine()按行读取写成字符串
//                    System.out.println(lineTxt);
                    if (lineTxt.equals(""))
                        continue;
                    String firStr = lineTxt.split(" ", 2)[0];
                    if (firStr.equals("#")) {
                        folder thisFolder = new folder(lineTxt.split(" ", 2)[1].split(" ", 2)[0], "#");
                        label.labellist.add(thisFolder);
                        lastFirstFolder = thisFolder;
                        latstFolder = thisFolder;
                        continue;
                    }
                    if (firStr.equals("##")) {
                        folder thisFolder = new folder(lineTxt.split(" ", 2)[1].split(" ", 2)[0], "##");
                        lastFirstFolder.subordinates.add(thisFolder);
                        lastSecondFolder = thisFolder;
                        latstFolder = thisFolder;
                        continue;
                    }
                    if (firStr.equals("###")) {
                        folder thisFolder = new folder(lineTxt.split(" ", 2)[1].split(" ", 2)[0], "###");
                        lastSecondFolder.subordinates.add(thisFolder);
                        latstFolder = thisFolder;
                        continue;
                    }
                    String linkName = lineTxt.split("\\]\\(", 2)[0];
                    String linkcontent = lineTxt.split("\\]\\(", 2)[1].split(" ", 2)[0];
                    linkName = linkName.substring(1, linkName.length());
                    linkcontent = linkcontent.substring(0, linkcontent.length() - 1);
                    latstFolder.subordinates.add(new Link(linkName, linkcontent));

                }
                Reader.close();
                System.out.println("read file \"" + fileName + "\" successfully !");
            } else {
                myFile.createNewFile();
                System.out.println("The specified file could not be found, a new file has been created !");
            }
        } catch (Exception e) {
            System.out.println("Error reading file content, please try again !");
        }

    }

    @Override
    public void Execute(Label label, Label_dustbin dustbin) {

    }

    ;

    public void UnExecute(Label label) {

    }

    @Override
    public void UnExecute(Label label, Label_dustbin dustbin) {

    }

    ;

    public void Execute() {
        // label.labellist.add() 将读到的elemment装入label中。
    }

    public String getCommandStr() {// example

        return "";
    }
}
