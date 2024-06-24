package org.wikimedia.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class ChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    // We don't need @Autowired Spring will inject it!
    public ChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia-recent-change";
        // To read real time stream data from wikimedia, we have to use those dependencies -->
        // event source, Jackson core for JSON, Jackson databind, okhttp
        EventHandler eventHandler = new ChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}

