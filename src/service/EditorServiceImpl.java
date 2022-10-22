package service;

import command.Command;
import command.Modify.*;
import command.Unmodify.OpenCommand;
import command.Unmodify.RedoCommand;
import command.Unmodify.SaveCommand;
import command.Unmodify.UndoCommand;
import dustbin.Label_dustbin;
import factory.CommandFactory;
import history.History;
import history.ModifyHistoryEntity;
import label.Label;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorServiceImpl extends EditorService {
    private CommandFactory commandFactory = new CommandFactory();

    private History modifyHistory = new ModifyHistoryEntity();

    public Label_dustbin label_dustbin = new Label_dustbin();

    public EditorServiceImpl() {
        this.nowlabel = new Label();
    }

    @Override
    public void doCommand(String commandStr) {
        Command command = doCheckCommand(commandStr);
        if (null == command)
            return;
        String commandName = command.getCommandName();


        switch (commandName) {
            case "add-title":
            case "read-bookmark":
            case "add-bookmark":
            case "delete-bookmark":
            case "delete-title":
                command.Execute(nowlabel, label_dustbin);
                modifyHistory.add(command);
                break;
            case "ls-tree":
            case "show-tree":
            case "open":
            case "save":
                command.Execute(nowlabel);
                break;
            case "redo":
                ((RedoCommand) command).Execute(modifyHistory, nowlabel, label_dustbin);
                break;
            case "undo":
                ((UndoCommand) command).Execute(modifyHistory, nowlabel, label_dustbin);
                break;
            default:
                break;
        }
    }

    private Boolean checkCommandPattern(String commandStr, String regPattern) {
        Pattern pattern = Pattern.compile(regPattern);
        Matcher matcher = pattern.matcher(commandStr);
        return matcher.find();
    }

    private Command doCheckCommand(String commandStr) {

        String commandName = commandStr.split(" ")[0];
        if (null == commandName || "" == commandName || commandName.length() == 0)
            return null;

        String inputStr;
        if (commandStr.equals("show-tree") || commandStr.equals("ls-tree") || commandStr.equals("redo")
                || commandStr.equals("undo")) {
            return commandFactory.getInstance(commandStr);
        }
        if (commandName.equals("add-title")) {
            AddtitleCommand command = (AddtitleCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                inputStr = inputStr.replaceAll("\"", "");
                inputStr = inputStr.replaceAll("'", "");
                command.setInputStr(inputStr);
                return command;
            } else {
                System.out.println("add-title input is wrong");
                return null;
            }
        }
        if (commandName.equals("add-bookmark")) {
            AddbookmarkCommand command = (AddbookmarkCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                Integer strLen = inputStr.length();
                command.setInputStr(inputStr.substring(1, strLen - 1));
                return command;
            }
        }
        if (commandName.equals("delete-title")) {
            DeletetitleCommand command = (DeletetitleCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                inputStr = inputStr.replaceAll("\"", "");
                inputStr = inputStr.replaceAll("'", "");
                command.setInputStr(inputStr);
                return command;
            } else {
                System.out.println("delete-title input is wrong");
                return null;
            }
        }
        if (commandName.equals("delete-bookmark")) {
            DeletebookmarkCommand command = (DeletebookmarkCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                Integer strLen = inputStr.length();
                command.setInputStr(inputStr.substring(1, strLen - 1));
                return command;
            }
        }
        if (commandName.equals("read-bookmark")) {
            ReadBookmarkCommand command = (ReadBookmarkCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                Integer strLen = inputStr.length();
                command.setInputStr(inputStr.substring(1, strLen - 1));
                return command;
            }
        }
        if (commandName.equals("open")) {
            OpenCommand command = (OpenCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                Integer strLen = inputStr.length();
                if (inputStr.substring(0, 1).equals("\"") && inputStr.substring(strLen - 1, strLen).equals("\""))
                    command.setInputStr(inputStr.substring(1, strLen - 1));
                else {
                    command.setInputStr(inputStr);
                }
                return command;
            }
        }
        if (commandName.equals("save")) {
            SaveCommand command = (SaveCommand) commandFactory.getInstance(commandName);
            if (checkCommandPattern(commandStr, command.getRegPattern())) {
                inputStr = commandStr.split(" ", 2)[1];
                Integer strLen = inputStr.length();
                if (inputStr.substring(0, 1).equals("\"") && inputStr.substring(strLen - 1, strLen).equals("\""))
                    command.setInputStr(inputStr.substring(1, strLen - 1));
                else {
                    command.setInputStr(inputStr);
                }
                return command;
            }

        } else System.out.println("The command you enter does not exist");
        return null;
    }
}
