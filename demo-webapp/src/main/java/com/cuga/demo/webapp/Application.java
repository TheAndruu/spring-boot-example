package com.cuga.demo.webapp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MongoClient mongoClient;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    /** String hosts expected value inputs such as: "localhost:27017,localhost:27017" */
    @Bean
    public MongoClient mongoClient(@Value("${mongo.db.hosts}") String hosts, @Value("${mongo.db.name}") String database,
                    @Value("${mongo.db.username}") String username, @Value("${mongo.db.password}") String password,
                    @Value("${mongo.db.connections}") int mongoConnections, @Value("${mongo.db.replica.set.name}") String replicaSet) {
        log.info("Setting up mongo client");

        Builder optionsBuilder = MongoClientOptions.builder().connectionsPerHost(mongoConnections);
        if (replicaSet.trim().length() > 1) {
            String cleanReplica = replicaSet.trim();
            log.info(String.format("Using mongo replica set %s", cleanReplica));
            optionsBuilder.requiredReplicaSetName(cleanReplica);
        }
        MongoClientOptions options = optionsBuilder.build();

        // MongoCredential credential = MongoCredential.createScramSha1Credential(username, database, password.toCharArray());

        List<ServerAddress> addresses = new ArrayList<>();
        String[] splitServerString = hosts.split(",");
        for (String serverString : splitServerString) {
            String[] splitHost = serverString.split(":");
            addresses.add(new ServerAddress(splitHost[0], Integer.parseInt(splitHost[1])));
        }

        // return new MongoClient(addresses, Arrays.asList(credential), options);
        return new MongoClient(addresses, options);
    }

    /** Gracefully shut down the mongo connections */
    @Bean
    DisposableBean closeDatabaseConnections() {
        return new DisposableBean() {
            @Override
            public void destroy() throws Exception {
                log.info("Shutting down mongo connection pool");
                mongoClient.close();
            }
        };
    }

    /**
     * Useful if we need to customize the container when running in embedded mode
     * 
     * @return bean
     */
    @Bean
    public EmbeddedServletContainerCustomizer customizeServletContainer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
            }
        };
    }

}