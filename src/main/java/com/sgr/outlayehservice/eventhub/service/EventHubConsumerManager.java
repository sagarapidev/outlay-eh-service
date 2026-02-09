package com.sgr.outlayehservice.eventhub.service;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.models.PartitionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgr.outlayehservice.Services.Interface.UserService;
import com.sgr.outlayehservice.config.EventHubProp;
import com.sgr.outlayehservice.config.EventRoute;
import com.sgr.outlayehservice.dto.UserDto;
import com.sgr.outlayehservice.model.User;
import com.sgr.outlayehservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;



@Component
public class EventHubConsumerManager {

    private final EventHubProp eventHubProp;
    private final UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventHubConsumerManager(EventHubProp eventHubProp, UserService userService) {
        this.eventHubProp = eventHubProp;
        this.userService = userService;
    }

    @PostConstruct
    public void startConsumers() {
        for (EventRoute route : eventHubProp.getRoutes()) {
            startConsumer(route);
        }
    }

    private void startConsumer(EventRoute route) {
        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
                .connectionString(route.getConnectionString(), route.getEventHubName())
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();

        consumer.receive(false).subscribe(event -> handleEvent(route, event));
    }

    private void handleEvent(EventRoute route, PartitionEvent event) {
        String body = event.getData().getBodyAsString();
        System.out.println("[" + route.getEventName() + "] Event received: " + body);

        try {
            UserDto user = mapper.readValue(body, UserDto.class);

            // Decide based on eventName
            if (route.getEventName().toLowerCase().contains("create")) {
                userService.createUser(user);
                System.out.println("[" + route.getEventName() + "] User created successfully");
            } else if (route.getEventName().toLowerCase().contains("update")) {
                userService.updateUser(user);
                System.out.println("[" + route.getEventName() + "] User updated successfully");
            } else {
                System.out.println("[" + route.getEventName() + "] No matching handler, skipping");
            }

        } catch (Exception e) {
            System.err.println("Failed to process event for " + route.getEventName() + ": " + e.getMessage());
        }
    }
}


