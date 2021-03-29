package ru.bspb.logData;


public class LogEntry {
    private String timestamp;
    private String debugLevelType;
    private String debugLevelInfo;
    private String message;
    private int lineNumber;
    private String logFile;
    private String logFilePath;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDebugLevelType() {
        return debugLevelType;
    }

    public void setDebugLevelType(String debugLevelType) {
        this.debugLevelType = debugLevelType;
    }

    public String getDebugLevelInfo() {
        return debugLevelInfo;
    }

    public void setDebugLevelInfo(String debugLevelInfo) {
        this.debugLevelInfo = debugLevelInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public String toString() {
        return String.format("%nFile: %s, line: %d: %s - %s - %s",
                logFile, lineNumber, timestamp, debugLevelType, message);
    }

}
