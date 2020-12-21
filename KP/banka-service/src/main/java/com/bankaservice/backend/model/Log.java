package com.bankaservice.backend.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {

    private Date timestamp;
    private LogType logType;
    private String sourceName;
    private Integer eventId;
    private String message;

    public Log() {
        this.timestamp = new Date();
    }

    public Log(String line) throws ParseException {
        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
        String[] parts = line.split("\\|");
        this.timestamp = simpleDateFormat.parse(parts[0]);
        this.logType = LogType.valueOf(parts[1]);
        this.sourceName = parts[2];
        this.eventId = Integer.valueOf(parts[3]);
        this.message = parts[4];

    }

    public Log(LogType logType, String sourceName, Integer eventId, String message){
        this.logType = logType;
        this.sourceName = sourceName;
        this.eventId = eventId;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

        String dateString = simpleDateFormat.format(this.getTimestamp());

        return dateString +
                "|" + logType +
                "|" + sourceName +
                "|" + eventId +
                "|" + message + "\n";
    }

    public static ArrayList<Log> readLogs(String filename) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        ArrayList<Log> logs = new ArrayList<>();
        for (String line : allLines) {
            try {
                Log l = new Log(line);
                logs.add(l);
            } catch (ParseException ignored) {
            }
        }
        return logs;
    }
}
