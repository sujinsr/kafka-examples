package org.kafka.examples.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;

/**
 * Simple consumer!
 *
 */
public class SimpleConsumer 
{
	public static void main( String[] args )
	{
		String topic	= "example-topic";
		String groupId	= "example-topic-group";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
		props.put("group.id", groupId);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
		kafkaConsumer.subscribe(Arrays.asList(topic));

		try {
			while(true) {
				ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100); 
				for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					System.out.println("Record key = " + consumerRecord.key() + ", value = " + consumerRecord.value());
				}
				kafkaConsumer.commitSync();
			}   
		} finally {
			kafkaConsumer.close();
		}
	}
}
