package org.example.chatservice.tool;


import java.util.Map;

public interface Tool {
    String getName();
    String getDescription();
    ToolResult execute(Map<String, Object> args);
}