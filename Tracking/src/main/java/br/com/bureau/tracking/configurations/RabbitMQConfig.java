package br.com.bureau.tracking.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${queue.response.user}")
    private String getUserReponse;
	
	@Value("${queue.person}")
    private String getPersonQueue;
	
	@Value("${queue.address}")
    private String getAddressQueue;
	
	@Value("${queue.person.update}")
    private String getPersonUpdateQueue;
	
	@Bean
	public Queue userReponse() {
		return new Queue(this.getUserReponse, true);
	}
	
	@Bean
	public Queue personQueue() {
		return new Queue(this.getPersonQueue, true);
	}
	
	@Bean
	public Queue addressQueue() {
		return new Queue(this.getAddressQueue, true);
	}
	
	@Bean
	public Queue personUpdateQueue() {
		return new Queue(this.getPersonUpdateQueue, true);
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	    return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
}
