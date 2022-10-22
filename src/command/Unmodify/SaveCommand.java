package command.Unmodify;
import command.UnmodifyCommand;
import dustbin.Label_dustbin;
import label.Label;
import label.Element;
import label.Link;
import label.folder;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveCommand extends UnmodifyCommand {

    private String inputStr;
    public SaveCommand(){
        this.commandName="save";
        this.regPattern="^save \"[a-zA-Z]:(((\\\\(?! )[^/:*?<>\\\"\"|\\\\]+)+\\\\?)|(\\\\)?)\"[ ]*$";

    }
    public SaveCommand(String inputstr){
        this.commandName="save";
        this.regPattern="^save \"[a-zA-Z]:(((\\\\(?! )[^/:*?<>\\\"\"|\\\\]+)+\\\\?)|(\\\\)?)\"[ ]*$";
        this.inputStr=inputstr;

    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public void Execute(Label label) {

        try{
            File file = new File(inputStr);
            if(!file.exists()) {
                file.createNewFile();
            }
            OutputStream outPutStream;

            outPutStream = new FileOutputStream(file);
            StringBuilder stringBuilder = new StringBuilder();//使用长度可变的字符串对象；

            //TODO 这里写你的代码逻辑;
            List<Element> A = label.labellist;
            //参考 https://www.dounaite.com/article/627cd61bac359fc9132dc33b.html
            Stack<Element> stack = new Stack<>();
            for (Element i : A) {
                stack.push(i);
                while (!stack.empty()){
                    Element t = stack.pop();
                    folder fd = (folder)t;
                    stringBuilder.append(fd.depth+" "+fd.getFoldname()+"\n");//追加文件内容
                    if (!fd.getSubordinates().isEmpty()){
                        List<Element> sub = fd.getSubordinates();
                        for (Element j1 : sub){  //先打印link
                            if (j1.getType() == "link"){
                                Link link = (Link)j1;
                                stringBuilder.append("["+link.getLinkname()+"]"+"("+link.getLinkcontent()+")"+"\n");//追加文件内容
                            }
                        }
                        Collections.reverse(sub);//似乎＃后加了空格识别到md编码就不需要逆置了
                        for (Element j2 : sub){  //逆置list以保证原顺序
                            if (j2.getType() == "folder"){
                                stack.push(j2);
                            }
                        }
                        Collections.reverse(sub);
                    }
                }
            }

            String context = stringBuilder.toString();//将可变字符串变为固定长度的字符串，方便下面的转码；
            byte[]  bytes = context.getBytes("UTF-8");//因为中文可能会乱码，这里使用了转码，转成UTF-8；
            outPutStream.write(bytes);//开始写入内容到文件；
            outPutStream.close();//一定要关闭输出流；
        }catch(IOException e){
            e.printStackTrace();//获取异常
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

        return "";
    }
}
