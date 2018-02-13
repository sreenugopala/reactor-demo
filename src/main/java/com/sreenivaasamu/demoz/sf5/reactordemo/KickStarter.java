package com.sreenivaasamu.demoz.sf5.reactordemo;

import static reactor.bus.selector.Selectors.$;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sreenivaasamu.demoz.sf5.reactordemo.service.impl.SharpShooter;

import reactor.Environment;
import reactor.bus.EventBus;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class KickStarter implements CommandLineRunner
{
    @Autowired
    private EventBus eventBus;
    
    @Autowired    
    private SharpShooter sharpShooter;
     
    @Override
    public void run(String... args) throws Exception {
        eventBus.on($("sharpShooter"), sharpShooter);
    }

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

    public static void main( String[] args )
    {
    	SpringApplication.run(KickStarter.class, args);
    }
}
