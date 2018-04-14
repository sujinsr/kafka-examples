package org.kafka.examples.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallback implements Callback {

	@Override
	public void onCompletion(RecordMetadata recordMetadata, Exception exp) {
		if (exp == null) {
			System.out.println("Message sent successfully to the partition " + recordMetadata.partition());
		} else {
			System.out.println("Failed to send the message.");
		}
	}

}
