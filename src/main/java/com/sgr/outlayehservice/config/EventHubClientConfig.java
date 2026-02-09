package com.sgr.outlayehservice.config;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;


@Configuration
public class EventHubClientConfig {

    @Bean
    public EventHubConsumerAsyncClient eventHubConsumerClient(EventHubProp eventHubProp) {
        // Example: use the first route for now
        EventRoute route = eventHubProp.getRoutes().getFirst();

        return new EventHubClientBuilder()
                .connectionString(route.getConnectionString(), route.getEventHubName())
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();
    }
}

