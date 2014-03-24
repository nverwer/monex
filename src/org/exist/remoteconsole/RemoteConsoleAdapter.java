package org.exist.remoteconsole;

import org.exist.console.ConsoleAdapter;
import org.exist.xquery.value.DateTimeValue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RemoteConsoleAdapter implements ConsoleAdapter {

    private RemoteConsoleServlet servlet;

    public RemoteConsoleAdapter(RemoteConsoleServlet servlet) {
        this.servlet = servlet;
    }

    @Override
    public void log(String channel, String message) {
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("message", message);
        data.put("timestamp", new DateTimeValue(new Date()));

        servlet.send(channel, data);
    }

    @Override
    public void log(String channel, String source, int line, int column, String message) {
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("source", source);
        data.put("line", line);
        data.put("column", column);
        data.put("message", message);
        data.put("timestamp", new DateTimeValue(new Date()));

        servlet.send(channel, data);
    }
}