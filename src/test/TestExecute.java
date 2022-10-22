package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestExecute {
    public TestExecute() {
    }

    public Boolean RunTest(){
        Result result;
        result = JUnitCore.runClasses(OpenCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("open command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(AddtitleCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("add-title command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(AddbookmarkCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("add-bookmark command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(DeletetitleCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("delete-title command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(DeletebookmarkCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("delete-bookmark command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(ReadBookmarkCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("read-bookmark command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(UndoCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("undo command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(RedoCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("redo command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(ShowTreeCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("show-tree command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(LsTreeCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("ls-tree command finished successfully...");
        } else return false;

        result = JUnitCore.runClasses(SaveCommandTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("save command finished successfully...");
        } else return false;


        return true;
    }
}
