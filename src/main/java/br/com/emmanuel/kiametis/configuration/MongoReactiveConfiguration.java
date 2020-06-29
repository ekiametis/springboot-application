package br.com.emmanuel.kiametis.configuration;

import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories
@EnableMongoAuditing
public class MongoReactiveConfiguration
    extends AbstractReactiveMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "teste";
  }

  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }
}