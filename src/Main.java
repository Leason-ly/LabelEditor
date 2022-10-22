import service.EditorService;
import service.EditorServiceImpl;
import test.TestExecute;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static EditorService editorService = new EditorServiceImpl();
    public static TestExecute testExecute = new TestExecute();


    public static String Input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public static void main(String[] args) throws IOException{

        if(args.length != 0 && args[0].equals("-test")) {
            if(testExecute.RunTest()) {
                System.out.println("All tests finished successfully...");
            } else {
                System.out.println("Test Failed...");
            }
        } else {
            //样例，应该完成打开label文件，读取label内容
            if(args.length == 2 && args[0].equals("-t")) {
                System.out.println("Please enter you Open command as a start:");
            }
            String commandStr;
            while(true) {
                commandStr = Input().trim();
                if(commandStr.equals("exit"))
                    break;
                if(null != commandStr)
                    editorService.doCommand(commandStr);

            }
        }




    }
}