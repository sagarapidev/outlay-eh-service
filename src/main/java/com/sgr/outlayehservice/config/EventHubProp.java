package com.sgr.outlayehservice.config;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class EventHubProp {

    private final List<EventRoute> routes;

    public EventHubProp(@Value("${EVENTHUB_CONFIG}") String eventHubConfigJson) {
        // Debug: print raw string to see what Spring injects
        System.out.println("EVENTHUB_CONFIG raw: " + eventHubConfigJson);

        Gson gson = new Gson();
        EventHubConfig config = gson.fromJson(eventHubConfigJson, EventHubConfig.class);
        this.routes = config.getRoutes();
    }
}