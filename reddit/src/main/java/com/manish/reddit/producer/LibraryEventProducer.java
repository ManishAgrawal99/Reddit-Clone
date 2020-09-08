package com.manish.reddit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manish.reddit.model.NotificationEmail;

@Component
public class LibraryEventProducer {

	private static final Logger logger = LoggerFactory.getLogger(LibraryEventProducer.class);
	
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void sendMailEvent(NotificationEmail notificationEmail) throws JsonProcessingException {
		
		String key = notificationEmail.getRecipient();
		String value = objectMapper.writeValueAsString(notificationEmail);
		
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.sendDefault(key, value);
		
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                handleSuccess(key, value, result);
            }
        });
	}
	
	private void handleFailure(String key, String value, Throwable ex) {
    	logger.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
        	logger.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
    	logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
	
}
