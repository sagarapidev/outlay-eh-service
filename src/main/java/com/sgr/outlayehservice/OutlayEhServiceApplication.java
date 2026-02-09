package com.sgr.outlayehservice;

import com.azure.messaging.eventhubs.EventProcessorClient;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OutlayEhServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OutlayEhServiceApplication.class, args);

	}
}
