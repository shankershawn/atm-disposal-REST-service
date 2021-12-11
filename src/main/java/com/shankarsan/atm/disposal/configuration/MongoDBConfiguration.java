/**
 * 
 */
package com.shankarsan.atm.disposal.configuration;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.shankarsan.atm.disposal.constants.CommonConstants;

/**
 * @author SHANKARSAN
 *
 */
@Configuration
public class MongoDBConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public MongoCredential getMongoCredential() {
		return MongoCredential.createCredential(environment.getProperty(CommonConstants.MONGO_USERNAME), environment.getProperty(CommonConstants.MONGO_AUTH_DB),
				environment.getProperty(CommonConstants.MONGO_PASSWORD).toCharArray());
	}

	@Bean
	public MongoClientSettings getMongoClientSettings(@Qualifier("getMongoCredential") MongoCredential credential) {
		return MongoClientSettings
				.builder()
				.credential(credential)
				.applyToClusterSettings(block -> block.hosts(Arrays.stream(environment.getProperty(CommonConstants.MONGO_HOST).split(",")).map(host -> new ServerAddress(host)).collect(Collectors.toList())))
				.applyToSslSettings(block -> block.enabled(Boolean.TRUE))
				.build();
	}

	@Bean
	public MongoClient getMongoClient(@Qualifier("getMongoClientSettings") MongoClientSettings mongoClientSettings) {
		return MongoClients.create(mongoClientSettings);
	}

	@Bean
	public MongoTemplate getMongoTemplate(@Qualifier("getMongoClient") MongoClient mongoClient) {
		return new MongoTemplate(mongoClient, environment.getProperty(CommonConstants.MONGO_DATABASE));
	}
}
