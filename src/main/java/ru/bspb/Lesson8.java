package ru.bspb;

import ru.bspb.logData.LogEntry;
import ru.bspb.logData.LogEntryUtilsImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class Lesson8 {
    public static final String KEY_PATH = "ru/bspb/logFiles";
    public static LogEntryUtilsImpl logUtils = new LogEntryUtilsImpl();


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<LogEntry> logEntryList = logUtils.getLogEntries(KEY_PATH);
        logUtils.printList(logEntryList, "WARN");

        List<Map.Entry<String, Map<String, List<LogEntry>>>> groupedLogEntryList =
                logUtils.groupLogByMessage(logEntryList);
        logUtils.printMap(groupedLogEntryList);

    }


}
