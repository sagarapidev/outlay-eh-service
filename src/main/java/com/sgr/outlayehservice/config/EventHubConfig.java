package com.sgr.outlayehservice.config;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

@Data
public class EventHubConfig {
    @SerializedName("Routes")
    private List<EventRoute> routes;
}