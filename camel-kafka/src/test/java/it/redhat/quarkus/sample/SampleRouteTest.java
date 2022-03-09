package it.redhat.quarkus.sample;

import io.quarkus.test.junit.QuarkusTest;

import org.apache.camel.CamelContext;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.camel.component.mock.MockEndpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Properties;

@QuarkusTest
public class SampleRouteTest {

    @Inject
    public CamelContext camelContext;

    @Inject
    private ProducerTemplate producerTemplate;

    @BeforeEach
    public void attachTestProbes() throws Exception {

        AdviceWith.adviceWith(camelContext.adapt(ModelCamelContext.class).getRouteDefinition(SampleRoute.ROOT_ID_CONSUMER), camelContext,
            new AdviceWithRouteBuilder() {
                @Override
                public void configure() throws Exception {
                    interceptSendToEndpoint("log:complete").to("mock:complete");
                }
            });

        AdviceWith.adviceWith(camelContext.adapt(ModelCamelContext.class).getRouteDefinition(SampleRoute.ROOT_ID_PRODUCER), camelContext,
            new AdviceWithRouteBuilder() {
                @Override
                public void configure() throws Exception {
                    interceptSendToEndpoint("log:complete").to("mock:complete");
                }
            });
    }

    @Test
    public void testConsumer() throws InterruptedException {
        MockEndpoint mockComplete = camelContext.getEndpoint("mock:complete", MockEndpoint.class);

        mockComplete.expectedMinimumMessageCount(3);

        KafkaProducer producer = getKafkaProducer();

        producer.send(new ProducerRecord<String, String>(SampleRoute.CONSUMER_TOPIC, "Sample 1"));
        producer.send(new ProducerRecord<String, String>(SampleRoute.CONSUMER_TOPIC, "Sample 2"));
        producer.send(new ProducerRecord<String, String>(SampleRoute.CONSUMER_TOPIC, "Sample 3"));

        mockComplete.assertIsSatisfied();
    }

    @Test
    public void testProducer() throws InterruptedException, IOException {
        MockEndpoint mockComplete = camelContext.getEndpoint("mock:complete", MockEndpoint.class);

        mockComplete.expectedMinimumMessageCount(3);

        producerTemplate.sendBody("direct:produce", "Sample 1");
        producerTemplate.sendBody("direct:produce", "Sample 2");
        producerTemplate.sendBody("direct:produce", "Sample 3");

        mockComplete.assertIsSatisfied();
    }

    private KafkaProducer<String, String> getKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://localhost:55029");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
}
