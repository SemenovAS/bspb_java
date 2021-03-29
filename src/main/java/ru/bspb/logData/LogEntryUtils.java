package ru.bspb.logData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface LogEntryUtils {
    /**
     * Возвращает лист LogEntry из лог файлов
     *
     * @param path Путь к лог-файлам
     * @return Возвращает лист LogEntry из лог файлов
     * @throws IOException        Signals that an I/O exception of some sort has occurred
     * @throws URISyntaxException Checked exception thrown to indicate that a string could not be parsed as a URI reference
     */
    List<LogEntry> getLogEntries(String path) throws IOException, URISyntaxException;

    /**
     * Возвращает лист LogEntry с группировкой по message
     *
     * @param logEntryList лист LogEntry
     * @return Возвращает лист LogEntry с группировкой по message
     */
    List<Map.Entry<String, Map<String, List<LogEntry>>>> groupLogByMessage(List<LogEntry> logEntryList);

    /**
     * Выводит в консоль лист LogEntry c фильтром по debugLevel
     *
     * @param list           лист LogEntry
     * @param debugLevelType уровень debugLevel
     */
    void printList(List<LogEntry> list, String debugLevelType);

    /**
     * Выводит в консоль лист LogEntry
     *
     * @param mapEntryList лист mapEntry
     */
    void printMap(List<Map.Entry<String, Map<String, List<LogEntry>>>> mapEntryList);
}
