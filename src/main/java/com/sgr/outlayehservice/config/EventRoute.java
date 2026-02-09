package com.sgr.outlayehservice.config;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRoute {

    @SerializedName("EventName")
    private String eventName;

    @SerializedName("ConnectionString")
    private String connectionString;

    @SerializedName("EventHubName")
    private String eventHubName;
}