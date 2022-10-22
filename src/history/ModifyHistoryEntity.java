package history;

import command.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ModifyHistoryEntity extends History {

    @Override
    public ArrayList<Command> getLastK(Integer k) {
        List<Command> rtnList;
        ArrayList<Command> rtnArrayList = new ArrayList<Command>();
        if (now == 0)
            return new ArrayList<Command>();
        if (k >= now) {
            rtnList = historyList.subList(0, now);
        } else {
            rtnList = historyList.subList(now - k, now);
        }
        rtnArrayList.addAll(rtnList);
        Collections.reverse(rtnArrayList);
        return rtnArrayList;
    }

    @Override
    public void add(Command command) {
        historyList.add(now++, command);
        // System.out.println(historyList.get(now - 1).getCommandStr());
        int len = historyList.size();
        int tmp = 0;
        if (len > now) {
            Iterator iter = historyList.iterator();
            while (iter.hasNext()) {
                Object nowCmd = iter.next();
                if (tmp++ >= now)
                    iter.remove();
                ;
            }
        }
    }

    @Override
    public Boolean canUndo() {
        if (now == 0) {
            System.out.println("Can not undo cause this may be the last command");
            return false;
        }
        return true;
    }

    @Override
    public Boolean canRedo() {
        if (now == historyList.size()) {
            System.out.println("Can not redo cause this may be the newest command");
            return false;
        }

        return true;
    }

    @Override
    public Command undo() {
        if (now == 0) {

            return null;
        }
        return historyList.get(--now);
    }

    @Override
    public Command redo() {
        if (now == historyList.size())
            return null;

        return historyList.get(now++);
    }


}
