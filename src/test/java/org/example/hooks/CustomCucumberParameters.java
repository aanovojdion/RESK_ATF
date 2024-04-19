package org.example.hooks;

import io.cucumber.java.ParameterType;
import org.example.configurations.api_configs.EndPoints;

public class CustomCucumberParameters {
    @ParameterType(".*")
    public EndPoints endPoint(String value) {
        return EndPoints.valueOf(value);
    }
}
