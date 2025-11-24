package org.example.chatservice.tool;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ToolRegistry {
    private final Map<String, Tool> tools = new LinkedHashMap<>();

    public void register(Tool t) {
        tools.put(t.getName(), t);
    }

    public Tool getTool(String name) { return tools.get(name); }
    public Map<String, Tool> getAll() { return tools; }
}
