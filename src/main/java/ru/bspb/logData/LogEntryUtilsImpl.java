package ru.bspb.logData;

import org.apache.commons.io.FilenameUtils;
import ru.bspb.Lesson8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogEntryUtilsImpl implements LogEntryUtils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    public List<LogEntry> getLogEntries(String path) throws IOException, URISyntaxException {
        URL url = Lesson8.class.getClassLoader().getResource(path);
        Map<String, List<String>> mapOfFiles = new HashMap<>();
        assert url != null;
        Files.find(Path.of(url.toURI()), 10, (file, attrs) -> file.toString().endsWith(".log"))
                .forEach(file ->
                        {
                            try {
                                mapOfFiles.put(file.getFileName().toString(), Files.readAllLines(file));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

        List<LogEntry> logEntryList = new ArrayList<>();
        for (Map.Entry<String, List<String>> element : mapOfFiles.entrySet()) {

            for (int i = 0; i < element.getValue().size(); i++) {
                LogEntry logEntry = new LogEntry();
                logEntry.setLineNumber(i + 1);
                logEntry.setLogFile(element.getKey());
                String[] elementLines = element.getValue().get(i).split(" - ");
                logEntry.setTimestamp(elementLines[0]);

                String debugLevel = elementLines[1];
                Pattern pattern = Pattern.compile("(.*?)[\\s]+\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(debugLevel);
                if (matcher.find()) {
                    logEntry.setDebugLevelType(matcher.group(1));
                    logEntry.setDebugLevelInfo(matcher.group(2));
                }

                logEntry.setMessage(elementLines[2]);
                logEntry.setLogFilePath(FilenameUtils.separatorsToSystem(url.toString()));
                logEntryList.add(logEntry);
            }

        }

        return logEntryList;
    }

    @Override
    public List<Map.Entry<String, Map<String, List<LogEntry>>>> groupLogByMessage(List<LogEntry> logEntryList) {
        Map<String, Map<String, List<LogEntry>>> result = logEntryList.stream().collect(
                (Collectors.groupingBy(
                        LogEntry::getMessage,
                        Collectors.groupingBy(LogEntry::getLogFile)
                )
                )
        );

        return result.entrySet().stream().filter(e -> e.getValue().size() > 1).collect(Collectors.toList());
    }


    @Override
    public void printList(List<LogEntry> list, String debugLevelType) {
        List<LogEntry> filteredList = list.stream().
                filter(element -> element.getDebugLevelType().equals(debugLevelType)).collect(Collectors.toList());
        System.out.print("\n" + ANSI_RED + "Фильтрация лога по типу: " + debugLevelType + ANSI_RESET);
        for (LogEntry entry : filteredList) {
            System.out.print(entry.toString());
        }

        System.out.printf("%nКоличество записей: %d%n", filteredList.size());
    }

    @Override
    public void printMap(List<Map.Entry<String, Map<String, List<LogEntry>>>> mapEntryList) {
        System.out.print("\n" + ANSI_RED + "Группировка лога по наименованию" + ANSI_RESET + "\n");

        for (Map.Entry<String, Map<String, List<LogEntry>>> e : mapEntryList) {
            System.out.println(ANSI_RED + "Message: " + e.getKey() + "\n" + ANSI_RESET + e.toString() + "\n");
        }

        System.out.printf(ANSI_RED + "%nКоличество записей: %d%n", mapEntryList.size());
    }


}
