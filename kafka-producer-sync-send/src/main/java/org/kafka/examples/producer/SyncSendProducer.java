package org.kafka.examples.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.*;

public class SyncSendProducer 
{
    public static void main( String[] args )
    {
    	String key 		= "key-two";
    	String value 	= "value-two";
    	String topic	= "example-topic";
    	
    	Properties props = new Properties();
    	props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
    	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	
    	Producer<String, String> producer = new KafkaProducer<>(props);
    	
    	ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
    	
    	try {
			RecordMetadata recordMetadata = producer.send(producerRecord).get();
			System.out.println("Message sent successfully to the partition " + recordMetadata.partition());
			System.out.println("Message sent successfully to the broker.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to send the message.");
		} finally {
			producer.close();
		}
    	
    	System.out.println("SyncSendProducer finished.");
    }
}
