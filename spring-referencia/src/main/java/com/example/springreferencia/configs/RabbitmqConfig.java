package com.example.springreferencia.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${amqp.queue}")
    private String queue;
    @Value("${amqp.exchange}")
    private String exchange;
    @Value("${amqp.routing-key}")
    private String routingKey;

    // Define the Queue
    @Bean
    public Queue queue() {
        return new Queue(queue, true); // true = durable queue
    }

    // Define the Topic Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Bind the Queue to the Exchange using a Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    // JSON Message Converter (Translates objects to JSON strings automatically)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
