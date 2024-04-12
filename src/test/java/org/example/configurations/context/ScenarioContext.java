package org.example.configurations.context;

import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {
    private final Map<String, Object> scenarioContext;
    private static ScenarioContext instance;

    private ScenarioContext() {
        instance = this;
        scenarioContext = new HashMap<>();
    }

    public void setContext(ContextKeys key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public <T> T getContext(ContextKeys key, Class<T> type) {
        return type.cast(scenarioContext.get(key.toString()));
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            new ScenarioContext();
        }
        return instance;
    }

    public enum ContextKeys {
        DRIVER,
        RESPONSE,
        USERDATA
    }
}