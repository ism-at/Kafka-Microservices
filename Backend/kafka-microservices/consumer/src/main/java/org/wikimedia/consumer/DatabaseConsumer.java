package org.wikimedia.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConsumer.class);

    private final ConsumerRepository consumerRepository;

    @Autowired
    public DatabaseConsumer(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @KafkaListener(topics = "wikimedia-recent-change", groupId = "consumerGroup")
    public void consume(String eventMessage) {
        try {
            LOGGER.info("Size of incoming data: {} bytes", eventMessage.length());
            LOGGER.info("Receiving Event Message --> {}", eventMessage);
            WikimediaEntity wikimediaEntity = new WikimediaEntity();
            wikimediaEntity.setWikimedia_event_data(eventMessage);
            consumerRepository.save(wikimediaEntity);
        } catch (Exception e) {
            LOGGER.error("Failed to save wikimedia event data", e);
        }
    }
}

