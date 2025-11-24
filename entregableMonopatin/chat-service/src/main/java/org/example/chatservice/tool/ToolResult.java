package org.example.chatservice.tool;

public class ToolResult {
    private boolean ok;
    private String message;
    private Object data;

    public ToolResult(boolean ok, String message, Object data) {
        this.ok = ok; this.message = message; this.data = data;
    }
    // getters/setters
    public boolean isOk() { return ok; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
}