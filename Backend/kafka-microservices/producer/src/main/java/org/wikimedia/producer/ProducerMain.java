package org.wikimedia.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerMain.class,args);
    }
    @Autowired
    private ChangesProducer changesProducer;

    @Override
    public void run(String... args) throws Exception {
        changesProducer.sendMessage();
    }
}
