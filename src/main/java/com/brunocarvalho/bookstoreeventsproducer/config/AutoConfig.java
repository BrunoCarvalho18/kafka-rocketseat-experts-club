package com.brunocarvalho.bookstoreeventsproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoConfig {

	@Bean
	public NewTopic bookStoreEvents() {

		return TopicBuilder.name("bookstore-events")
				.partitions(1)
				.replicas(1)
				.build();
	}

}
