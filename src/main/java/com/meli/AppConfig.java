package com.meli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public CqlSessionFactoryBean session() {

        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
        session.setContactPoints(env.getProperty("HOST")==null?"localhost:9041":env.getProperty("HOST"));
        session.setKeyspaceName(env.getProperty("KEY_SPACE")==null?"meli_keyspace":env.getProperty("KEY_SPACE"));
        session.setLocalDatacenter(env.getProperty("DATACENTER")==null?"datacenter1":env.getProperty("DATACENTER"));

        return session;
    }
}