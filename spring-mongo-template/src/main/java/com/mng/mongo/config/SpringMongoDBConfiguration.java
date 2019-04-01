package com.mng.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class SpringMongoDBConfiguration {

	@Autowired
	private MongoDbProperties mongoDbProperties;

	@Bean
	public MongoDbFactory getMongoDbFactory() {
		MongoClient client = new MongoClient(mongoDbProperties.getHost(), mongoDbProperties.getPort());
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(client, mongoDbProperties.getDataBase());
		return simpleMongoDbFactory;
	}
	
	@Bean
	public MongoTemplate getMongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
		return mongoTemplate;
	}
}
