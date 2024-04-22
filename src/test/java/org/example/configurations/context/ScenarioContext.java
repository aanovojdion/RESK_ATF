package org.example.configurations.context;

import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {
    private static Map<ObjectKeys, Object> scenarioContext;
    private static ScenarioContext instance;


    private ScenarioContext() {
        instance = this;
        scenarioContext = new HashMap<>();
    }

    public void setContext(ObjectKeys key, Object value) {
        scenarioContext.put(key, value);
    }

    public <T> T getContext(ObjectKeys key, Class<T> type) {
        return type.cast(scenarioContext.get(key));
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            new ScenarioContext();
        }
        return instance;
    }

    public static void tearDown() {
        scenarioContext.clear();
        LogManager.getLogger().info("ScenarioContext has been cleared, size = {}", scenarioContext.size());
    }

    public enum ObjectKeys {
        RESPONSE,
        USERDATA,
        EMAIL,
        PASSWORD
    }
}